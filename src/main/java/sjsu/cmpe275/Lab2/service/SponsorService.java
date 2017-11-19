package sjsu.cmpe275.Lab2.service;

import sjsu.cmpe275.Lab2.model.Sponsor;
import sjsu.cmpe275Lab2.CustomException.CustomException;

public interface SponsorService {
	Sponsor createSponsor(String name, String description, String street, String city, String state, String zip) throws CustomException;
	Sponsor getSponsor(long id) throws CustomException;
	Sponsor updateSponsor( long id, String name, String description, String street, String city, String state, String zip) throws CustomException;
	Sponsor deleteSponsor(long id) throws CustomException;
}