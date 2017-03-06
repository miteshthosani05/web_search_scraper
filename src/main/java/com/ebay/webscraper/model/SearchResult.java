package com.ebay.webscraper.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * SearchResult represent one search element returned by Search Provider 
 * 
 * @author mthosani
 *
 */
@XmlRootElement
public class SearchResult {
	
	private String provider;
	
	private String title;
	
	private String description;
	
	public SearchResult(){
		
	}
	public SearchResult(String provider, String title, String description){
		this.provider = provider;
		this.title = title;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getProvider() {
		return provider;
	}
	
	public void setProvider(String provider) {
		this.provider = provider;
	}
}
