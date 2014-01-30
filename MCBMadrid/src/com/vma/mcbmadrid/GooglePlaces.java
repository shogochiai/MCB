package com.vma.mcbmadrid;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

public class GooglePlaces {

	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final String API_KEY = "AIzaSyAgBTUju8TyhJzAChsag1J2JTSdDhakNKU";
	//private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
	//private static final String PLACES_TEXT_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/search/json?";
	//private static final String PLACES_DETAILS_URL = "https://maps.googleapis.com/maps/api/place/details/json?";
	private static final String PLACES_AUTOCOMPLETE = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
	
	//private double _latitude;
	//private double _longitude;
	//private double _radius;
	
	/**
	 * Get the request for the Autocomplete search API
	 * 
	 * @see https://developers.google.com/places/documentation/autocomplete?hl=es
	 * @param search the string to search
	 * @return ListAutocomplete type
	 * @throws Exception
	 */
	public ListAutocomplete getAutocomplete(String search) throws Exception{
		try{
			HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
			HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_AUTOCOMPLETE));
			request.getUrl().put("key", API_KEY);
			request.getUrl().put("sensor", "false");
			request.getUrl().put("input", search);
			
			ListAutocomplete lista = request.execute().parseAs(ListAutocomplete.class);
			
			return lista;
		}catch(HttpResponseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/*
	public PlacesList search(double latitude, double longitude, double radius, String types) throws Exception{
		this._latitude = latitude;
		this._longitude = longitude;
		this._radius = radius;
		
		try{
			HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
			HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_SEARCH_URL));
			request.getUrl().put("key", API_KEY);
			request.getUrl().put("location", _latitude + "," + _longitude);
			request.getUrl().put("radius", _radius);
			request.getUrl().put("sensor", "false");
			
			if (types != null){
				request.getUrl().put("types", types);
			}
			
			PlacesList list = request.execute().parseAs(PlacesList.class);
			return list;
		}catch(HttpResponseException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public PlaceDetails getPlaceDetails(String reference) throws Exception{
		try{
			HttpRequestFactory httpRequestFactory = createRequestFactory(HTTP_TRANSPORT);
			HttpRequest request = httpRequestFactory.buildGetRequest(new GenericUrl(PLACES_DETAILS_URL));
			request.getUrl().put("key", API_KEY);
			request.getUrl().put("reference", reference);
			request.getUrl().put("sensor", "false");
			
			PlaceDetails place = request.execute().parseAs(PlaceDetails.class);
			return place;
		}catch(HttpResponseException e){
			throw e;
		}
	}
	*/
	
	public static HttpRequestFactory createRequestFactory(final HttpTransport transport){
		return transport.createRequestFactory(new HttpRequestInitializer(){

			@Override
			public void initialize(HttpRequest request) throws IOException {
				HttpHeaders headers = new HttpHeaders();
				headers.setUserAgent("Android-Google-Places");
				request.setHeaders(headers);
				JsonObjectParser parser = new JsonObjectParser(new JacksonFactory());
				request.setParser(parser);
			}
			
		});
	}
}
