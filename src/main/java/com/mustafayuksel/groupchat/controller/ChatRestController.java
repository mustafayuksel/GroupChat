package com.mustafayuksel.groupchat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mustafayuksel.groupchat.request.CreateChatDetailRequest;
import com.mustafayuksel.groupchat.response.BaseResponse;
import com.mustafayuksel.groupchat.response.ListChatDetailsResponse;
import com.mustafayuksel.groupchat.service.BannedService;
import com.mustafayuksel.groupchat.service.ChatService;

@RestController
@RequestMapping("/chat")
public class ChatRestController {
	private final ChatService chatService;
	private final BannedService bannedService;

	@Autowired
	public ChatRestController(ChatService chatService, BannedService bannedService) {
		this.chatService = chatService;
		this.bannedService = bannedService;
	}

	@GetMapping(value = "/")
	public ListChatDetailsResponse getMessages(@RequestParam("languageCode") String languageCode) {
		return chatService.getMessages(languageCode);
	}

	@PostMapping(value = "/")
	public ListChatDetailsResponse sendMessage(@RequestBody CreateChatDetailRequest createChatDetailRequest) {
		return chatService.createMessage(createChatDetailRequest);
	}

	@PostMapping(value = "/complain/{userId}")
	public BaseResponse complainUser(@PathVariable("userId") String userId) {
		try {
			bannedService.sendBannedUserMessageViaEmail(userId);
		} catch (Exception ex) {

		}
		return new BaseResponse("", true);
	}

	@GetMapping(value = "/banuser")
	public BaseResponse banUser(@RequestParam("userId") String userId) {
		return bannedService.save(userId);
	}

	@GetMapping(value = "/all")
	public ListChatDetailsResponse getAllMessages(@RequestParam("languageCode") String languageCode) {
		return chatService.getMessages(languageCode);
	}

	@DeleteMapping(value = "/")
	public BaseResponse deleteAllMessages(@RequestParam("languageCode") String languageCode) {
		return chatService.deleteAll(languageCode);
	}

	@DeleteMapping(value = "/{messageId}")
	public BaseResponse deleteMessage(@PathVariable("messageId") Integer messageId) {
		return chatService.delete(messageId);
	}

	@DeleteMapping(value = "banuser/{userId}")
	public BaseResponse deleteMessage(@PathVariable("userId") String userId) {
		return bannedService.delete(userId);
	}
}