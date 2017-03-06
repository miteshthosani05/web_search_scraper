package com.ebay.webscraper.search.service;

/**
 * Interface implemented by Search Service
 * 
 * @author mthosani
 *
 * @param <REQUEST>
 * @param <RESPONSE>
 */
public interface ISearchService<REQUEST,RESPONSE> {
	
	public RESPONSE getResponse(REQUEST request);

}
