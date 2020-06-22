package com.example.springsocial.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import com.example.springsocial.util.Github;
import com.example.springsocial.util.SocialConfig;

/**
 * UserController handles requests to get user information.
 * @author Michael Muzio
 *
 */
@RestController
public class UserController {
	
	private static final String GITHUB_API_BASE_URL = "https://api.github.com";

    @Autowired
    private UserRepository userRepository;

    /**
     * Get the authenticated user
     * @param userPrincipal The current user
     * @return The user
     */
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
    	System.out.println("In User Controller");
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    /**
     * Use the GitHub API to get user data using OAuth token
     * @param request The request
     * @return A ResponseEntity with a message
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getCurrentUserGithub(HttpServletRequest request) {
    	String bearerToken = request.getHeader("Authorization");
    	HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		System.out.println(bearerToken);
		headers.set("Authorization", bearerToken);
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(GITHUB_API_BASE_URL + "/user", HttpMethod.GET, entity, String.class);
		System.out.println(result);
		return result;
    }
    
}
