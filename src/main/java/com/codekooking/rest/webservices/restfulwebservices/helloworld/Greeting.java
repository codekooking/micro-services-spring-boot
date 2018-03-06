package com.codekooking.rest.webservices.restfulwebservices.helloworld;

public class Greeting {

	private String message;
	
	public Greeting(String message) {
		this.message = message;
	}	
	
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return String.format("Hello [message=%s]", message);
	}
}
