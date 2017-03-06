package com.ebay.webscraper.resources;

import java.util.List;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ebay.webscraper.model.Provider;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.search.service.MockSearchService;
import com.ebay.webscraper.search.service.SearchService;

@RunWith(PowerMockRunner.class)
@PrepareForTest({SearchService.class})
public class SearchTest {
	
	@Test
	public void testSearchRequest() {
		Search search = new Search();
		ISearchService<SearchRequest,String> searchService = new MockSearchService();
		PowerMockito.mockStatic(SearchService.class);
		PowerMockito.when(SearchService.getInstance()).thenReturn(searchService);
		
		Response response = search.search("test");
		List<SearchResult> results = (List<SearchResult>)response.getEntity();
		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() == 20);
		
		for(int i= 0; i<10 ; i++){
			Assert.assertTrue(results.get(i).getProvider().equals(Provider.BING.getProviderName()));
			Assert.assertNotNull(results.get(i).getTitle());
			Assert.assertNotNull(results.get(i).getDescription());
		}
		
		for(int i= 10; i<20 ; i++){
			Assert.assertTrue(results.get(i).getProvider().equals(Provider.YAHOO.getProviderName()));
			Assert.assertNotNull(results.get(i).getTitle());
			Assert.assertNotNull(results.get(i).getDescription());
		}
	}


}
