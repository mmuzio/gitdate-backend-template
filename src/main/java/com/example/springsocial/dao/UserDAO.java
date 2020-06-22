package com.example.springsocial.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springsocial.model.User;
import com.example.springsocial.monitoring.MonitoredDAO;
import com.example.springsocial.repository.UserRepository;

@Component
@MonitoredDAO
public class UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
	
	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findAll() {
		
        logger.debug("Retrieving all users");
        
		return userRepository.findAll();
		
	}
	
	public User findByUsername(String username) {
		
		User user = userRepository.findByUsername(username).orElse(null);
		
        logger.debug("Retrieving user with username: {}", username);
        
        if (user == null) {
        	
            logger.warn("User not found");
            
        } else {
        	
            logger.debug("Found user: {}", user);
            
        }
        
		return user;
		
	}

	public void registerUser(User user) {
		
        logger.debug("saving user: {}", user);
		
		userRepository.save(user);
		
	}

	public boolean doesUserExist(String username) {
		
		boolean doesUserExist = userRepository.existsByUsername(username);
		
        logger.debug("Checking if user with username: {} exists", username);
        
        if (doesUserExist == true) {
        	
            logger.debug("Found user with username: {}", username);
            
        } else {
        	
            logger.warn("No user with username: {} exists", username);
            
        }

		return doesUserExist;
		
	}

	public List<User> getAll() {
		
        logger.debug("getting all users");
		
		return userRepository.findAll();
		
	}


}
