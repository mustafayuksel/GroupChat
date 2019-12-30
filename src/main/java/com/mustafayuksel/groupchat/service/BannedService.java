package com.mustafayuksel.groupchat.service;

import com.mustafayuksel.groupchat.domain.BannedUser;

public interface BannedService {
	void sendCouldBeBannedUserMessageViaEmail(String userId);

	void save(BannedUser bannedUser);
}