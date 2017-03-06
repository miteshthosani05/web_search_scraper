package com.ebay.webscraper.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.jsoup.nodes.Document;

import com.ebay.webscraper.bing.BingSearchProvider;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.search.provider.SearchProvider;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.yahoo.YahooSearchProvider;

/**
 * A helper class to wire all the Search Providers
 * 
 * This class takes the responsibility to identify all the Search Provider
 * to be called and combine the results
 * 
 * @author mthosani
 *
 */
public class SearchProviderResourceManager {
	
	private  List<SearchProvider> searchProviders = new ArrayList<SearchProvider>();
	
	public SearchProviderResourceManager(String searchString, ISearchService<SearchRequest,String> searchService){
		searchProviders.add(new BingSearchProvider(searchString,searchService));
		searchProviders.add(new YahooSearchProvider(searchString,searchService));
	}
	
	public List<SearchResult> getSearchresults(){
		List<SearchResult> searchResults = new ArrayList<SearchResult>();
		
		for(SearchProvider searchProvider : searchProviders){
			Document searchresponse = searchProvider.getSearchResponse();
			List<SearchResult> searchProviderResults = searchProvider.parseResponse(searchresponse);
			if(CollectionUtils.isNotEmpty(searchProviderResults)){
				searchResults.addAll(searchProviderResults);
			}
		}
		
		return searchResults;
	}

}