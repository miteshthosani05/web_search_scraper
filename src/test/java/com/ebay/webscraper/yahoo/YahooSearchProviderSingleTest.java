package com.ebay.webscraper.yahoo;

import javax.ws.rs.InternalServerErrorException;

import org.junit.Test;

import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.provider.SearchProvider;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.search.service.MockSearchServiceNullResposne;

public class YahooSearchProviderSingleTest {

	@Test(expected = InternalServerErrorException.class)
	public void testYahooSearchServiceNullResponse(){
		ISearchService<SearchRequest,String> searchService = new MockSearchServiceNullResposne();
		SearchProvider searchProvider = new YahooSearchProvider("test",searchService);
		searchProvider.getSearchResponse();

	}

}
