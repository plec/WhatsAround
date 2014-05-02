package com.plec.whatsaround.populate.geolocalisation;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.plec.whatsaround.populate.bean.POI;

public class GoogleMapsDao {
	private static final Logger LOGGER = Logger.getLogger(GoogleMapsDao.class);
	private String googleApiBaseUrl = "http://maps.googleapis.com/maps/api/geocode/json?";
	private String googleApiKey = "AIzaSyCeNTWK1kHEk0Q8ofH_eOgu4NbUE1KDcEM";
	public String geolocalize(POI poi) {
		StringBuilder queryParameters = new StringBuilder();
		queryParameters.append("sensor=false");
		queryParameters.append("&");
		queryParameters.append("address=");
		queryParameters.append(poi.getAdresse()).append(" ");
		queryParameters.append(poi.getCp()).append(" ");
		queryParameters.append(poi.getVille());
		String queryParam = StringUtils.replace(queryParameters.toString(), " ", "%20");
		
		LOGGER.info("URL : " + googleApiBaseUrl + queryParam);
		try {
			URLConnection connection = new URL(googleApiBaseUrl + queryParam).openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			LOGGER.info("Calling google API : " + connection.getURL().toString());
			InputStream response = connection.getInputStream();
			StringWriter writer = new StringWriter();
			IOUtils.copy(response, writer,"UTF-8");
			return writer.toString();
		} catch (IOException ioe) {
			LOGGER.error("Erreur while google api call", ioe);
			throw new RuntimeException(ioe);
		}
		
	}
	/**
	 * @return the googleApiBaseUrl
	 */
	public String getGoogleApiBaseUrl() {
		return googleApiBaseUrl;
	}
	/**
	 * @param googleApiBaseUrl the googleApiBaseUrl to set
	 */
	public void setGoogleApiBaseUrl(String googleApiBaseUrl) {
		this.googleApiBaseUrl = googleApiBaseUrl;
	}
}
