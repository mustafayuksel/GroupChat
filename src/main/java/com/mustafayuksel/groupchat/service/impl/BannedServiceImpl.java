package com.mustafayuksel.groupchat.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.groupchat.domain.BannedUser;
import com.mustafayuksel.groupchat.domain.Chat;
import com.mustafayuksel.groupchat.repository.BannedUserRepository;
import com.mustafayuksel.groupchat.repository.ChatRepository;
import com.mustafayuksel.groupchat.repository.PreferenceRepository;
import com.mustafayuksel.groupchat.response.BaseResponse;
import com.mustafayuksel.groupchat.service.BannedService;
import com.mustafayuksel.groupchat.service.MailSenderService;

@Service
public class BannedServiceImpl implements BannedService {

	private final ChatRepository chatRepository;

	private final MailSenderService mailSenderService;

	private final BannedUserRepository bannedUserRepository;

	private final PreferenceRepository preferenceRepository;

	@Autowired
	public BannedServiceImpl(ChatRepository chatRepository, MailSenderService mailSenderService,
			BannedUserRepository bannedUserRepository, PreferenceRepository preferenceRepository) {
		this.chatRepository = chatRepository;
		this.mailSenderService = mailSenderService;
		this.bannedUserRepository = bannedUserRepository;
		this.preferenceRepository = preferenceRepository;
	}

	@Override
	public void sendBannedUserMessageViaEmail(String userId) {
		List<Chat> chatDetails = chatRepository.findAllByUserId(userId);
		String bannedUserUrl = preferenceRepository.findByMnemonic("HOST_NAME").getName() + "/chat/banuser?userId="
				+ userId + "\n";
		String chatMessages = chatDetails.stream().map(c -> c.getId() + " " + c.getMessage().concat("\n")).reduce("",
				String::concat);
		mailSenderService.send(bannedUserUrl + chatMessages);
	}

	@Override
	public BaseResponse save(String userId) {
		List<BannedUser> bannedUsers = bannedUserRepository.findByUserId(userId);
		if (bannedUsers.isEmpty()) {
			BannedUser bannedUser = new BannedUser(userId);
			bannedUserRepository.save(bannedUser);
			return new BaseResponse("Inserted Successfully", true);
		}
		return new BaseResponse("Already Inserted!", true);
	}

	@Transactional
	@Override
	public BaseResponse delete(String userId) {
		bannedUserRepository.deleteByUserId(userId);
		return new BaseResponse("Deleted Successfully!", true);
	}
}