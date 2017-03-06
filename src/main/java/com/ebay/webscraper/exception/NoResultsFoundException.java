package com.ebay.webscraper.exception;

/**
 * NoResultsFoundException handles cases when search provider returned 0 results
 * 
 * @author mthosani
 *
 */
public class NoResultsFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NoResultsFoundException(String message){
		super(message);
	}

	

}
