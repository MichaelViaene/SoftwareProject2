package com.model;

import java.util.Date;

public class VerlorenVoorwerp {

	private int voorwerp_id;
	private String naam;
	private String omschrijving;
	private Date datum;
	private String station;
	private boolean aanwezig;
	private int persoon_id;
	@Override
	public String toString() {
		return "VerlorenVoorwerp voorwerpid=" + voorwerp_id + ", naam=" + naam + ", omschrijving=" + omschrijving
				+ ", datum=" + datum + ", persoon=" + persoon_id+ ", aanwezig=" + aanwezig +", station= " + station;
	}
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
		return aanwezig;
	}
	public void setAanwezig(boolean aanwezig) {
		this.aanwezig = aanwezig;
	}
	public boolean getAanwezig(){
		return aanwezig;
	}
	
}
