package com.mustafayuksel.groupchat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mustafayuksel.groupchat.domain.Chat;
import com.mustafayuksel.groupchat.dto.Status;

@Repository
public interface ChatRepository extends CrudRepository<Chat, Integer> {
	List<Chat> findTop20ByStatusAndLanguageCodeOrderByCreateDateDesc(Status status, String languageCode);

	List<Chat> findAllByLanguageCodeOrderByCreateDateDesc(String languageCode);

	List<Chat> findAllByStatusAndLanguageCodeOrderByCreateDateDesc(Status status, String languageCode);

	List<Chat> findAllByUserId(String userId);

	void deleteAllByStatusAndLanguageCode(Status status, String languageCode);
}