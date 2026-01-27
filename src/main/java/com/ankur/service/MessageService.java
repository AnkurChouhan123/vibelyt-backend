package com.ankur.service;

import java.util.List;

import com.ankur.exception.ChatException;
import com.ankur.exception.MessageException;
import com.ankur.exception.UserException;
import com.ankur.model.Message;
import com.ankur.request.SendMessageRequest;

public interface MessageService  {
	
	public Message sendMessage(SendMessageRequest req) throws UserException, ChatException;
	
	public List<Message> getChatsMessages(Integer chatId) throws ChatException;
	
	public Message findMessageById(Integer messageId) throws MessageException;
	
	public String deleteMessage(Integer messageId) throws MessageException;

}
