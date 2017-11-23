package sjsu.cmpe275.Lab2.service;

import sjsu.cmpe275.Lab2.model.Sponsor;
import sjsu.cmpe275Lab2.CustomException.CustomException;
/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */

public interface SponsorService {

	
	/*
	 * Method to create an instance of Sponsor
	 */
	Sponsor createSponsor(String name, String description, String street, String city, String state, String zip) throws CustomException;
	
	/*
	 * Method to retrieve an instance of Sponsor
	 * throws custom exception in case Sponsor is not found
	 */
	Sponsor getSponsor(long id) throws CustomException;
	
	/*
	 * Method to update an instance of sponsor
	 * throws custom exception in case sponsor is not found
	 */
	Sponsor updateSponsor( long id, String name, String description, String street, String city, String state, String zip) throws CustomException;
	
	/*
	 * Method to delete an instance of sponsor
	 * throws custom exception in case sponsor is not found or it sponsors other players
	 */
	Sponsor deleteSponsor(long id) throws CustomException;
}