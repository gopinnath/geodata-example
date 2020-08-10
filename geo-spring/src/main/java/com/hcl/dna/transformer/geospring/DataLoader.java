package com.hcl.dna.transformer.geospring;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
	
	private static final Logger logger = Logger.getLogger(DataLoader.class.getName());
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	private GeoRepository repo;
	
	@KafkaListener(topics = "geocode-topic", groupId = "spring")
	public void listen(String tsvString) throws Exception {
		GeoData data = buildGeoData(tsvString);
		GeoData entity = repo.save(data);
		logger.info("Persisted Geo Id " + entity.getGeocodeId());
	}
	

	private GeoData buildGeoData(String tsvString) throws Exception {
		GeoData data = new GeoData();
		String[] geoData = tsvString.split("\t");
		data.setGeocodeId(Integer.valueOf(geoData[0]));
		data.setName(geoData[1]);
		data.setAsciiName(geoData[2]);
		data.setAlternateNames(geoData[3]);
		data.setLatitude(Double.valueOf(geoData[4]));
		data.setLongitude(Double.valueOf(geoData[5]));
		data.setFeatureClass(geoData[6]);
		data.setFeatureCode(geoData[7]);
		data.setCountryCode(geoData[8]);
		data.setAltCountryCode(geoData[9]);
		data.setPopulation(fromStringToBigInt(geoData[14]));
		data.setElevation(fromStringToInteger(geoData[15]));
		data.setTimezone(geoData[17]);
		data.setModificationDate(fromStringToDate(geoData[18],data.getGeocodeId()));
		return data;
	}
	
	private BigInteger fromStringToBigInt(String value)	{
		if(value != null && !"".equals(value))	{
			return BigInteger.valueOf(Long.valueOf(value));
		}
		return null;
	}
	
	private Integer fromStringToInteger(String value)	{
		if(value != null && !"".equals(value))	{
			return Integer.valueOf(value);
		}
		return null;
	}

	private Date fromStringToDate(String value,Integer geocodeId)	{
		if(value != null && !"".equals(value))	{
			try {
				return dateFormat.parse(value);
			} catch (ParseException exception) {
				logger.log(Level.WARNING," Could Not Parse Data For Geocode Id " + geocodeId,exception);
				return null;
			}
		}
		return null;
	}

}
