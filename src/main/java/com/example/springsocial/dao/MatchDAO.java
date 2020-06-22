package com.example.springsocial.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springsocial.model.Match;
import com.example.springsocial.monitoring.MonitoredDAO;
import com.example.springsocial.repository.MatchRepository;

@Component
@MonitoredDAO
public class MatchDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(MatchDAO.class);
	
	private MatchRepository matchRepository;
	
	@Autowired
	public void setMatchRepository(MatchRepository matchRepository) {
		this.matchRepository = matchRepository;
	}
	
	public Match getMatchById(Long id) {
		
		Match match = matchRepository.getOne(id);
		
        logger.debug("retrieved match: {}", match);
		
		return match;
		
	}
	
	public void save(Match match) {
		
        logger.debug("saving match: {}", match);
        
        matchRepository.save(match);
        
    }
	
	public Match saveAndFlush(Match match) {
		
        logger.debug("save and flush match: {}", match);
        
        return matchRepository.saveAndFlush(match);
        
    }
	
	public List<Match> getAllMatches() {
		
        logger.debug("getting all matches");

		return matchRepository.findAll();
		
	}
	
	public void deleteMatch(Long id) {
		
        logger.debug("deleting match with id: {}", id);

		matchRepository.deleteById(id);
		
	}

}
