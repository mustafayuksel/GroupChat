package com.mustafayuksel.groupchat.request;

public class CreateChatDetailRequest {
	private String languageCode;
	private String userId;
	private String message;
	private String userName;
	private String messageCreateDate;

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessageCreateDate() {
		return messageCreateDate;
	}

	public void setMessageCreateDate(String messageCreateDate) {
		this.messageCreateDate = messageCreateDate;
	}
}