package com.sk.chatapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.chatapp.dao.MessageDao;
import com.sk.chatapp.model.Message;

@Service
public class MessageService {

	@Autowired
	MessageDao messageDao;
	
	public List<Message> selectAll(String fromUser,String toUser){
		
		return messageDao.selectAll(fromUser, toUser);
	}
}
