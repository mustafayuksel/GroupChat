package com.mustafayuksel.groupchat.response;

import java.util.List;

import com.mustafayuksel.groupchat.dto.ChatDTO;

public class ListChatDetailsResponse extends BaseResponse {
	private List<ChatDTO> chatDetails;

	public List<ChatDTO> getChatDetails() {
		return chatDetails;
	}

	public ListChatDetailsResponse(Boolean isSuccess, String errorMessage, List<ChatDTO> chatDetails) {
		super(errorMessage, isSuccess);
		this.chatDetails = chatDetails;
	}

	public ListChatDetailsResponse() {
	}
}