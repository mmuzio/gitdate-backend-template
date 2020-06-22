package com.example.springsocial.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.DisplayMessage;
import com.example.springsocial.model.Match;
import com.example.springsocial.model.Message;
import com.example.springsocial.model.RequestMessage;
import com.example.springsocial.model.User;
import com.example.springsocial.service.MatchService;
import com.example.springsocial.service.MessageService;
import com.example.springsocial.service.UserService;

/**
 * MessageController is used to create, get, update, and delete messages.
 * @author Michael Muzio
 *
 */
@CrossOrigin("*")
@RestController
public class MessageController {
	
	private MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	private MatchService matchService;

	@Autowired
	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}
	
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * Get a single message
	 * @param id The message ID
	 * @return The message
	 */
	@GetMapping(path = "/message/{id}")
	@PreAuthorize("hasRole('USER')")
	public Message getMessage(@PathVariable Long id) {
		Message message = messageService.getMessageById(id);
		System.out.println(message);
		return message;
	}

	/**
	 * Get all messages <br>
	 * TO-DO: Delete this or change authorization role to ADMIN
	 * @return A list of messages
	 */
	@GetMapping(path = "/message")
	@PreAuthorize("hasRole('USER')")
	public List<Message> getAllMessages() {
		return messageService.getAllMessages();
	}
	
	/**
	 * Get messages belonging to a match and convert to list of DisplayMessage 
	 * @param match_id
	 * @return A list of DisplayMessage
	 */
	@GetMapping(path = "/message/match")
	@PreAuthorize("hasRole('USER')")
	public List<DisplayMessage> getMessagesByCurrentMatch(@RequestParam Long match_id) {		
		
		// Get all messages belonging to the match
		List<Message> messageList = messageService.getMessagesByMatch(match_id);
		
		List<DisplayMessage> displayMessageList = new ArrayList<DisplayMessage>();
		
		// For each Message in messageList, convert to a DisplayMessage and add to displayMessageList
		for (int i = 0; i < messageList.size(); i++) {
			Message message = messageList.get(i);
			String messageBody = message.getMessageBody();
			Date submitTime = message.getSubmitTime();
			User messageSender = message.getMessageSender();
			String username = messageSender.getUsername();
			DisplayMessage displayMessage = new DisplayMessage(messageBody, submitTime, username);
			displayMessageList.add(displayMessage);
			
		}
		
		// Return list of DisplayMessage
		return displayMessageList;
		
	}
	
	/**
	 * Update a message
	 * @param message The message
	 */
	@PutMapping(path = "/message")
	@PreAuthorize("hasRole('USER')")
	public void updateMessage(@RequestBody Message message) {
		messageService.updateMessage(message);
	}
	
	/**
	 * Delete a message
	 * @param id The message ID
	 */
	@DeleteMapping(path = "/message/{id}")
	@PreAuthorize("hasRole('USER')")
	public void deleteMessage(@PathVariable Long id) {
		messageService.deleteMessage(id);
	}
	
	/**
	 * Add a new message
	 * @param message The message
	 * @return A ResponseEntity with HttpStatus code
	 */
	@PostMapping(path = "/message")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<HttpStatus> addMessage(@RequestBody RequestMessage message) {
		
		// Get the username of the message sender
		User messageSender = userService.getUserByUsername(message.getUsername());
		
		// Get the match associated with the message
		Match match = matchService.getMatchById(message.getMatch_id());
		
		// Create a new match with data from the RequestMessage and add the User and Match
		Message newMessage = new Message(message.getMatch_id(),
				message.getMessageBody(), messageSender, match, message.getSubmitTime());
		
		// Persist the message
		messageService.insertMessage(newMessage);
		
		// Return a 201 CREATED response code
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}

}
