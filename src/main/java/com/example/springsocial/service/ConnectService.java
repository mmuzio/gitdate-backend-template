package com.example.springsocial.service;

/**
 * Interface for common connect operations.
 * @author Michael Muzio
 *
 */
public interface ConnectService {

	/**
	 * Gets the username of a potential connection
	 * @param currentUser The authenticated user
	 * @return The username
	 */
	public String getRandomUsername(String currentUser);
	
}
