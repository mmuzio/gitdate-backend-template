package com.example.springsocial.service;

import java.util.List;

import com.example.springsocial.model.Match;
import com.example.springsocial.model.User;

/**
 * Interface for common User operations.
 * @author Michael Muzio
 *
 */
public interface UserService {

	/**
	 * Get a single user by username
	 * @param username The username
	 * @return The user
	 */
	public User getUserByUsername(String username);
	
	/**
	 * Check if a userwith a given username exists
	 * @param username The username
	 * @return boolean true if user exists
	 */
	public boolean doesUserExist(String username);
	
	/**
	 * Register a new user
	 * @param user The user
	 */
	public void registerUser(User user);
	
	/**
	 * Get users for a given match
	 * @param match The match
	 * @return A list of users
	 */
	public List<User> getUsersByMatch(Match match);
	
	/**
	 * Get all users
	 * @return A list of users
	 */
	public List<User> getAll();
	
}
