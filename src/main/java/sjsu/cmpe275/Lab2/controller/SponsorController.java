package sjsu.cmpe275.Lab2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sjsu.cmpe275.Lab2.model.Address;
import sjsu.cmpe275.Lab2.model.Player;
import sjsu.cmpe275.Lab2.model.Sponsor;
import sjsu.cmpe275.Lab2.service.PlayerService;
import sjsu.cmpe275.Lab2.service.SponsorService;
import sjsu.cmpe275Lab2.CustomException.CustomException;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {
	
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> createSponsor(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip) {
		try {
			Sponsor newSponsor = sponsorService.createSponsor(name, description, street, city, state, zip);
			return new ResponseEntity<Object>(newSponsor, HttpStatus.OK);		
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSponsor(@PathVariable("id") long id) {
		try {
			Sponsor sponsor = sponsorService.getSponsor(id);
			return new ResponseEntity<Object>(sponsor,HttpStatus.OK);
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSponsor(@PathVariable("id") long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip) {
		Sponsor sponsor = null;
		try {
			sponsor = sponsorService.updateSponsor(id, name, description, street, city, state, zip);
			return new ResponseEntity<Object>(sponsor,HttpStatus.OK);
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSponsor(@PathVariable long id)
    {
		Sponsor sponsor = null;
		try {
			sponsor = sponsorService.deleteSponsor(id);
			return new ResponseEntity<Object>(sponsor,HttpStatus.OK);
		}catch(CustomException ce) {
			return new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			return new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}	
    }
}
