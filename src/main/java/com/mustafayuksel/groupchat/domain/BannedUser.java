package com.mustafayuksel.groupchat.domain;

import javax.persistence.Entity;

@Entity
public class BannedUser extends BaseEntity {
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BannedUser() {
	}

	public BannedUser(String userId) {
		super();
		this.userId = userId;
	}
}