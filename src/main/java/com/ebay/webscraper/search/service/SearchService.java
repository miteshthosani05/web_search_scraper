package com.ebay.webscraper.search.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.InternalServerErrorException;

import org.apache.log4j.Logger;

import com.ebay.webscraper.model.SearchRequest;

/**
 * Search Service that connects to the Search Provider and gets the raw response
 * 
 * @author mthosani
 *
 */
public class SearchService implements ISearchService<SearchRequest,String>{

	private static final Logger log = Logger.getLogger(SearchService.class);

	private static final ISearchService<SearchRequest,String> instance = new SearchService();

	private SearchService(){}

	public static ISearchService<SearchRequest,String> getInstance(){
		return instance;
	}

	@Override
	public String getResponse(SearchRequest request) {

		URL url;
		String response;
		
		try {
			url = new URL(request.getSearchRequestUrl());
			response = getContent(url);
		} catch (MalformedURLException e) {
			log.error(e,e);
			throw new InternalServerErrorException("Internal Server Error");
		}
		
		return response;
	}
	
	public String getContent(URL url){
		
		HttpsURLConnection con = null;
		String responseContent;
		
		try{
		con = (HttpsURLConnection)url.openConnection();
		responseContent = readInputStream(con.getInputStream());
		}catch(Exception e){
			log.error(e,e);
			throw new InternalServerErrorException("Internal Server Error");

		}finally {
			if(con != null){
				log.info("Closing HttpsURLConection");
				con.disconnect();
			}
		}
		return responseContent;
	}
	
	public String readInputStream(InputStream connectionInputStream){
		
		BufferedReader in = null;
		String response;
		StringBuilder sb = new StringBuilder();
		String line="";
		
		try {
			in = new BufferedReader(new InputStreamReader(connectionInputStream));

			while ((line = in.readLine()) != null) {
				sb.append(line);
			}

			response= sb.toString();
		} catch (IOException e) {
			log.error(e,e);
			throw new InternalServerErrorException("Internal Server Error");
		}finally {
			if (in != null) {
				try {
					log.info("ClosingInput stream");
					in.close();
				} catch (IOException e) {
					log.error(e,e);
				}
			}
		}

		return response;

	}
}
