package com.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class VerlorenVoorwerp {

	private int voorwerpid;
	private String naam;
	private String omschrijving;
	private String datum;
	private String station;
	private boolean aanwezig;

	@Override
	public String toString() {
		return "VerlorenVoorwerp voorwerpid=" + voorwerpid + ", naam=" + naam + ", omschrijving=" + omschrijving
				+ ", datum=" + datum + ", persoonnaam=" + station + ",aanwezig=" + aanwezig;
	}

	public boolean isAanwezig() {
		return aanwezig;
	}

	public void setAanwezig(boolean aanwezig) {
		this.aanwezig = aanwezig;
	}

	public VerlorenVoorwerp() {
	}

	public VerlorenVoorwerp(int voorwerpid, String naam, String omschrijving, String datum,
			String station) {
		super();
		this.voorwerpid = voorwerpid;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.station = station;
	}

	public VerlorenVoorwerp(String naam, String omschrijving, String datum, String station) {
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.station = station;
	}

	public int getVoorwerpid() {
		return voorwerpid;
	}

	public void setVoorwerpid(int voorwerpid) {
		this.voorwerpid = voorwerpid;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getStation() {
		return station;
	}

	public void setStation(String station) {
		this.station = station;
	}
}
