package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.model.Sponsor;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment 2 submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */

/*
 * Player repository to operate on player table.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	/*
	 * find Players by sponsor 
	 */
	public Player findBySponsor(Sponsor sponsor);
	
	/*
	 * find a Players by its email. 
	 */
	public Player findByEmail(String email);
}
