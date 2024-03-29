package sjsu.cmpe275.Lab2.service;

import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SponsorRepository sponsorRepository;
    

    /*
	 * Implementation to create an instance of Player
	 * throws custom exception in case unique email is not provided
	 */
	@Override
	public Player createPlayer(String fName, String lName, String email, String description, String street, String city,
											String state, String zip, Long sponsor) throws CustomException
	{
		try{
			Player player = this.playerRepository.findByEmail(email);
	        if(player == null) player = new Player(fName,lName,email);
	        else
	        	throw new CustomException("Already registered email", HttpStatus.BAD_REQUEST);
	        player.setDescription(description);
	        player.setAddress(new Address(street,city,state,zip));
	        Sponsor sponsorObj = null;
	        if(sponsor != null)
	        sponsorObj = this.sponsorRepository.findOne(sponsor);
	        player.setSponsor(sponsorObj);
	        return this.playerRepository.save(player);
		}
		catch(CustomException ce)
		{
			System.err.println("Exception message: " + ce.getMessage());
			throw ce;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new CustomException("Server error", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	/*
	 * Implementation to retrieve an instance of Player
	 * throws custom exception in case player is not found
	 */
    @Override
	@Transactional(readOnly=true)
    public Player getPlayer(long id) throws CustomException
    {
    	try {
			Player player = this.playerRepository.findOne(id);
			if(player == null) {
				throw new CustomException("Player is not present",HttpStatus.NOT_FOUND);
			}
			return player;
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
	 * Implementation to update an instance of Player
	 * throws custom exception in case player is not found or unique email is not provided or mentioned sponsor not found 
	 */
    @Override
    public Player updatePlayer( long id, String fName, String lName, String email, String description,
            String street, String city, String state, String zip, Long sponsor) throws CustomException {
		Player player = null;
		Player playerTemp = null;
		try {
			playerTemp = this.playerRepository.findByEmail(email);
			player = this.playerRepository.findOne(id);
			if(player == null) {
				throw new CustomException("Player is not present",HttpStatus.NOT_FOUND);
			}
			else if(playerTemp != null && player.getId() != playerTemp.getId()) throw new CustomException("Already registered email", HttpStatus.BAD_REQUEST);
			player.setFirstName(fName);
			player.setLastname(lName);
			player.setEmail(email);
			player.setDescription(description);
	        player.setAddress(new Address(street,city,state,zip));
	        if(sponsor != null)
	        {
	        	Sponsor sponsorObj = this.sponsorRepository.findOne(sponsor);
	        	if(sponsorObj !=null)
		        	player.setSponsor(sponsorObj);
		        else
		        	throw new CustomException("Sponsor is not present", HttpStatus.BAD_REQUEST);
	        }
			return this.playerRepository.save(player);
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
     * @see sjsu.cmpe275.Lab2.service.PlayerService#deletePlayer(long)
     * Implementation to delete an instance of Player
	 * throws custom exception in case player is not found
     */
    @Override
    public Player deletePlayer(long id) throws CustomException
    {
    	Player player = null;
		try {
			player = this.playerRepository.findOne(id);
			if(player == null) {
				throw new CustomException("Player is not present",HttpStatus.NOT_FOUND);
			}
			else
			{
				Set<Player> opponents= player.getOpponents();
				Iterator<Player> it = opponents.iterator();
				while(it.hasNext())
				{
					Player op = it.next();
					op.getOpponents().remove(player);
				}
			}
			this.playerRepository.delete(id);
			return player;
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
     * @see sjsu.cmpe275.Lab2.service.PlayerService#addOpponents(long, long)
     * Implementation to make two players as opponents of each other
	 * throws custom exception in case player is not found
     */
    @Override
	public String addOpponents(long id1, long id2) throws CustomException
	{
		try{
			Player player1 = this.playerRepository.findOne(id1);
			Player player2 = this.playerRepository.findOne(id2);
			
			if(player1 != null && player2 != null)
			{
				if(player1.getOpponents().contains(player2) || player2.getOpponents().contains(player1))  
					return "Already Opponents";
				else
				{
					player1.getOpponents().add(player2);
					player2.getOpponents().add(player1);
					this.playerRepository.save(player1);
					this.playerRepository.save(player2);
					return "Opponents are Added";
				}
			}
			else
			{
				throw new CustomException("Player does not exists", HttpStatus.NOT_FOUND);
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
	
    /*
     * @see sjsu.cmpe275.Lab2.service.PlayerService#deleteOpponents(long, long)
     * Implementation to remove two players as opponents of each other
	 * throws custom exception in case player is not found or players are not opponent of each other
     */
	@Override
	public String deleteOpponents(long id1,long id2) throws CustomException
	{
		try{
			Player player1 = this.playerRepository.findOne(id1);
			Player player2 = this.playerRepository.findOne(id2);
			
			if(player1 != null && player2 != null)
			{
				if(!player1.getOpponents().contains(player2) || !player2.getOpponents().contains(player1))
					throw new CustomException("Players are not opponents", HttpStatus.NOT_FOUND);
				else
				{
					player1.getOpponents().remove(player2);
					player2.getOpponents().remove(player1);
					this.playerRepository.save(player1);
					this.playerRepository.save(player2);
					return "No more Opponents";
				}
			}
			else
			{
				throw new CustomException("Player does not exists", HttpStatus.NOT_FOUND);
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