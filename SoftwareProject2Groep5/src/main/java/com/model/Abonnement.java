/**
 * 
 */
package com.model;

import java.util.Date;

/**
 * @author Vik Mortier
 *
 */
public class Abonnement {

	private int abonnement_id;
	private int klant_id;
	private int klasse;
	private int type;
	
	private double prijs;
	
	private Route route;
	
	private Date beginDatum;
	private Date eindDatum;
	
	
	
	public Abonnement() {
		super();
	}
	
	
	public Abonnement(int abonnement_id, int klant_id, int klasse, int type, double prijs, Route route, Date beginDatum,
			Date eindDatum) {
		super();
		this.abonnement_id = abonnement_id;
		this.klant_id = klant_id;
		this.klasse = klasse;
		this.type = type;
		this.prijs = prijs;
		this.route = route;
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
	public Date getBeginDatum() {
		return beginDatum;
	}
	public void setBeginDatum(Date beginDatum) {
		this.beginDatum = beginDatum;
	}
	public Date getEindDatum() {
		return eindDatum;
	}
	public void setEindDatum(Date eindDatum) {
		this.eindDatum = eindDatum;
	}
	@Override
	public String toString() {
		return "Abonnement [abonnement_id=" + abonnement_id + ", klant_id=" + klant_id + ", klasse=" + klasse
				+ ", type=" + type + ", prijs=" + prijs + ", route=" + route + ", beginDatum=" + beginDatum
				+ ", eindDatum=" + eindDatum + "]";
	}
	
	
	
	
}
