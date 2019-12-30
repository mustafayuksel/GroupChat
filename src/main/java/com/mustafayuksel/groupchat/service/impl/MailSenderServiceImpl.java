package com.mustafayuksel.groupchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mustafayuksel.groupchat.service.MailSenderService;

@Service
public class MailSenderServiceImpl implements MailSenderService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public void send(String mailText) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("lovingdayslovecounter@gmail.com");

		msg.setSubject("Complained User Details");
		msg.setText(mailText);

		javaMailSender.send(msg);
	}
}