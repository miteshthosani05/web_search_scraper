package com.ebay.webscraper.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.BadRequestException;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ebay.webscraper.exception.NoResultsFoundException;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.search.service.ISearchService;
import com.ebay.webscraper.search.service.SearchService;

/**
 * A Helper class to validate input search string
 * 
 *  This class takes responsibility of validating input search string,
 *  encoding it and passing it to the search provider
 *  
 * @author mthosani
 *
 */
public class SearchServiceApplicationHelper {
	
	private static final Logger log = Logger.getLogger(SearchServiceApplicationHelper.class);
	
	public static List<SearchResult> search(String searchString){
		
		if(StringUtils.isEmpty(searchString)){
			throw new BadRequestException("Empty or null search query String");
		}
		
		try {
			searchString = URLEncoder.encode(searchString, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e,e);
			throw new BadRequestException("search query String URL encoding failed");
		}
		
		ISearchService<SearchRequest,String> searchService = SearchService.getInstance();
		SearchProviderResourceManager util = new SearchProviderResourceManager(searchString,searchService);
		List<SearchResult> searchResults = util.getSearchresults();
		
		if(CollectionUtils.isEmpty(searchResults)){
			throw new NoResultsFoundException("No results Found");
		}
		return searchResults;
	}

}
