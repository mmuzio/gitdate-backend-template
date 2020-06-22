package com.example.springsocial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.dao.UserDAO;
import com.example.springsocial.model.User;
import com.example.springsocial.monitoring.MonitoredService;

/**
 * Connect service implementation. <br>
 * Used to get new users to connect with.
 * @author Michael Muzio
 *
 */
@Service
@MonitoredService
public class ConnectServiceImpl implements ConnectService {

	@Autowired
    private UserDAO userDao;
	
	@Override
	public String getRandomUsername(String currentUser) {
		List<User> users = new ArrayList<User>();
		// Get all users
		users = userDao.findAll();
		boolean isCurrentUser = true;
		String username = "";
		// Find a random user and check if that user is the current user
		// If not, it is a potential connection, so break the loop and 
		// return the username
		while (isCurrentUser) {
			int randomIndex = (int) (Math.random() * users.size());
			username = users.get(randomIndex).getUsername();
			if (!username.equals(currentUser)) {
				isCurrentUser = false;
			} else {
				continue;
			}
		}
		return username;
	}

}
