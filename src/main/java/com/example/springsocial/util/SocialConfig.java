package com.example.springsocial.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.context.annotation.RequestScope;

/**
 * Configure beans for accessing data from OAuth2 social providers
 * @author Michael Muzio
 *
 */
@Configuration
public class SocialConfig {

	/**
	 * Configure a bean for accessing data from Github
	 * @param clientService The OAuth2AuthorizedClientService
	 * @return The Github bean
	 */
	@Bean
	@RequestScope
	public Github github(OAuth2AuthorizedClientService clientService) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String accessToken = null;
		if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
			String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
			if (clientRegistrationId.equals("github")) {
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                    clientRegistrationId, oauthToken.getName());
				accessToken = client.getAccessToken().getTokenValue();
			}
		}
		return new Github(accessToken);
	}

}
