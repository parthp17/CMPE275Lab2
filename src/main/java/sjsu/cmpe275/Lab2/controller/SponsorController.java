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

@RestController
public class SponsorController {
	
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value="/sponsor", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> createSponsor(@RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip) {
		try {
			Sponsor newSponsor = new Sponsor();
			newSponsor.setName(name);
			newSponsor.setDescription(description);
			newSponsor.setAddress(new Address(street,city,state,zip));
			sponsorService.save(newSponsor);
			return new ResponseEntity<Object>(newSponsor, HttpStatus.OK);		
		}catch(Exception e) {
			return new ResponseEntity<Object>("Invalid Request",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value ="/sponsor/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSponsor(@PathVariable("id") long id) {
		try {
			Sponsor sponsor = sponsorService.findOne(id);
			if(sponsor == null) {
				return new ResponseEntity<Object>("Sponsor is not present",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(sponsor,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>("bad request",HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/sponsor/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> updateSponsor(@PathVariable("id") long id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "street", required = false) String street,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "state", required = false) String state,
            @RequestParam(value = "zip", required = false) String zip) {
		Sponsor sponsor = null;
		try {
			sponsor = sponsorService.findOne(id);
			if(sponsor == null) {
				return new ResponseEntity<Object>("Sponsor is not present", HttpStatus.NOT_FOUND);
			}
			sponsor.setName(name);
			sponsor.setDescription(description);
			sponsor.setAddress(new Address(street,city,state,zip));
			sponsorService.save(sponsor);
			return new ResponseEntity<Object>(sponsor,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<Object>("Required Parameter is not given", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/sponsor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteSponsor(@PathVariable long id)
    {
		Sponsor sponsor = null;
		try {
			sponsor = sponsorService.findOne(id);
			if(sponsor == null) {
				return new ResponseEntity<Object>("Sponsor is not present", HttpStatus.NOT_FOUND);
			}
			Player player = playerService.findBySponsor(sponsor);
			
			if(player == null) {
				sponsorService.delete(id);
				return new ResponseEntity<Object>(sponsor,HttpStatus.OK);
			}else {
				return new ResponseEntity<Object>("Player is belonging to this Sponsor",HttpStatus.BAD_REQUEST);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<Object>("Invalid request",HttpStatus.BAD_REQUEST);
		}	
    }
}
