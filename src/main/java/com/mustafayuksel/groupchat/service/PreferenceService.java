package com.mustafayuksel.groupchat.service;

import com.mustafayuksel.groupchat.response.BaseResponse;

public interface PreferenceService {
	Boolean isChatOpen();

	BaseResponse updateChatFlag(String flag);
}