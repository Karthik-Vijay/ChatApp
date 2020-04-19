package com.sk.chatapp.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.chatapp.model.Message;
import com.sk.chatapp.service.MessageService;

@RestController
@RequestMapping("/messages")
public class MessageController {

	@Autowired
	MessageService messageService;
	
	@GetMapping("/{toUser}")
	public List<Message> selectAll(@PathVariable String toUser,Principal principal){
		
		String fromUser = principal.getName();
		
		return messageService.selectAll(fromUser, toUser);
	}
	
}
