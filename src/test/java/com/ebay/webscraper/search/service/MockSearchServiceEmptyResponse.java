package com.ebay.webscraper.search.service;

import com.ebay.webscraper.model.SearchRequest;

/**
 * Mock response to validate use case when empty response was returned
 * 
 * @author mthosani
 *
 */
public class MockSearchServiceEmptyResponse implements ISearchService<SearchRequest,String>{

	@Override
	public String getResponse(SearchRequest request) {
		return "";
	}

}
