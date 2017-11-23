package sjsu.cmpe275Lab2.CustomException;

import org.springframework.http.HttpStatus;

/*
 * CustomException class to handle status code 404 and 400 scenarios
 */

public class CustomException extends Exception{

	private final String message;
	private final HttpStatus status;
	public CustomException(String message, HttpStatus status) {
		super();
		this.message = message;
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	
}
