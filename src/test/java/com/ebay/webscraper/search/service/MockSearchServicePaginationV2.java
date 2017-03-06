package com.ebay.webscraper.search.service;

import com.ebay.webscraper.model.Provider;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.helper.SearchServiceMockResponseGenerator;

/**
 * Mock response to validate use case when pagination returned no results
 * 
 * @author mthosani
 *
 */
public class MockSearchServicePaginationV2 implements ISearchService<SearchRequest,String>{
	
	public MockSearchServicePaginationV2(){
		
	}

	@Override
	public String getResponse(SearchRequest request) {
		
		if((request.getProvider().equals(Provider.BING) 
				&& request.getSearchRequestUrl().equals("https://www.bing.com/search?q=test&first=10"))||
				(request.getProvider().equals(Provider.YAHOO) 
						&& request.getSearchRequestUrl().equals("https://search.yahoo.com/search?p=test&b=11"))){
			return "";
		}else if(request.getProvider().equals(Provider.BING)){
			return SearchServiceMockResponseGenerator.getBingSearchPaginationMockResponse();
		}else{
			return SearchServiceMockResponseGenerator.getYahooSearchPaginationMockResponse();
		}
	}

}
