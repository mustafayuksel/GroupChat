package com.mustafayuksel.groupchat.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mustafayuksel.groupchat.domain.BannedUser;

@Repository
public interface BannedUserRepository extends CrudRepository<BannedUser, Integer> {
	List<BannedUser> findByUserId(String userId);
}