package com.mustafayuksel.groupchat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.groupchat.domain.BannedUser;
import com.mustafayuksel.groupchat.domain.Chat;
import com.mustafayuksel.groupchat.repository.BannedUserRepository;
import com.mustafayuksel.groupchat.repository.ChatRepository;
import com.mustafayuksel.groupchat.repository.PreferenceRepository;
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
	public void sendCouldBeBannedUserMessageViaEmail(String userId) {
		List<Chat> chatDetails = chatRepository.findAllByUserId(userId);
		String bannedUserUrl = preferenceRepository.findByMnemonic("HOST_NAME").getName() + "/chat/banuser?userId="
				+ userId + "\n";
		String chatMessages = chatDetails.stream().map(c -> c.getMessage().concat("\n")).reduce("", String::concat);
		mailSenderService.send(bannedUserUrl + chatMessages);
	}

	@Override
	public void save(BannedUser bannedUser) {
		bannedUserRepository.save(bannedUser);
	}
}