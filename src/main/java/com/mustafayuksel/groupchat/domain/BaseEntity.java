package com.mustafayuksel.groupchat.domain;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.mustafayuksel.groupchat.dto.Status;

@MappedSuperclass
public class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Version
	private Long version;
	private LocalDateTime createDate;

	@Enumerated
	private Status status;

	public BaseEntity() {
		this.createDate = LocalDateTime.now(ZoneId.of("Europe/Istanbul"));
		this.status = Status.ACTIVE;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}