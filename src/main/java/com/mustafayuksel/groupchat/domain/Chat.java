package com.mustafayuksel.groupchat.domain;

import javax.persistence.Entity;

import com.mustafayuksel.groupchat.dto.ChatDTO;

@Entity
public class Chat extends BaseEntity {

	private String languageCode;

	private String userId;

	private String message;

	private String userName;

	private String messageCreateDate;

	public Chat() {
	}

	public Chat(String languageCode, String userId, String message, String userName, String messageCreateDate) {
		super();
		this.languageCode = languageCode;
		this.userId = userId;
		this.message = message;
		this.userName = userName;
		this.messageCreateDate = messageCreateDate;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public ChatDTO toDTO() {
		return new ChatDTO(this.userId, this.message, this.userName, this.messageCreateDate);
	}
}