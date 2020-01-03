package com.mustafayuksel.groupchat.dto;

public class ChatDTO {
	private String userId;
	private Integer messageId;
	private String message;
	private String userName;
	private String createDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public String getMessage() {
		return message;
	}

	public ChatDTO(String userId, Integer messageId, String message, String userName, String createDate) {
		this.userId = userId;
		this.messageId = messageId;
		this.message = message;
		this.userName = userName;
		this.createDate = createDate;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
}