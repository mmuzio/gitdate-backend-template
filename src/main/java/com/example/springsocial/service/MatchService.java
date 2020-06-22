package com.example.springsocial.service;

import java.util.List;

import com.example.springsocial.model.DisplayMatch;
import com.example.springsocial.model.Match;
import com.example.springsocial.model.User;

/**
 * Interface for common Match operations.
 * @author Michael Muzio
 *
 */
public interface MatchService {
	
	/**
	 * Get a single match
	 * @param id The match ID
	 * @return The match
	 */
	public Match getMatchById(Long id);
	
	/**
	 * Get all matches by the current user
	 * @param currentUser The authenticated user
	 * @return A list of DisplayMatch
	 */
	public List<DisplayMatch> getMatchesByUser(User currentUser);

	/**
	 * Get all matches
	 * @return A list of Match
	 */
	public List<Match> getAllMatches();

	/**
	 * Update a match
	 * @param match The match
	 */
	public void updateMatch(Match match);

	/**
	 * Delete a match
	 * @param id The match ID
	 */
	public void deleteMatch(Long id);
	
	/**
	 * Insert a new match
	 * @param currentUser The authenticated user
	 * @param username The username of the user to match with
	 * @return The match
	 */
	public Match insertMatch(User currentUser, String username);

}
