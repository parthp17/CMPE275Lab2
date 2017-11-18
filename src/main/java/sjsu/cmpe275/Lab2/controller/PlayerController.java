package sjsu.cmpe275.Lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import sjsu.cmpe275.Lab2.model.Address;
import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.model.Sponsor;
import sjsu.cmpe275.Lab2.service.PlayerService;
import sjsu.cmpe275.Lab2.service.SponsorService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kemy on 11/13/17.
 */

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SponsorService sponsorService;
    
    @RequestMapping(value = "/player", method = RequestMethod.POST)
    public ResponseEntity<Object> createPlayer(@RequestParam(value = "firstname", required = true) String fName,
    		@RequestParam(value = "lastname", required = true) String lName,
    		@RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip,
            @RequestParam(value= "sponsor", required = false) Long sponsor)
    {
    	try {
	        Player player = new Player(fName,lName,email);
	        player.setDescription(description);
	        player.setAddress(new Address(street,city,state,zip));
	        Sponsor sponsorObj = null;
	        if(sponsor != null)
	        sponsorObj = sponsorService.findOne(sponsor);
	        player.setSponsor(sponsorObj);
	        playerService.save(player);
        	return new ResponseEntity<Object>(player, HttpStatus.OK);		
		}catch(Exception e) {
			return new ResponseEntity<Object>("Invalid Request",HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPlayer(@PathVariable long id)
    {
    	try {
			Player player = playerService.findOne(id);
			if(player == null) {
				return new ResponseEntity<Object>("Player is not present",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(player,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>("bad request",HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.POST)
    public ResponseEntity<Object> updatePlayer(@PathVariable("id") long id,
    		@RequestParam(value = "firstname", required = true) String fName,
    		@RequestParam(value = "lastname", required = true) String lName,
    		@RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip,
            @RequestParam(value= "sponsor", required = false) Long sponsor) {
		Player player = null;
		try {
			player = playerService.findOne(id);
			if(player == null) {
				return new ResponseEntity<Object>("Player is not present", HttpStatus.NOT_FOUND);
			}
			player.setFirstName(fName);
			player.setLastname(lName);
			player.setEmail(email);
			player.setDescription(description);
	        player.setAddress(new Address(street,city,state,zip));
	        Sponsor sponsorObj = sponsorService.findOne(sponsor);
	        player.setSponsor(sponsorObj);
	        playerService.save(player);
			return new ResponseEntity<Object>(player,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>("Required Parameters are not given", HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/player/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlayer(@PathVariable long id)
    {
    	Player player = null;
		try {
			player = playerService.findOne(id);
			if(player == null) {
				return new ResponseEntity<Object>("Player is not present", HttpStatus.NOT_FOUND);
			}
			playerService.delete(id);
			return new ResponseEntity<Object>(player,HttpStatus.OK);

		}
		catch(Exception e) {
			return new ResponseEntity<Object>("Invalid request",HttpStatus.BAD_REQUEST);
		}	
    }
}
