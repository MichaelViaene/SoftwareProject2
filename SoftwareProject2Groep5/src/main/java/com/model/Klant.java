package com.model;

import java.time.LocalDate;

/**
 *
 * @author Ilias El Mesaoudi created 18-11-2016
 **/

public class Klant {

	private int klantid;
	private int adresid;
	private LocalDate geboortedatum;
	private String gsmnummer;
	private String commentaar;
	private boolean actief;
	private String naam;
	private String voornaam;

	public Klant(int adresid, LocalDate geboortedatum, String gsmnummer, String commentaar, boolean actief, String naam,
			String voornaam) {
		super();
		this.adresid = adresid;
		this.geboortedatum = geboortedatum;
		this.gsmnummer = gsmnummer;
		this.commentaar = commentaar;
		this.actief = actief;
		this.naam = naam;
		this.voornaam = voornaam;
	}
	public Klant() {

	}


	public int getKlantid() {
		return klantid;
	}

	public void setKlantid(int klantid) {
		this.klantid = klantid;
	}

	public int getAdresid() {
		return adresid;
	}

	public void setAdresid(int adresid) {
		this.adresid = adresid;
	}

	public LocalDate getGeboortedatum() {
		return geboortedatum;
	}

	public void setGeboortedatum(LocalDate geboortedatum) {
		this.geboortedatum = geboortedatum;
	}

	public String getGsmnummer() {
		return gsmnummer;
	}

	public void setGsmnummer(String gsmnummer) {
		this.gsmnummer = gsmnummer;
	}

	public String getCommentaar() {
		return commentaar;
	}

	public void setCommentaar(String commentaar) {
		this.commentaar = commentaar;
	}

	public boolean isActief() {
		return actief;
	}

	public void setActief(boolean actief) {
		this.actief = actief;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

}
