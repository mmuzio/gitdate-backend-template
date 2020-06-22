package com.example.springsocial.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.dao.UserDAO;
import com.example.springsocial.model.Match;
import com.example.springsocial.model.User;
import com.example.springsocial.monitoring.MonitoredService;

/**
 * UserService implementation. <br>
 * Used for common User operations.
 * @author Michael Muzio
 *
 */
@Service
@MonitoredService
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public User getUserByUsername(String username) {
		
		return userDAO.findByUsername(username);
		
	}

	@Override
	public void registerUser(User user) {
		
		userDAO.registerUser(user);
		
	}

	@Override
	public List<User> getUsersByMatch(Match match) {
		
		return match.getUsers();
		
	}

	@Override
	public boolean doesUserExist(String username) {
		
		return userDAO.doesUserExist(username);
		
	}

	@Override
	public List<User> getAll() {
		
		return userDAO.getAll();
		
	}

}
