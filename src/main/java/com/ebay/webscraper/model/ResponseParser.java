package com.ebay.webscraper.model;

/**
 * ResponseParser represent attributes required to parse Search Provider response
 * 
 * @author mthosani
 *
 */
public class ResponseParser {
	
	private String searchResultRootTag;
	
	private String titleTag;
	
	private String descriptionTag;
	
	private String paginationParam;
	
	private Integer paginationInput; 
	
	public ResponseParser(String searchResultRootTag , String titleTag, String descriptionTag, String paginationParam,Integer paginationInput){
		
		this.searchResultRootTag = searchResultRootTag;
		this.titleTag = titleTag;
		this.descriptionTag = descriptionTag;
		this.paginationParam = paginationParam;
		this.paginationInput = paginationInput;
	}
	
	public String getTitleTag() {
		return titleTag;
	}

	public String getDescriptionTag() {
		return descriptionTag;
	}

	public String getPaginationParam() {
		return paginationParam;
	}

	public Integer getPaginationInput() {
		return paginationInput;
	}

	public String getSearchResultRootTag() {
		return searchResultRootTag;
	}

}
