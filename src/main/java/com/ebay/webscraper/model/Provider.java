package com.ebay.webscraper.model;
/**
 * Values to identify Search Provider
 *  
 * @author mthosani
 *
 */
public enum Provider {
	
	BING("Bing"),
	YAHOO("Yahoo");
	
	private String providerName;
	
	Provider(String providerName){
		this.providerName = providerName;
	}
	
	public String getProviderName(){
		return providerName;
	}

}
