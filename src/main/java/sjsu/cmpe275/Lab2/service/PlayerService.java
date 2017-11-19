package sjsu.cmpe275.Lab2.service;

import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275Lab2.CustomException.CustomException;

public interface PlayerService {

	Player createPlayer(String fName, String lName, String email, String description, String street, String city, String state, String zip, Long sponsor) throws CustomException;
	Player getPlayer(long id) throws CustomException;
	Player updatePlayer( long id, String fName, String lName, String email, String description,String street, String city, String state, String zip, Long sponsor) throws CustomException;
	Player deletePlayer(long id) throws CustomException;
	String addOpponents(long id1, long id2) throws CustomException;
	String deleteOpponents(long id1,long id2) throws CustomException;
}