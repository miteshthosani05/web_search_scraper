package com.ebay.webscraper.exception;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.ebay.webscraper.model.Message;
/**
 * RuntimeExceptionMapper that maps exception to Response
 * 
 * @author mthosani
 *
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException>{

	@Override
	public Response toResponse(RuntimeException ex) {
		
		int statusCode;
		if(ex instanceof BadRequestException){
			statusCode = Status.BAD_REQUEST.getStatusCode();
		}else if(ex instanceof InternalServerErrorException){
			statusCode = Status.INTERNAL_SERVER_ERROR.getStatusCode();
		}else {
			statusCode = Status.OK.getStatusCode();
		}
		Message message = new Message(ex.getMessage(),statusCode);
		return Response.status(statusCode).entity(message).build();
	}

}
