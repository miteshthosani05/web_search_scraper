package com.ebay.webscraper.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ebay.webscraper.model.SearchResult;
import com.ebay.webscraper.util.SearchServiceApplicationHelper;

/**
 * Search resource
 * 
 * @author mthosani
 *
 */
@Path("/search")
public class Search {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response search(@QueryParam("searchString") String searchString){
		
		List<SearchResult> searchResults = SearchServiceApplicationHelper.search(searchString);
		GenericEntity< List<SearchResult> > entity = new GenericEntity<List<SearchResult>>( searchResults ) { };
		return Response.status(Status.OK.getStatusCode()).entity(entity).build();
		
	}


}
