package com.model;
/**
*
* @author Ilias El Mesaoudi
**/


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import java.util.Date;

public class VerlorenVoorwerp {

	private int voorwerp_id;
	private String naam;
	private String omschrijving;
	private Date datum;
	private String station;
	private boolean aanwezig;
<<<<<<< HEAD
	private int persoon_id;
=======

>>>>>>> refs/heads/master
	@Override
	public String toString() {
<<<<<<< HEAD
		return "VerlorenVoorwerp voorwerpid=" + voorwerp_id + ", naam=" + naam + ", omschrijving=" + omschrijving
				+ ", datum=" + datum + ", persoon=" + persoon_id+ ", aanwezig=" + aanwezig +", station= " + station;
=======
		return "VerlorenVoorwerp voorwerpid=" + voorwerpid + ", naam=" + naam + ", omschrijving=" + omschrijving
				+ ", datum=" + datum + ", station=" + station + ",aanwezig=" + aanwezig;
>>>>>>> refs/heads/master
	}
<<<<<<< HEAD
	public VerlorenVoorwerp(){
	}
	public VerlorenVoorwerp(int voorwerpid, String naam, String omschrijving, Date datum, String station,
			boolean aanwezig) {
		super();
		this.voorwerp_id = voorwerpid;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.station= station;
		this.aanwezig = aanwezig;
	}
	public int getVoorwerpid() {
		return voorwerp_id;
	}
	public void setVoorwerpid(int voorwerpid) {
		this.voorwerp_id = voorwerpid;
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
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String station) {
		this.station= station;
	}
	public boolean isAanwezig() {
=======

	public boolean getAanwezig() {
>>>>>>> refs/heads/master
		return aanwezig;
	}

	public void setAanwezig(boolean aanwezig) {
		this.aanwezig = aanwezig;
	}

	public VerlorenVoorwerp() {
	}

	public VerlorenVoorwerp(int voorwerpid, String naam, String omschrijving, String datum,
			String station) {
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
