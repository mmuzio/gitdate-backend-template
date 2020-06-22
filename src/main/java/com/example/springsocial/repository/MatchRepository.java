package com.example.springsocial.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsocial.model.Match;

/**
 * MatchResposity extends JpaRepository to make advanced database queries with the Match entity.
 * @author Michael Muzio
 *
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
