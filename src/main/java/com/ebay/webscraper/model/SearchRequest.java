package com.ebay.webscraper.model;

/**
 * Search Request represents a request made to Search Provider
 * 
 * @author mthosani
 *
 */
public class SearchRequest {
	
	private Provider provider;

	private String searchRequestUrl;
	
	public SearchRequest(String searchRequestUrl, Provider provider){
		this.searchRequestUrl = searchRequestUrl;
		this.provider = provider;
	}

	public String getSearchRequestUrl() {
		return searchRequestUrl;
	}

	public void setSearchRequestUrl(String searchRequestUrl) {
		this.searchRequestUrl = searchRequestUrl;
	}
	
	public Provider getProvider() {
		return provider;
	}
}
