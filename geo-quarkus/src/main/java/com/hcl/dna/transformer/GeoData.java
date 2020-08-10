package com.hcl.dna.transformer;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GEOCODE")
public class GeoData {
	
	@Id
	@Column(name = "GEOCODE_ID")
	private Integer geocodeId;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ASCII_NAME")
	private String asciiName;
	
	@Column(name = "ALTERNATE_NAMES")
	private String alternateNames;
	
	@Column(name = "LATITUDE")
	private Double latitude;
	
	@Column(name = "LONGITUDE")
	private Double longitude;
	
	@Column(name = "FEATURE_CLASS")
	private String featureClass;
	
	@Column(name = "FEATURE_CODE")
	private String featureCode;
	
	@Column(name = "COUNTRY_CODE")
	private String countryCode;
	
	@Column(name = "ALT_COUNTRY")
	private String altCountryCode;
	
	@Column(name = "POPULATION")
	private BigInteger population;
	
	@Column(name = "ELEVATION")
	private Integer elevation;
	
	@Column(name = "TIME_ZONE")
	private String timezone;
	
	@Column(name = "MODIFICATION_DATE")
	private Date modificationDate;

	public Integer getGeocodeId() {
		return geocodeId;
	}

	public void setGeocodeId(Integer geocodeId) {
		this.geocodeId = geocodeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAsciiName() {
		return asciiName;
	}

	public void setAsciiName(String asciiName) {
		this.asciiName = asciiName;
	}

	public String getAlternateNames() {
		return alternateNames;
	}

	public void setAlternateNames(String alternateNames) {
		this.alternateNames = alternateNames;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getFeatureClass() {
		return featureClass;
	}

	public void setFeatureClass(String featureClass) {
		this.featureClass = featureClass;
	}

	public String getFeatureCode() {
		return featureCode;
	}

	public void setFeatureCode(String featureCode) {
		this.featureCode = featureCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getAltCountryCode() {
		return altCountryCode;
	}

	public void setAltCountryCode(String altCountryCode) {
		this.altCountryCode = altCountryCode;
	}

	public BigInteger getPopulation() {
		return population;
	}

	public void setPopulation(BigInteger population) {
		this.population = population;
	}

	public Integer getElevation() {
		return elevation;
	}

	public void setElevation(Integer elevation) {
		this.elevation = elevation;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	
	
}
