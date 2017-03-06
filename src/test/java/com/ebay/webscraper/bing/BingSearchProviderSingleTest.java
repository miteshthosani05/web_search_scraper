package com.ebay.webscraper.bing;

import javax.ws.rs.InternalServerErrorException;

import org.junit.Test;

import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.provider.SearchProvider;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.search.service.MockSearchServiceNullResposne;

public class BingSearchProviderSingleTest {
	
	@Test(expected = InternalServerErrorException.class)
	public void testBingSearchServiceNullResponse(){
		ISearchService<SearchRequest,String> searchService = new MockSearchServiceNullResposne();
		SearchProvider searchProvider = new BingSearchProvider("test",searchService);
		searchProvider.getSearchResponse();
	}

}
