package com.ebay.webscraper.exception;

import java.util.Arrays;
import java.util.Collection;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ebay.webscraper.model.Message;

@RunWith(Parameterized.class)
public class RuntimeExceptionMapperTest {
	
	@Parameters
	  public static Collection<Object[]> data() {
	    return Arrays.asList(new Object[][] { { new BadRequestException("Bad Request"),400,"Bad Request"}, 
	    		{ new InternalServerErrorException("Internal Server Error"),500,"Internal Server Error"}, 
	    		{new NoResultsFoundException("No Results Found"),200,"No Results Found"}});
	}
	
	private RuntimeException runtimeException;
	private int statusCode;
	private String message;
	
	
	public RuntimeExceptionMapperTest(RuntimeException runtimeException, int statusCode, String message){
		this.runtimeException = runtimeException;
		this.statusCode = statusCode;
		this.message = message;
	}
	
	@Test
	public void testRuntimeExceptionMapper() {
		RuntimeExceptionMapper runtimeExceptionMapper = new RuntimeExceptionMapper();
		Response response = runtimeExceptionMapper.toResponse(runtimeException);
		Assert.assertTrue(response.getStatus() == statusCode);
		Message responseMessage = (Message)response.getEntity();
		Assert.assertTrue(responseMessage.getStatusCode() == statusCode);
		Assert.assertEquals(message, responseMessage.getMessage());
	}
}
