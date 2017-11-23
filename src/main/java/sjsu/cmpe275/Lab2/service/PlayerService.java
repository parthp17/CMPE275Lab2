package sjsu.cmpe275.Lab2.service;

import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275Lab2.CustomException.CustomException;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */

/*
 * Player Services to perform actions on Player objects
 */
public interface PlayerService {

	/*
	 * method to create a n instance of Player
	 * throws custom exception in case unique constraints are violated
	 */
	Player createPlayer(String fName, String lName, String email, String description, String street, String city, String state, String zip, Long sponsor) throws CustomException;
	
	/*
	 * method to retrieve an instance of Player
	 * throws custom exception in case player is not found
	 */
	Player getPlayer(long id) throws CustomException;
	
	/*
	 * Method to update an instance of Player
	 * throws custom exception in case unique constraints are violated
	 */
	Player updatePlayer( long id, String fName, String lName, String email, String description,String street, String city, String state, String zip, Long sponsor) throws CustomException;
	
	/*
	 * Method to delete an instance of Player
	 * throws custom exception in case player is not found
	 */
	Player deletePlayer(long id) throws CustomException;
	
	/*
	 * Method to make two players as opponents of each other
	 * throws custom exception in case player is not found
	 */
	String addOpponents(long id1, long id2) throws CustomException;
	
	/*
     * Method to remove two players as opponents of each other
	 * throws custom exception in case player is not found or players are not opponent of each other
     */
	
	String deleteOpponents(long id1,long id2) throws CustomException;
}