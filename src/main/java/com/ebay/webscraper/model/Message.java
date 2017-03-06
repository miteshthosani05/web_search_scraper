package com.ebay.webscraper.model;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * Message class represents a message returned in case of exceptions
 * 
 * @author mthosani
 *
 */
@XmlRootElement
public class Message {

	private String message;
	
	private int statusCode;
	
	public Message(){
		
	}
	
	public Message(String message, int statusCode){
		this.message = message;
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String errorMessage) {
		this.message = errorMessage;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int errorCode) {
		this.statusCode = errorCode;
	}
}
