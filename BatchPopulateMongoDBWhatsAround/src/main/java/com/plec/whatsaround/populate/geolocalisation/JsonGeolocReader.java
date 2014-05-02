package com.plec.whatsaround.populate.geolocalisation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Map;

import org.beanio.stream.json.JsonReader;

import com.plec.whatsaround.populate.bean.GoogleMapsApiResponse;
import com.plec.whatsaround.populate.bean.LatLng;

public class JsonGeolocReader {
	/**
	 * Extract latLng from the json response of the google maps api
	 * @param json the json stream from google api
	 * @return a LatLng object
	 */
	@SuppressWarnings("unchecked")
	public GoogleMapsApiResponse getLatLngFromGoogleJson(String json) {
		try {
			GoogleMapsApiResponse googleMapsApiResponse = new GoogleMapsApiResponse();
			Reader reader = new BufferedReader(new StringReader(json));
			JsonReader jsonReader = new JsonReader(reader);
			 Map<String, Object> jsonObject = jsonReader.read();
			 ArrayList<Map<String, Object>> results = (ArrayList<Map<String, Object>>) jsonObject.get("results");			 
			 Map<String, Double> location = (Map<String, Double>) ((Map<String, Object>) results.get(0).get("geometry")).get("location");
			 
			 googleMapsApiResponse.setLat(location.get("lat"));
			 googleMapsApiResponse.setLng(location.get("lng"));
			 googleMapsApiResponse.setFormattedAddress((String) results.get(0).get("formatted_address"));
			 return googleMapsApiResponse;
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
		
	}
}
