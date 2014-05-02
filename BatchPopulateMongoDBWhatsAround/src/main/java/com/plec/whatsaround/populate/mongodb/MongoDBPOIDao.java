package com.plec.whatsaround.populate.mongodb;

import java.net.UnknownHostException;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.code.morphia.Morphia;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.WriteResult;
import com.plec.whatsaround.populate.bean.POI;

public class MongoDBPOIDao {
	private static final Logger LOGGER = Logger.getLogger(MongoDBPOIDao.class);
	public void savePOIs(List<POI> pois) {
		try {
			MongoClientURI uri  = new MongoClientURI("mongodb://whatsaround:whatsaround@ds029630.mongolab.com:29630/whatsaroundbase");
	        MongoClient client = new MongoClient(uri);
	        DB db = client.getDB(uri.getDatabase());
	        DBCollection POICollection = db.getCollection("POI");
	        for (POI poi : pois) {
	        	Morphia m = new Morphia().map(POI.class);
	        	DBObject currentPoi = m.toDBObject(poi);
		        LOGGER.info("Inserting a new POI : "+currentPoi.toString());
		        WriteResult result = POICollection.insert(currentPoi);
		        LOGGER.info("Result : " + result.getN() + " documents inserted");
	        }
	        client.close();
		} catch (UnknownHostException uhe) {
			LOGGER.error("Error inserting pois : " + uhe.getMessage(), uhe);
		}
	}
	public void savePOI(POI poi) {
		try {
			MongoClientURI uri  = new MongoClientURI("mongodb://whatsaround:whatsaround@ds029630.mongolab.com:29630/whatsaroundbase");
	        MongoClient client = new MongoClient(uri);
	        DB db = client.getDB(uri.getDatabase());
	        DBCollection POICollection = db.getCollection("POI");
        	Morphia m = new Morphia().map(POI.class);
        	DBObject currentPoi = m.toDBObject(poi);
	        LOGGER.info("Inserting a new POI : "+currentPoi.toString());
	        WriteResult result = POICollection.insert(currentPoi);
	        LOGGER.info("Result : " + result.getN() + " documents inserted");
	        client.close();
		} catch (UnknownHostException uhe) {
			LOGGER.error("Error inserting pois : " + uhe.getMessage(), uhe);
		}
	}
	public void search(double lat, double lng, int radius) {
		try {
			MongoClientURI uri  = new MongoClientURI("mongodb://whatsaround:whatsaround@ds029630.mongolab.com:29630/whatsaroundbase");
	        MongoClient client = new MongoClient(uri);
	        DB db = client.getDB(uri.getDatabase());
	        DBCollection pOICollection = db.getCollection("POI");
	        //pOICollection.ensureIndex( new BasicDBObject( "latlng" , "2d" ) );
	        //DBCursor result = pOICollection.find(new BasicDBObject( "latlng" , new BasicDBObject( "$near" , new Double[]{(double)5.033,(double) 47.333})));
	        DBCursor result = pOICollection.find(new BasicDBObject(
	        		"latlng" , new BasicDBObject(
	        				"$geoWithin", 
	        				new BasicDBObject(
	        						"$centerSphere",new Object[]{
	        								new Double[]{(double) lat,(double) lng},
	        								(double) radius/3959}
	        						)
	        				)
	        		));
	        LOGGER.info("NB result : " + result.count());
	        LOGGER.info(result.toString());
	        client.close();
		} catch (UnknownHostException uhe) {
			LOGGER.error("Error inserting pois : " + uhe.getMessage(), uhe);
		}
		
	}
}
