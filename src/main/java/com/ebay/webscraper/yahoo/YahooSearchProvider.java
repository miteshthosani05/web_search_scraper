package com.ebay.webscraper.yahoo;

import com.ebay.webscraper.model.Provider;
import com.ebay.webscraper.model.ResponseParser;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.provider.SearchProvider;
import com.ebay.webscraper.search.service.ISearchService;

/**
 * YahooSearchProvider to scrape Yahoo search results
 * 
 * @author mthosani
 *
 */
public class YahooSearchProvider extends SearchProvider{

	private static final String baseUrl = "https://search.yahoo.com/search?p=";
	
	//Tag to extract search element
	private static final String SEARCH_RESULT_ROOT_TAG = "div.yst.result";
	
	//Tag to extract title from search element
	private static final String TITLE_TAG = "h3.title > a[href]";
	
	//Tag to extract description from search element
	private static final String DESCRIPTION_TAG = "div.s > p.abstract.ellipsis";
	
	//Param for next search results page
	private static final String PAGINATION_PARAM = "&b=";

	public YahooSearchProvider(String searchRequest, ISearchService<SearchRequest,String> searchService) {
		super(new SearchRequest(baseUrl + searchRequest, Provider.YAHOO),
				new ResponseParser(SEARCH_RESULT_ROOT_TAG,TITLE_TAG,DESCRIPTION_TAG,PAGINATION_PARAM,11),
				searchService);
	}
}
