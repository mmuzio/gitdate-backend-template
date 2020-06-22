package com.example.springsocial.model;

/**
 * DisplayMatch class used for displaying matches to users. <br>
 * Could potentially be the superclass of Match.
 * @author Michael Muzio
 *
 */
public class DisplayMatch {
	
	private Long match_id;
	
	private String username;

	public DisplayMatch() {
		super();
	}

	public DisplayMatch(Long match_id, String username) {
		super();
		this.match_id = match_id;
		this.username = username;
	}

	public Long getMatch_id() {
		return match_id;
	}

	public void setMatch_id(Long match_id) {
		this.match_id = match_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	

}
