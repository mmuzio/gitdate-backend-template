package com.example.springsocial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.dao.MatchDAO;
import com.example.springsocial.dao.MessageDAO;
import com.example.springsocial.model.Message;
import com.example.springsocial.monitoring.MonitoredService;

/**
 * MessageService implementation. <br>
 * Used for common message operations.
 * @author Michael Muzio
 *
 */
@Service
@MonitoredService
public class MessageServiceImpl implements MessageService {
	
	private MatchDAO matchDAO;
	
	@Autowired
	public void setMatchDAO(MatchDAO matchDAO) {
		this.matchDAO = matchDAO;
	}
	
	private MessageDAO messageDAO;
	
	@Autowired
	public void setMessageDAO(MessageDAO messageDAO) {
		this.messageDAO = messageDAO;
	}

	@Override
	public Message getMessageById(Long id) {
		
		return messageDAO.getMessageById(id);
		
	}

	@Override
	public List<Message> getMessagesByMatch(Long id) {
		return matchDAO.getMatchById(id).getMessages();
	}
	
	@Override
	public void insertMessage(Message message) {
		
		messageDAO.insertMessage(message);
		
	}

	@Override
	public void deleteMessage(Long id) {
		
		messageDAO.deleteMessage(id);
		
	}

	@Override
	public void updateMessage(Message message) {
		
		messageDAO.updateMessage(message);
		
	}

	@Override
	public List<Message> getAllMessages() {
		
		return messageDAO.getAllMessages();
		
	}

}
