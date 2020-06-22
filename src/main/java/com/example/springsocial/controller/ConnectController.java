package com.example.springsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.ConnectService;

/**
 * ConnectController handles connection requests and returns usernames 
 * of potential users for connection. <br><br>
 * Liking and disliking of potential matches 
 * is handled by the MatchController.
 * @author Michael Muzio
 *
 */
@CrossOrigin("*")
@RestController
public class ConnectController {
	
	private ConnectService connectService;

	@Autowired
	public void setConnectService(ConnectService connectService) {
		this.connectService = connectService;
	}
	
	/**
	 * The UserRepository is autowired to inject a UserPrincipal as the current user
	 */
	@Autowired
    private UserRepository userRepository;
	
	/**
	 * Gets the username of a random user
	 * @param userPrincipal The current user
	 * @return The username of a random user
	 */
	@GetMapping(path = "/connect")
	@PreAuthorize("hasRole('USER')")
	public String getRandomUsername(@CurrentUser UserPrincipal userPrincipal) {
		User user = userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
		String currentUser = user.getUsername();
		return connectService.getRandomUsername(currentUser);
		
	}
	
	
}
