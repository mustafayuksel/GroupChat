package com.mustafayuksel.groupchat.service;

import com.mustafayuksel.groupchat.request.CreateChatDetailRequest;
import com.mustafayuksel.groupchat.response.BaseResponse;
import com.mustafayuksel.groupchat.response.ListChatDetailsResponse;

public interface ChatService {
	ListChatDetailsResponse getMessages(String languageCode);

	ListChatDetailsResponse createMessage(CreateChatDetailRequest createChatDetailRequest);

	ListChatDetailsResponse getAllMessages(String languageCode);

	BaseResponse deleteAll(String languageCode);

	BaseResponse delete(Integer messageId);
}