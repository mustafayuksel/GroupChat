package com.mustafayuksel.groupchat.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.groupchat.domain.BannedUser;
import com.mustafayuksel.groupchat.domain.Chat;
import com.mustafayuksel.groupchat.repository.BannedUserRepository;
import com.mustafayuksel.groupchat.repository.ChatRepository;
import com.mustafayuksel.groupchat.service.BannedService;
import com.mustafayuksel.groupchat.service.MailSenderService;

@Service
public class BannedServiceImpl implements BannedService {

	private final ChatRepository chatRepository;

	private final MailSenderService mailSenderService;

	private final BannedUserRepository bannedUserRepository;

	@Autowired
	public BannedServiceImpl(ChatRepository chatRepository, MailSenderService mailSenderService,
			BannedUserRepository bannedUserRepository) {
		this.chatRepository = chatRepository;
		this.mailSenderService = mailSenderService;
		this.bannedUserRepository = bannedUserRepository;
	}

	@Override
	public void sendCouldBeBannedUserMessageViaEmail(String userId) {
		List<Chat> chatDetails = chatRepository.findAllByUserId(userId);
		String bannedUserUrl = "";
		try {
			bannedUserUrl = InetAddress.getLocalHost().getHostAddress() + "/chat/banuser?userId=" + userId + "\n";
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		String chatMessages = chatDetails.stream().map(c -> c.getMessage().concat("\n")).reduce("", String::concat);
		mailSenderService.send(bannedUserUrl + chatMessages);
	}

	@Override
	public void save(BannedUser bannedUser) {
		bannedUserRepository.save(bannedUser);
	}
}
