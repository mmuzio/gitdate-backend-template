package com.example.springsocial.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springsocial.dao.MatchDAO;
import com.example.springsocial.dao.UserDAO;
import com.example.springsocial.model.DisplayMatch;
import com.example.springsocial.model.Match;
import com.example.springsocial.model.User;
import com.example.springsocial.monitoring.MonitoredService;

/**
 * MatchService implementation. <br>
 * Used for common match operations.
 * @author Michael Muzio
 *
 */
@Service
@MonitoredService
public class MatchServiceImpl implements MatchService {

	private MatchDAO matchDao;
	
	private UserDAO userDao;
	
	@Autowired
	public void setMatchDAO(MatchDAO matchDao) {
		this.matchDao = matchDao;
	}
	
	@Autowired
	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public Match getMatchById(Long id) {
		
		return matchDao.getMatchById(id);
		
	}

	@Override
	public List<DisplayMatch> getMatchesByUser(User currentUser) {
		
		// Get the user's matches, whether or not they are confirmed or awaiting acceptance by the other user
		List<Match> matches = currentUser.getMatches();
		
		List<DisplayMatch> displayMatches = new ArrayList<DisplayMatch>();
		
		// For each match, check if it has been accepted by the other user
		// If it has, add to list of displayMatches
		for (Match match: matches) {
			if (match.getIsAccepted()) {
				for (User user: match.getUsers()) {
					if (!user.getUsername().equals(currentUser.getUsername())) {
						displayMatches.add(new DisplayMatch(match.getId(), user.getUsername()));
					}
				}
			}
		}
		return displayMatches;
		
	}

	@Override
	public List<Match> getAllMatches() {
		
		return matchDao.getAllMatches();
		
	}

	@Override
	public void updateMatch(Match match) {
		
		matchDao.save(match);
		
	}

	@Override
	public void deleteMatch(Long id) {
		
		matchDao.deleteMatch(id);
		
	}

	@Override
	public Match insertMatch(User currentUser, String username) {

		// Get the user you want to match with
		User requestedUser = userDao.findByUsername(username);
		
		// Get the matches of the user you want to match with
		List<Match> matchesByRequestedUser = requestedUser.getMatches();
		
		// For each match by the requested user, get the users in that match
		// If the current user belongs to a match of the requested user, 
		// that match should now contain both users and be accepted, and that 
		// match should be added to the current user
		for (Match match: matchesByRequestedUser) {
			for (User user: match.getUsers()) {
				if (user.getUsername().equals(currentUser.getUsername())) {
					match.setIsAccepted(true);
					currentUser.addMatch(match);
					Match newMatch = matchDao.saveAndFlush(match);
					return newMatch;
				}
			}
		}
		
		// If no match is found and set to accepted, create a new match 
		// initiated by the current user and set isAccepted to false
		Match match = new Match(currentUser.getUsername(), false);
		
		// Add the current user and requested user to the match
		match.addUser(currentUser);
		match.addUser(requestedUser);
		
		// Add the match to the current user
		currentUser.addMatch(match);
		
		// Persist the match
		Match newMatch = matchDao.saveAndFlush(match);
		
		return newMatch;
		
	}

}
