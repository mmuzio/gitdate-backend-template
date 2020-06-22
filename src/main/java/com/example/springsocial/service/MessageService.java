package com.example.springsocial.service;

import java.util.List;

import com.example.springsocial.model.Message;

/**
 * Interface for common message operations.
 * @author Michael Muzio
 *
 */
public interface MessageService {
	
	/**
	 * Insert a new message
	 * @param message The message
	 */
	public void insertMessage(Message message);
	
	/**
	 * Get a single message 
	 * @param id The message ID
	 * @return The message
	 */
	public Message getMessageById(Long id);
	
	/**
	 * Get all messages for a given match
	 * @param id The match ID
	 * @return A list of messages
	 */
	public List<Message> getMessagesByMatch(Long id);

	/**
	 * Delete a single message
	 * @param id The ID of the message
	 */
	public void deleteMessage(Long id);

	/**
	 * Update a message
	 * @param message The message
	 */
	public void updateMessage(Message message);

	/**
	 * Get all messages
	 * @return A list of messages
	 */
	public List<Message> getAllMessages();

}
