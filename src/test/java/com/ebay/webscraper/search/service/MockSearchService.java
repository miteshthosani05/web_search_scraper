package com.ebay.webscraper.search.service;

import com.ebay.webscraper.model.Provider;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.helper.SearchServiceMockResponseGenerator;

/**
 * Mock response to validate Bing and Yahoo search provider,
 * cases when results were returned and cases when no results were returned
 * for some queries
 * 
 * @author mthosani
 *
 */
public class MockSearchService implements ISearchService<SearchRequest,String>{
	
	public MockSearchService(){
		
	}
	
	@Override
	public String getResponse(SearchRequest request){
	
		if(request.getProvider().equals(Provider.BING) && request.getSearchRequestUrl().equals("https://www.bing.com/search?q=%3F%3F%3F%3F")){
			return SearchServiceMockResponseGenerator.getBingSearchNoResultsMockResponse();
		}else if(request.getProvider().equals(Provider.BING)){
			return SearchServiceMockResponseGenerator.getBingSearchMockResponse();
		}else if(request.getProvider().equals(Provider.YAHOO) && request.getSearchRequestUrl().equals("https://search.yahoo.com/search?p=%3F%3F%3F%3F")){
			return SearchServiceMockResponseGenerator.getYahooSearchNoResultsMockResponse();
		}else{
			return SearchServiceMockResponseGenerator.getYahooSearchMockResponse();
		}
	}
}
