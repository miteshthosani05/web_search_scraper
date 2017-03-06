package com.ebay.webscraper.util;

import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ebay.webscraper.exception.NoResultsFoundException;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.search.service.MockSearchService;
import com.ebay.webscraper.search.service.SearchService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchService.class,URLEncoder.class})
public class SearchServiceApplicationHelperTest {

	@Test(expected = BadRequestException.class)
	public void testEmptySearchString() {
		SearchServiceApplicationHelper.search("");
	}
	
	@Test
	public void testSearch() {
		mockSearchService();
		List<SearchResult> searchResults = SearchServiceApplicationHelper.search("test");
		Assert.assertNotNull(searchResults);
		Assert.assertEquals(20, searchResults.size());
	}
	
	@Test(expected = NoResultsFoundException.class)
	public void testNoResultsFound() {
		mockSearchService();
		SearchServiceApplicationHelper.search("????");
	}
	
	private void mockSearchService(){
		ISearchService<SearchRequest,String> searchService = new MockSearchService();
		PowerMockito.mockStatic(SearchService.class);
		PowerMockito.when(SearchService.getInstance()).thenReturn(searchService);
	}

}
