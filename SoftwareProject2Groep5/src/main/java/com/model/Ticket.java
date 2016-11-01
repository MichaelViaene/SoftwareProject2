/**
 * 
 */
package com.model;

import java.util.Date;

/**
 * @author Vik Mortier
 *
 */

public class Ticket {
	
	private int ticket_id;
	private int klasse;
	private int type;
	private int medewerker_id;
	private double prijs;

	private Date datumAankoop;
	private Date datumHeen;
	private Date datumTerug;
	
	private Route route;
	
	public Ticket() {
		prijs=0;
	}

	public Ticket(int ticket_id, int klasse, int type, double prijs, Date datumAankoop, Date datumHeen, Date datumTerug,
			Route route) {
		this.ticket_id = ticket_id;
		this.klasse = klasse;
		this.type = type;
		this.prijs = prijs;
		this.datumAankoop = datumAankoop;
		this.datumHeen = datumHeen;
		this.datumTerug = datumTerug;
		this.route = route;
	}

	public Date getDatumAankoop() {
		return datumAankoop;
	}

	public void setDatumAankoop(Date datumAankoop) {
		this.datumAankoop = datumAankoop;
	}

	public Date getDatumHeen() {
		return datumHeen;
	}

	public void setDatumHeen(Date datumHeen) {
		this.datumHeen = datumHeen;
	}

	public Date getDatumTerug() {
		return datumTerug;
	}

	public void setDatumTerug(Date datumTerug) {
		this.datumTerug = datumTerug;
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
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

	@Override
	public String toString() {
		return "Ticket [ticket_id=" + ticket_id + ", klasse=" + klasse + ", type=" + type + ", prijs=" + prijs
				+ ", datumAankoop=" + datumAankoop + ", datumHeen=" + datumHeen + ", datumTerug=" + datumTerug
				+ ", route=" + route + ", medewerker=" + medewerker_id + "]";
	}

	public int getMedewerker_id() {
		return medewerker_id;
	}

	public void setMedewerker_id(int medewerker_id) {
		this.medewerker_id = medewerker_id;
	}

	
	
	
}
