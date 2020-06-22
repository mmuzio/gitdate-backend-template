package com.example.springsocial.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springsocial.model.Message;
import com.example.springsocial.monitoring.MonitoredDAO;
import com.example.springsocial.repository.MessageRepository;

@Component
@MonitoredDAO
public class MessageDAO {

	private static final Logger logger = LoggerFactory.getLogger(MessageDAO.class);
	
	private MessageRepository messageRepository;
	
	@Autowired
	public void setMessageRepository(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public Message getMessageById(Long id) {
				
		Message message = messageRepository.getOne(id);
		
        logger.debug("retrieved message: {}", message);
		
		return message;
		
	}
	
	public void insertMessage(Message message) {
		
        logger.debug("saving message: {}", message);
		
		messageRepository.save(message);
		
	}

	public void deleteMessage(Long id) {
		
        logger.debug("deleting message with id: {}", id);
		
		messageRepository.deleteById(id);
		
	}

	public void updateMessage(Message message) {
		
		messageRepository.save(message);
		
	}

	public List<Message> getAllMessages() {
		
		return messageRepository.findAll();
		
	}

	
}
