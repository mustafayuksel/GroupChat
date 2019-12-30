package com.mustafayuksel.groupchat.domain;

import javax.persistence.Entity;

@Entity
public class Preference extends BaseEntity {
	private String name;
	private String mnemonic;
	private Integer value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMnemonic() {
		return mnemonic;
	}

	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}