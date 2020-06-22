package com.example.springsocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.example.springsocial.config.AppProperties;

/**
 * The application entry point.
 * @author Michael Muzio
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.springsocial"})
@EnableConfigurationProperties(AppProperties.class)
public class SpringSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSocialApplication.class, args);
	}
}
