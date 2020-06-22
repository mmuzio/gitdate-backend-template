package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Message;

/**
 * MessageResposity extends JpaRepository to make advanced database queries with the Message entity.
 * @author Michael Muzio
 *
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
}
