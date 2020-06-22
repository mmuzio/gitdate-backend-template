package com.example.springsocial.util;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Github extends ApiBinding to provide authenticated access to the Github API.
 * @author Michael Muzio
 *
 */
public class Github extends ApiBinding {
	
	  private static final String GITHUB_API_BASE_URL =
	              "https://api.github.com";
	  
	  String accessToken;

	  public Github(String accessToken) {
	    super(accessToken);
	  }

	  /**
	   * Make an authenticated request to the Github API to get the authenticated user's profile
	   * @return ResponseEntity with a message
	   */
	  public ResponseEntity<String> getProfile() {
		
		// Set HttpHeaders with Accept and Authorization headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		System.out.println(accessToken);
		headers.set("Authorization", accessToken);
		
		// Prepare an HttpEntity for exchange with the RestTemplate
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		     
		// Make the call to /user endpoint of Github API to get user profile data
		ResponseEntity<String> result = restTemplate.exchange(GITHUB_API_BASE_URL + "/user", HttpMethod.GET, entity, String.class);
		System.out.println(result);
		return result;
		
	  }

	}
