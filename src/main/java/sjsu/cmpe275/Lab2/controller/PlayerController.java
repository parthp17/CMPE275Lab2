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
import sjsu.cmpe275Lab2.CustomException.CustomException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kemy on 11/13/17.
 */

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private SponsorService sponsorService;
    
    @RequestMapping(method = RequestMethod.POST)
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
    		Player player = this.playerService.createPlayer(fName, lName, email, description, street, city, state, zip, sponsor);
        	return new ResponseEntity<Object>(player, HttpStatus.OK);		
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getPlayer(@PathVariable long id)
    {
    	try {
			Player player = playerService.getPlayer(id);
			return new ResponseEntity<Object>(player,HttpStatus.OK);
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
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
			player = playerService.updatePlayer(id, fName, lName, email, description, street, city, state, zip, sponsor);
			return new ResponseEntity<Object>(player,HttpStatus.OK);
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deletePlayer(@PathVariable long id)
    {
    	Player player = null;
		try {
			player = playerService.deletePlayer(id);
			return new ResponseEntity<Object>(player,HttpStatus.OK);
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
    }
}
