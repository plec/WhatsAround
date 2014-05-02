package com.plec.whatsaround.populate.input.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.beanio.stream.csv.CsvParserConfiguration;
import org.beanio.stream.csv.CsvReader;

import com.plec.whatsaround.populate.bean.POI;
import com.plec.whatsaround.populate.bean.POIType;
import com.plec.whatsaround.populate.input.InputFileReader;

public class MHInputFileReader implements InputFileReader {

	private static final Logger LOGGER = Logger.getLogger(MHInputFileReader.class);

	private int nbError = 0;

	public List<POI> processInputFile(String fileName) {
		List<POI> pois = new ArrayList<POI>();
		try {
			LOGGER.info("Processing input file : " + fileName);

			Reader reader = new BufferedReader(new FileReader(fileName));
			CsvParserConfiguration config = new CsvParserConfiguration();
			config.setDelimiter(',');
			config.setAlwaysQuote(true);
			config.setMultilineEnabled(true);
			config.setQuote('"');
			config.setWhitespaceAllowed(true);

			CsvReader csvReader = new CsvReader(reader, config);
			String[] currentLine = csvReader.read();
			// escape first line
			currentLine = csvReader.read();
			while (currentLine != null) {
				if (currentLine != null) {
					POI poi = convert(currentLine);
					pois.add(poi);
				}
				currentLine = csvReader.read();
			}

			LOGGER.info("End processing file " + fileName + " nb of poi " + pois.size() + " nb error " + nbError);
		} catch (IOException ioe) {
			LOGGER.error("Error processing file " + fileName + " error : " + ioe.getMessage(), ioe);
		}
		return pois;
	}

	private POI convert(String[] splittedLine) {
		POI poi = new POI();
		try {
			poi.setType(POIType.MH);
			poi.setSourceId(splittedLine[0]);
			poi.setName(cleanString(splittedLine[8]));
			poi.setAdresse(cleanString(splittedLine[9]));
			poi.setCp(cleanString(splittedLine[4]));
			poi.setVille(cleanString(splittedLine[6]));
			poi.setDescription(cleanString(splittedLine[12]));
			return poi;
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.error("Error processing " + poi.getName());
			nbError++;
			return null;
		}

	}

	private String cleanString(String s) {
		if (!StringUtils.isEmpty(s)) {
			String clearedString = StringUtils.remove(s, "\"");
			clearedString = StringUtils.remove(clearedString, "\n");
			return (clearedString);
		}
		return "";
	}

}
