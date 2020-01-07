package com.mustafayuksel.groupchat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mustafayuksel.groupchat.domain.Preference;
import com.mustafayuksel.groupchat.repository.PreferenceRepository;
import com.mustafayuksel.groupchat.response.BaseResponse;
import com.mustafayuksel.groupchat.service.PreferenceService;

@Service
public class PreferenceServiceImpl implements PreferenceService {

	private static final String IS_CHAT_OPEN = "IS_CHAT_OPEN";
	private final PreferenceRepository preferenceRepository;

	@Autowired
	public PreferenceServiceImpl(PreferenceRepository preferenceRepository) {
		this.preferenceRepository = preferenceRepository;
	}

	@Override
	public Boolean isChatOpen() {
		return preferenceRepository.findByMnemonic(IS_CHAT_OPEN).getValue() > 0;
	}

	@Override
	public BaseResponse updateChatFlag(String flag) {
		Preference preference = preferenceRepository.findByMnemonic(IS_CHAT_OPEN);
		preference.setValue("true".equalsIgnoreCase(flag) ? 1 : 0);
		preferenceRepository.save(preference);
		return new BaseResponse("Updated Successfully!", true);
	}
}