/**
 * 
 */
package sjsu.cmpe275.Lab2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import sjsu.cmpe275.Lab2.model.Address;
import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.model.Sponsor;
import sjsu.cmpe275.Lab2.repositories.PlayerRepository;
import sjsu.cmpe275.Lab2.repositories.SponsorRepository;
import sjsu.cmpe275Lab2.CustomException.CustomException;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */

@Service
@Transactional(propagation=Propagation.REQUIRED, rollbackFor = Exception.class)
public class SponsorServiceImpl implements SponsorService {

	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	/*
	 * (non-Javadoc)
	 * @see sjsu.cmpe275.Lab2.service.SponsorService#createSponsor(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Implementation to create an instance of Sponsor
	 */
	
	@Override
	public Sponsor createSponsor(String name, String description, String street, String city, String state, String zip) throws CustomException{
		try {
			Sponsor newSponsor = new Sponsor();
			newSponsor.setName(name);
			newSponsor.setDescription(description);
			newSponsor.setAddress(new Address(street,city,state,zip));
			return this.sponsorRepository.save(newSponsor);		
		}
    	catch(Exception e) {
			e.printStackTrace();
			throw new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see sjsu.cmpe275.Lab2.service.SponsorService#getSponsor(long)
	 * 
	 * Implementation to retrieve an instance of Sponsor
	 * throws custom exception in case Sponsor is not found
	 */
	@Override
	@Transactional(readOnly=true)
	public Sponsor getSponsor(long id) throws CustomException{
		try {
			Sponsor sponsor = this.sponsorRepository.findOne(id);
			if(sponsor == null) {
				throw new CustomException("Sponsor not found", HttpStatus.NOT_FOUND);
			}
			return sponsor;
		}catch(CustomException ce){
			System.err.println("Exception message: " + ce.getMessage());
			throw ce;
		}
    	catch(Exception e) {
			e.printStackTrace();
			throw new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see sjsu.cmpe275.Lab2.service.SponsorService#updateSponsor(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Implementation to update an instance of Sponsor
	 * throws custom exception if sponsor not found.
	 * 
	 */
	
	@Override
	public Sponsor updateSponsor( long id, String name, String description, String street, String city, String state, String zip) throws CustomException{
		Sponsor sponsor = null;
		try {
			sponsor = this.sponsorRepository.findOne(id);
			if(sponsor == null) {
				throw new CustomException("Sponsor not found", HttpStatus.NOT_FOUND);
			}
			sponsor.setName(name);
			sponsor.setDescription(description);
			sponsor.setAddress(new Address(street,city,state,zip));
			return this.sponsorRepository.save(sponsor);
		}catch(CustomException ce){
			System.err.println("Exception message: " + ce.getMessage());
			throw ce;
		}
    	catch(Exception e) {
			e.printStackTrace();
			throw new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see sjsu.cmpe275.Lab2.service.SponsorService#deleteSponsor(long)
	 * Implementation to delete an instance of Sponsor
	 * throws custom exception if sponsor not found or if it still sponsors other players
	 */
	
	@Override
	public Sponsor deleteSponsor(long id) throws CustomException{
		Sponsor sponsor = null;
		try {
			sponsor = this.sponsorRepository.findOne(id);
			if(sponsor == null) {
				throw new CustomException("Sponsor not found", HttpStatus.NOT_FOUND);
			}
			Player player = this.playerRepository.findBySponsor(sponsor);
			if(player == null) 
			{
				this.sponsorRepository.delete(id);
				return sponsor;
			}
			else
			{
				throw new CustomException("Sponsor cannot be deleted! It still sponsors players.", HttpStatus.BAD_REQUEST);
			}
		}catch(CustomException ce){
			System.err.println("Exception message: " + ce.getMessage());
			throw ce;
		}
    	catch(Exception e) {
			e.printStackTrace();
			throw new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
