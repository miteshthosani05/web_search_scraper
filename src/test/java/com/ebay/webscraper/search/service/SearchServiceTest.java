package com.ebay.webscraper.search.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.InternalServerErrorException;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.ebay.webscraper.model.Provider;
import com.ebay.webscraper.model.SearchRequest;
import com.ebay.webscraper.search.helper.SearchServiceMockResponseGenerator;

@RunWith(PowerMockRunner.class)
@PrepareForTest({URL.class})
public class SearchServiceTest {

	@Test
	public void testGetResponse() throws Exception{

		URL newUrl = new URL("https://www.bing.com/search?q=test");
		SearchService searchService = getSearchServiceInstance();
		searchService = PowerMockito.spy(searchService);
		PowerMockito.doReturn(SearchServiceMockResponseGenerator.getBingSearchMockResponse()).when(searchService, "getContent", newUrl);
		SearchRequest searchRequest = new SearchRequest("https://www.bing.com/search?q=test",Provider.BING);
		String response = searchService.getResponse(searchRequest);
		Assert.assertTrue(StringUtils.isNotEmpty(response));
		Assert.assertEquals(SearchServiceMockResponseGenerator.getBingSearchMockResponse(), response);
	}

	@Test(expected = InternalServerErrorException.class)
	public void testMalformedUrl(){
		SearchService searchService = getSearchServiceInstance();
		SearchRequest searchRequest = new SearchRequest("htp://www.bing.com/search?q=test",Provider.BING);
		searchService.getResponse(searchRequest);
	}

	@Test
	public void testGetContent() throws Exception{

		URL url = buildUrl(new FileInputStream("src/test/resources/bing_search_response.html"));
		SearchService searchservice =  getSearchServiceInstance();
		String response = searchservice.getContent(url);
		Assert.assertEquals(SearchServiceMockResponseGenerator.getBingSearchMockResponse(), response);

	}

	@Test(expected = InternalServerErrorException.class)
	public void testNullInputStream() throws Exception{
		URL url = buildUrl(null);
		SearchService searchservice =  getSearchServiceInstance();
		searchservice.getContent(url);
	}

	@Test
	public void testReadingInutstream() throws Exception{
		SearchService searchservice =  getSearchServiceInstance();
		InputStream in = new FileInputStream("src/test/resources/bing_search_response.html");
		String response = searchservice.readInputStream(in);
		Assert.assertEquals(SearchServiceMockResponseGenerator.getBingSearchMockResponse(), response);
	}

	@Test(expected = InternalServerErrorException.class)
	public void testInputStreamThrowingIOException() throws Exception{
		InputStream inputStream = Mockito.mock(InputStream.class);
		Mockito.doReturn(-1).when(inputStream).read(new byte[1]);
		testInputstream(inputStream);
	}

	@Test(expected = InternalServerErrorException.class)
	public void testInutStreamCloseThrowingIOException() throws Exception{

		InputStream inputStream = Mockito.mock(InputStream.class);
		Mockito.doThrow(new IOException()).when(inputStream).close();
		testInputstream(inputStream);
	}

	public URL buildUrl(InputStream in) throws Exception{

		final HttpsURLConnection mockConnection =PowerMockito.mock(HttpsURLConnection. class);
		InputStream inputStream = in;   
		PowerMockito.when(mockConnection.getInputStream()).thenReturn( inputStream);

		URLStreamHandler stubURLStreamHandler = new URLStreamHandler() {
			@Override
			protected URLConnection openConnection(URL u ) throws IOException {
				return mockConnection ;
			}           
		};

		URL url = new URL(null,"https://www.bing.com/search?q=test",stubURLStreamHandler);
		return url;

	}

	private SearchService getSearchServiceInstance(){
		return (SearchService)SearchService.getInstance();
	}

	private void testInputstream(InputStream inputStream){
		SearchService searchservice =  getSearchServiceInstance();
		searchservice.readInputStream(inputStream);
	}
}
