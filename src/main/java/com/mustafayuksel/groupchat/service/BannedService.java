package com.mustafayuksel.groupchat.service;

import com.mustafayuksel.groupchat.response.BaseResponse;

public interface BannedService {
	void sendCouldBeBannedUserMessageViaEmail(String userId);

	BaseResponse save(String userId);

	BaseResponse delete(String userId);
}