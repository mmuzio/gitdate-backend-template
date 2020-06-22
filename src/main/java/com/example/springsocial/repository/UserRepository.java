package com.example.springsocial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.User;

/**
 * The UserResposity extends JpaRepository to make advanced database queries with the User entity.
 * @author Michael Muzio
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Retrieve a user with given email
	 * @param email The email
	 * @return The user (optional)
	 */
    Optional<User> findByEmail(String email);
    
    /**
     * Retrieve a user with given username
     * @param username The username
     * @return The user (optional)
     */
    Optional<User> findByUsername(String username);

    /**
     * Check if a user with given email exists
     * @param email The email
     * @return Boolean true if user exists
     */
    Boolean existsByEmail(String email);
    
    /**
     * Check if a user with given username exists
     * @param username The username
     * @return Boolean true if user exists
     */
    Boolean existsByUsername(String username);

}
