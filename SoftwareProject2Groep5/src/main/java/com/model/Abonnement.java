package com.model;

import java.time.LocalDate;


/**
 * @author Vik Mortier
 *
 */
public class Abonnement {

	private int abonnement_id;
	private int klant_id;
	private int klasse;
	private int type;
	private int actief;
	
	private double prijs;
	
	private Route route;
	private String beginStation;
	private String eindStation;
	
	private LocalDate beginDatum;
	private LocalDate eindDatum;

	public Abonnement() {
		super();
	}

	public Abonnement(int abonnement_id, int klant_id, int klasse, int type, int actief, double prijs, Route route, String beginStation, String eindStation, LocalDate beginDatum, LocalDate eindDatum) {
		this.abonnement_id = abonnement_id;
		this.klant_id = klant_id;
		this.klasse = klasse;
		this.type = type;
		this.actief = actief;
		this.prijs = prijs;
		this.route = route;
		this.beginStation = beginStation;
		this.eindStation = eindStation;
		this.beginDatum = beginDatum;
		this.eindDatum = eindDatum;
	}

	public int getAbonnement_id() {
		return abonnement_id;
	}

	public void setAbonnement_id(int abonnement_id) {
		this.abonnement_id = abonnement_id;
	}

	public int getKlant_id() {
		return klant_id;
	}

	public void setKlant_id(int klant_id) {
		this.klant_id = klant_id;
	}

	public int getKlasse() {
		return klasse;
	}

	public void setKlasse(int klasse) {
		this.klasse = klasse;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getActief() {
		return actief;
	}

	public void setActief(int actief) {
		this.actief = actief;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getBeginStation() {
		return beginStation;
	}

	public void setBeginStation(String beginStation) {
		this.beginStation = beginStation;
	}

	public String getEindStation() {
		return eindStation;
	}

	public void setEindStation(String eindStation) {
		this.eindStation = eindStation;
	}

	public LocalDate getBeginDatum() {
		return beginDatum;
	}

	public void setBeginDatum(LocalDate beginDatum) {
		this.beginDatum = beginDatum;
	}

	public LocalDate getEindDatum() {
		return eindDatum;
	}

	public void setEindDatum(LocalDate eindDatum) {
		this.eindDatum = eindDatum;
	}

	@Override
	public String toString() {
		return "Abonnement [abonnement_id=" + abonnement_id + ", klant_id=" + klant_id + ", klasse=" + klasse
				+ ", type=" + type + ", prijs=" + prijs + ", route=" + route + ", beginDatum=" + beginDatum
				+ ", eindDatum=" + eindDatum + "]";
	}
	
	
	
	
}
