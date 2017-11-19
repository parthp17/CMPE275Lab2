package sjsu.cmpe275.Lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sjsu.cmpe275.Lab2.service.PlayerService;
import sjsu.cmpe275Lab2.CustomException.CustomException;

@RestController
@RequestMapping("/opponents")
public class OpponentController {

	@Autowired
	private PlayerService playerService;
	
	@RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.PUT)
	public ResponseEntity<Object> addOpponents(@PathVariable("id1") long id1, @PathVariable("id2") long id2)
	{
		if(id1 == id2) return new ResponseEntity<Object>("Same Players", HttpStatus.BAD_REQUEST);
		ResponseEntity<Object> response = null;
		try{
			String str = playerService.addOpponents(id1, id2);
			if(str.equals("Opponents are Added") || str.equals("Already Opponents")) response = new ResponseEntity<Object>(str, HttpStatus.OK);
		}catch(CustomException ce) {
			response = new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteOpponents(@PathVariable("id1") long id1, @PathVariable("id2") long id2)
	{
		if(id1 == id2) return new ResponseEntity<Object>("Same Players", HttpStatus.BAD_REQUEST);
		ResponseEntity<Object> response = null;
		try{
			String str = playerService.deleteOpponents(id1, id2);
			if(str=="No more Opponents") response = new ResponseEntity<Object>(str, HttpStatus.OK);
		}catch(CustomException ce) {
			response = new ResponseEntity<Object>(ce.getMessage(),ce.getStatus());
		}catch(Exception e){
			response = new ResponseEntity<Object>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
}