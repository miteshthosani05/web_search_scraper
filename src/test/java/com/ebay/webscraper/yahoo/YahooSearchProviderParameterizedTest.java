package com.ebay.webscraper.yahoo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.search.provider.SearchProvider;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.search.service.MockPaginationSearchServie;
import com.ebay.webscraper.search.service.MockSearchService;
import com.ebay.webscraper.search.service.MockSearchServiceEmptyResponse;
import com.ebay.webscraper.search.service.MockSearchServicePaginationV2;

@RunWith(Parameterized.class)
public class YahooSearchProviderParameterizedTest {

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { new MockSearchService(),"test",10}, 
				{ new MockPaginationSearchServie(),"test",10}, 
				{ new MockSearchServicePaginationV2(),"test",4}, 
				{new MockSearchServiceEmptyResponse(),"test",0}});
	}

	private ISearchService<SearchRequest,String> searchService;
	private String searchString;
	private int expectedNumberOfResults;

	public YahooSearchProviderParameterizedTest(ISearchService<SearchRequest,String> searchService, String searchString, int expectedNumberOfResults){
		this.searchService = searchService;
		this.searchString = searchString;
		this.expectedNumberOfResults = expectedNumberOfResults;
	}
	
	@Test
	public void testYahooSearchProvider(){
		SearchProvider searchProvider = new YahooSearchProvider(searchString,searchService);
		Document searchResponse = searchProvider.getSearchResponse();
		Assert.assertNotNull(searchResponse);

		List<SearchResult> searchResults = searchProvider.parseResponse(searchResponse);
		Assert.assertNotNull(searchResults);
		Assert.assertEquals(expectedNumberOfResults, searchResults.size());
	}

}
