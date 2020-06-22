package com.example.springsocial.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springsocial.model.DisplayMatch;
import com.example.springsocial.model.Match;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.service.MatchService;
import com.example.springsocial.service.UserService;

/**
 * MatchController is used to create, get, update, and delete matches.
 * @author Michael Muzio
 *
 */
@CrossOrigin("*")
@RestController
public class MatchController {
	
	private UserService userService;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private MatchService matchService;

	@Autowired
	public void setMatchService(MatchService matchService) {
		this.matchService = matchService;
	}
	
	/**
	 * The UserRepository is autowired to inject a UserPrincipal as the current user
	 */
	@Autowired
    private UserRepository userRepository;
	
	/**
	 * Get a single match
	 * @param id The match ID
	 * @return The match
	 */
	@GetMapping(path = "/match/{id}")
	@PreAuthorize("hasRole('USER')")
	public Match getMatch(@PathVariable Long id) {
		
		return matchService.getMatchById(id);
		
	}

	/**
	 * Get all matches. <br>
	 * TO-DO: Delete this, or change role to ADMIN
	 * @return A list of matches
	 */
	@GetMapping(path = "/match")
	@PreAuthorize("hasRole('USER')")
	public List<Match> getAllMatches() {
		return matchService.getAllMatches();
	}
	
	/**
	 * Get a list of the current user's matches
	 * @param userPrincipal The current user
	 * @return A list of the current user's matches
	 */
	@GetMapping(path = "/match/user")
	@PreAuthorize("hasRole('USER')")
	public List<DisplayMatch> getMatchesByCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
		
		// Get the current user
		User currentUser = userRepository.findById(userPrincipal.getId()).orElse(null);
		
		// Get the current user's matches
		return matchService.getMatchesByUser(currentUser);
		
	}
	
	/**
	 * Adds a user with given username to a match with given ID. <br>
	 * TO-DO: Delete this or make use of it
	 * @param id The match ID
	 * @param username The user's username
	 */
	@PostMapping(path = "/match/user")
	@PreAuthorize("hasRole('USER')")
	public void addUserToMatchByIdAndUsername(@RequestParam Long id, @RequestParam String username) {
		
		Match match = matchService.getMatchById(id);
		
		User user = userService.getUserByUsername(username);
		
		match.addUser(user);
		
		matchService.updateMatch(match);
		
	}
	
	/**
	 * Update a match
	 * @param match The match to update
	 */
	@PutMapping(path = "/match")
	@PreAuthorize("hasRole('USER')")
	public void updateMatch(@RequestBody Match match) {
		matchService.updateMatch(match);
	}
	
	/**
	 * Delete a match
	 * @param id The ID of the match
	 */
	@DeleteMapping(path = "/match/{id}")
	@PreAuthorize("hasRole('USER')")
	public void deleteMatch(@PathVariable Long id) {
		matchService.deleteMatch(id);
	}
	
	/**
	 * Add a new match containing the current user and the user corresponding 
	 * to the given username
	 * @param userPrincipal The current user
	 * @param username The username of the user to match
	 * @return The match
	 */
	@PostMapping(path = "/match")
	@PreAuthorize("hasRole('USER')")
	public @ResponseBody Match addMatch(@CurrentUser UserPrincipal userPrincipal, @RequestBody String username) {
				
		User currentUser = userRepository.findById(userPrincipal.getId()).orElse(null);
		
		Match newMatch = matchService.insertMatch(currentUser, username);
		
		return newMatch;
		
	}

}
