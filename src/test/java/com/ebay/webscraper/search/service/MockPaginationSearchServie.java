package com.ebay.webscraper.search.service;

import com.ebay.webscraper.model.Provider;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.helper.SearchServiceMockResponseGenerator;

/**
 * Mock response returning few results, that will make Search Provider 
 * to call Search Service again
 * 
 * This Mock response is to validate pagination use case
 * 
 * @author mthosani
 *
 */
public class MockPaginationSearchServie implements ISearchService<SearchRequest,String>{
	
	public MockPaginationSearchServie(){
		
	}

	@Override
	public String getResponse(SearchRequest request) {
		if(request.getProvider().equals(Provider.BING)){
			return SearchServiceMockResponseGenerator.getBingSearchPaginationMockResponse();
		}else{
			return SearchServiceMockResponseGenerator.getYahooSearchPaginationMockResponse();
		}
	}

}
