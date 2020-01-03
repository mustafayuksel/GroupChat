package com.mustafayuksel.groupchat.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.groupchat.domain.BannedUser;
import com.mustafayuksel.groupchat.domain.Chat;
import com.mustafayuksel.groupchat.dto.Status;
import com.mustafayuksel.groupchat.repository.BannedUserRepository;
import com.mustafayuksel.groupchat.repository.ChatRepository;
import com.mustafayuksel.groupchat.request.CreateChatDetailRequest;
import com.mustafayuksel.groupchat.response.BaseResponse;
import com.mustafayuksel.groupchat.response.ListChatDetailsResponse;
import com.mustafayuksel.groupchat.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	private final ChatRepository chatRepository;
	private final BannedUserRepository bannedUserRepository;

	@Autowired
	public ChatServiceImpl(ChatRepository chatRepository, BannedUserRepository bannedUserRepository) {
		this.chatRepository = chatRepository;
		this.bannedUserRepository = bannedUserRepository;
	}

	@Override
	public ListChatDetailsResponse getMessages(String languageCode) {
		return new ListChatDetailsResponse(true, null,
				chatRepository.findTop20ByStatusAndLanguageCodeOrderByCreateDateDesc(Status.ACTIVE, languageCode)
						.stream().map(Chat::toDTO).collect(Collectors.toList()));
	}

	@Override
	public BaseResponse deleteAll(String languageCode) {
		chatRepository.deleteAllByStatusAndLanguageCode(Status.ACTIVE, languageCode);
		return new BaseResponse("Deleted Succesfully!", true);
	}

	@Override
	public BaseResponse delete(Integer messageId) {
		chatRepository.deleteById(messageId);
		return new BaseResponse("Deleted Succesfully!", true);
	}

	@Override
	public ListChatDetailsResponse getAllMessages(String languageCode) {
		return new ListChatDetailsResponse(true, null,
				chatRepository.findAllByLanguageCodeOrderByCreateDateDesc(languageCode).stream().map(Chat::toDTO)
						.collect(Collectors.toList()));
	}

	@Override
	public ListChatDetailsResponse createMessage(CreateChatDetailRequest createChatDetailRequest) {
		List<BannedUser> bannedUsers = bannedUserRepository.findByUserId(createChatDetailRequest.getUserId());

		if (!bannedUsers.isEmpty()) {
			return new ListChatDetailsResponse(false, "BANNED_USER", null);
		} else {
			chatRepository.save(new Chat(createChatDetailRequest.getLanguageCode(), createChatDetailRequest.getUserId(),
					createChatDetailRequest.getMessage(), createChatDetailRequest.getUserName(),
					createChatDetailRequest.getMessageCreateDate()));

			List<Chat> allChatDetails = chatRepository.findAllByStatusOrderByCreateDateDesc(Status.ACTIVE);

			if (allChatDetails.size() > 20) {
				List<Chat> deletedChatDetails = allChatDetails.subList(20, allChatDetails.size());
				deletedChatDetails.get(0).setStatus(Status.PASSIVE);
				chatRepository.save(deletedChatDetails.get(0));
			}
			return getMessages(createChatDetailRequest.getLanguageCode());
		}
	}
}