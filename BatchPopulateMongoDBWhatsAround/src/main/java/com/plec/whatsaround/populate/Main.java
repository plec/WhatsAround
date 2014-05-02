package com.plec.whatsaround.populate;

import java.util.List;

import org.apache.log4j.Logger;

import com.plec.whatsaround.populate.bean.POI;
import com.plec.whatsaround.populate.geolocalisation.LocaliseManager;
import com.plec.whatsaround.populate.input.InputFileReader;
import com.plec.whatsaround.populate.input.impl.MDFInputFileReader;
import com.plec.whatsaround.populate.input.impl.MHInputFileReader;
import com.plec.whatsaround.populate.mongodb.MongoDBPOIDao;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);

	private static final String WORKING_PATH = "d:/temp/whatsAround/";

	public static void main(String[] args) {
		Main main = new Main();
		main.execute();
	}

	private void execute() {
		LOGGER.info("Process MDF");
		//parseMuseeDeFrance();
		LOGGER.info("End processing MDF");
		
		LOGGER.info("Process MH");
		parseMonumentsHistoriques();
		LOGGER.info("End processing MH");
	}

	private void parseMuseeDeFrance() {
		LOGGER.info("Step 1 - Reading input file Muse de france " + WORKING_PATH + "liste-musees-de-france-2012-2.csv");
		InputFileReader reader = new MDFInputFileReader();
		List<POI> poisMDF = reader.processInputFile(WORKING_PATH + "liste-musees-de-france-2012-2.csv");
		LOGGER.info("End of step 1 - got " + poisMDF.size() + "pois");
		processPOI(poisMDF);
	}
	
	private void parseMonumentsHistoriques() {
		LOGGER.info("Step 1 - Reading input file Muse de france " + WORKING_PATH + "prot2012-MH-3.csv");
		InputFileReader reader = new MHInputFileReader();
		List<POI> poisMH = reader.processInputFile(WORKING_PATH + "prot2012-MH-3.csv");
		LOGGER.info("End of step 1 - got " + poisMH.size() + "pois");
		processPOI(poisMH);
	}
	
	private void processPOI(List<POI> pois) {
		// geolocalisation des POIs
		LOGGER.info("Step 2 - Localise POI");
		LocaliseManager localiseManager = new LocaliseManager();
		MongoDBPOIDao dao = new MongoDBPOIDao();

		// for (POI currentPoi : pois) {
		for (int i = 0; i < 1; i++) {
			POI currentPoi = pois.get(i);
			// test if json already exists
			localiseManager.updatePoiLocalisation(currentPoi);
			dao.savePOI(currentPoi);
		}

//		MongoDBPOIDao dao = new MongoDBPOIDao();
//		dao.savePOIs(pois);
		
	}

}
