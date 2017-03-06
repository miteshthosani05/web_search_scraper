package com.ebay.webscraper.search.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.ebay.webscraper.search.service.SearchService;

/**
 * Utility class to generate mock responses for various use cases
 * 
 * @author mthosani
 *
 */
public class SearchServiceMockResponseGenerator {
	
	private static final Logger log = Logger.getLogger(SearchServiceMockResponseGenerator.class);
	
	public static String getBingSearchNoResultsMockResponse(){
		return getMockResponse("src/test/resources/bing_search_no_results_response.html");
	}
	
	public static String getYahooSearchNoResultsMockResponse(){
		return getMockResponse("src/test/resources/yahoo_search_no_results_response.html");
	}
	
	public static String getBingSearchMockResponse(){
		return getMockResponse("src/test/resources/bing_search_response.html");
	}
	
	public static String getYahooSearchMockResponse(){
		return getMockResponse("src/test/resources/yahoo_search_response.html");
	}
	
	public static String getBingSearchPaginationMockResponse(){
		return getMockResponse("src/test/resources/bing_search_pagination.html");
	}
	
	public static String getYahooSearchPaginationMockResponse(){
		return getMockResponse("src/test/resources/yahoo_search_pagination.html");
	}
	
	public static String getMockResponse(String fileName){
		
		StringBuilder contentBuilder = new StringBuilder();
		try {
			
		    BufferedReader in = new BufferedReader(new FileReader(fileName));
		    String str;
		    while ((str = in.readLine()) != null) {
		        contentBuilder.append(str);
		    }
		    in.close();
		} catch (IOException e) {
			log.info(e,e);
		}
		String content = contentBuilder.toString();
		
		return content;
		
	}

}
