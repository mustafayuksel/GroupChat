package com.mustafayuksel.groupchat.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mustafayuksel.groupchat.domain.Preference;

@Repository
public interface PreferenceRepository extends CrudRepository<Preference, Integer> {
	@Cacheable("findByMnemonic")
	Preference findByMnemonic(String mnemonic);
}