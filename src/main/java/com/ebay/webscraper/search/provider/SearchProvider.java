package com.ebay.webscraper.search.provider;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.ebay.webscraper.model.ResponseParser;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.search.service.ISearchService;

/**
 * SearchProvider calls Search Service and parse the search response from Search Provider
 * 
 * @author mthosani
 *
 */
public abstract class SearchProvider {

	private static final Logger log = Logger.getLogger(SearchProvider.class);

	private SearchRequest searchRequest;
	private ResponseParser responseParser;
	private ISearchService<SearchRequest,String> searchService;
	
	// Number of search results expected from Search Provider 
	private static final int numberOfResults = 10;

	public SearchProvider(SearchRequest searchRequest, ResponseParser responseParser, ISearchService<SearchRequest,String> searchService){

		this.searchRequest = searchRequest;
		this.responseParser = responseParser;
		this.searchService = searchService;
	}

	/**
	 * This method calls search service to get response from Search Provider
	 * 
	 * @return
	 */
	public Document getSearchResponse(){

		Document doc = null;

		try{
			String response = searchService.getResponse(searchRequest);
			doc = Jsoup.parse(response);
		}catch(Exception e){
			log.error(e,e);
			throw new InternalServerErrorException("Internal Server Error");
		}

		return doc;
	}

	/**
	 * This method is to parse the Search Provider Response
	 * 
	 * @param searchResponse
	 * @return
	 */
	public List<SearchResult> parseResponse(Document searchResponse) {

		List<SearchResult> searchResults = new ArrayList<SearchResult>();

		if(searchResponse != null){

			//Extract search results from search response
			Elements searchElements = getSearchResutElements(searchResponse);

			//If no results return 
			if(searchElements.size() == 0){
				return searchResults;
			}

			//Extract title and description from search results and add it to the list
			addSearchResultToTheSearchResultsList(searchElements,searchResults);
			
			//If size of the list is less than number of results, fetch results from next page
			if(searchResults.size() < numberOfResults){
				fetchMoreResults(searchResults);
			}
		}
		return searchResults;
	}

	/**
	 * This method is to update search request URL with pagination param
	 * to fetch search results from next page
	 * 
	 * @param nextSetOfResults
	 */
	private void updateSearchRequest(int nextSetOfResults){

		StringBuffer bf = new StringBuffer(searchRequest.getSearchRequestUrl());
		bf.append(responseParser.getPaginationParam()).append(nextSetOfResults);
		searchRequest.setSearchRequestUrl(bf.toString());

	}

	/**
	 * This method is to extract search results from search response
	 * 
	 * @param searchResponse
	 * @return
	 */
	private Elements getSearchResutElements(Document searchResponse){
		Elements searchElements = searchResponse.select(responseParser.getSearchResultRootTag());
		return searchElements;
	}
	
	/**
	 * This method is to extract title and description for search result
	 * from search results and add it to the list
	 * 
	 * @param searchElements
	 * @param searchResults
	 */
	private void addSearchResultToTheSearchResultsList(Elements searchElements, List<SearchResult> searchResults){

		for(Element searchElement : searchElements){
			Elements title = searchElement.select(responseParser.getTitleTag());
			Elements description = searchElement.select(responseParser.getDescriptionTag());

			if(title.size() > 0 && description.size() > 0){
				SearchResult searchResult = new SearchResult(searchRequest.getProvider().getProviderName(), title.get(0).text(),description.get(0).text());
				searchResults.add(searchResult);
				if(searchResults.size()==numberOfResults){
					break;
				}
			}
		}

	}
	
	/**
	 * This method is to fetch next set of results from next page
	 * 
	 * @param searchResults
	 */
	private void fetchMoreResults(List<SearchResult> searchResults){
		
		int pagination = 0;
		while(searchResults.size() < numberOfResults){

			//Calculate starting index for next set of results
			int nextSetOfResults = pagination * 10 + responseParser.getPaginationInput();

			pagination ++ ;
			//update the search request with pagination param and starting index
			updateSearchRequest(nextSetOfResults);
			Document searchResponse = getSearchResponse();
			
			Elements searchElements = getSearchResutElements(searchResponse);
			
			//In case next page returned no results
			if(searchElements.size() == 0){
				break;
			}
			
			addSearchResultToTheSearchResultsList(searchElements,searchResults);
		}
		
	}

}
