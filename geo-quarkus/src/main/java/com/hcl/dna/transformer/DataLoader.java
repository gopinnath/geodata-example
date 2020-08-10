package com.hcl.dna.transformer;
/*
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.vertx.mutiny.mysqlclient.MySQLPool;
import io.vertx.mutiny.sqlclient.Tuple;

@ApplicationScoped
public class DataLoader {
	
	@Inject
	private MySQLPool client;
	
	private static final Logger logger = Logger.getLogger(DataLoader.class.getName());
	
	private static final String SQL_INSERT = "INSERT INTO GEOCODE VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Incoming("geocode-topic")
	public void listenAndLoadGeoData(String tsvString)	{
		client.preparedQuery(SQL_INSERT).execute(buildTuple(tsvString)).onItem().apply(rowSet -> {
			logger.info("Inserted Row " + rowSet.iterator().next().getColumnName(0));
			return rowSet.iterator().next().getColumnName(0);
		});
	}

	private Tuple buildTuple(String tsvString)	{
		String[] geoData = tsvString.split("\t");
		return Tuple.of(new Object[] { Integer.valueOf(geoData[0]),
				geoData[1],
				geoData[2],
				geoData[3],
				Double.valueOf(geoData[4]),
				Double.valueOf(geoData[5]),
				geoData[6],
				geoData[7],
				geoData[8],
				geoData[9],
				fromStringToBigInt(geoData[14]),
				fromStringToInteger(geoData[15]),
				geoData[17],
				fromStringToDate(geoData[18],geoData[0])});
		
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

	private Date fromStringToDate(String value,String geocodeId)	{
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
*/