package com.plec.whatsaround.populate.geolocalisation;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.plec.whatsaround.populate.bean.GoogleMapsApiResponse;
import com.plec.whatsaround.populate.bean.POI;
/**
 * Class that make the localisation resolution. If the localisation has already been done by previous process, then get the localisation from file in temp directory, otherwise call google api via GoogleMapsDao
 * @author PLEC
 *
 */
public class LocaliseManager {
	private static final Logger LOGGER = Logger.getLogger(LocaliseManager.class);
	private static final String WORKING_PATH = "d:/temp/whatsAround/";
	public void updatePoiLocalisation(POI currentPoi) {
		GoogleMapsDao googleMapsDao = new GoogleMapsDao();
		JsonGeolocReader reader = new JsonGeolocReader();
		try {
		
		File jsonFileForPoi = new File(WORKING_PATH + "response/"+currentPoi.getSourceId()+".json");
		if (!jsonFileForPoi.exists()) {
			LOGGER.info("Json dosn't exists, call google API");
			String jsonResponse = googleMapsDao.geolocalize(currentPoi);
				FileUtils.writeByteArrayToFile(jsonFileForPoi, jsonResponse.getBytes());
				LOGGER.info("Call google API done !");
		} else {
			LOGGER.info("Json already exists, parsing stream");
		}
		String jsonGeoloc = FileUtils.readFileToString(jsonFileForPoi);
		GoogleMapsApiResponse googleMapsApiResponse = reader.getLatLngFromGoogleJson(jsonGeoloc);
		currentPoi.getLatlng().setLat(googleMapsApiResponse.getLat());
		currentPoi.getLatlng().setLng(googleMapsApiResponse.getLng());
		currentPoi.setFormattedAddress(googleMapsApiResponse.getFormattedAddress());
		} catch (IOException ioe) {
			LOGGER.error("Error while writing json reponse"+ ioe.getMessage());
			throw new RuntimeException(ioe.getMessage());
		}
	}
}
