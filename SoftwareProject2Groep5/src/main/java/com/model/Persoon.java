package com.model;

import java.util.Date;

public class Persoon {
	private String naam, voornaam,gsmnummer, commentaar;
	private Date geboortedatum;
	private int adres_id,persoon_id;
	private boolean actief;
	
	public Persoon(){
		voornaam=gsmnummer=commentaar=naam="";
		actief=true;
	}
	
	public Persoon(String naam, String voornaam, String gsmnummer, String commentaar, Date geboortedatum,int adres_id, int persoon_id,boolean actief){
		this.naam=naam;
		this.voornaam=voornaam;
		this.gsmnummer=gsmnummer;
		this.commentaar=commentaar;
		this.geboortedatum=geboortedatum;
		this.adres_id=adres_id;
		this.persoon_id=persoon_id;
		this.actief=actief;
	}
	@Override
	public String toString(){
		String s="Persoon id= "+persoon_id+"\n";
		s+="Naam= "+naam+"\n";
		s+="Voornaam= "+voornaam+"\n";
		s+="Gsmnummer= "+gsmnummer+"\n";
		s+="Commentaar= "+commentaar+"\n";
		s+="Geboortedatum= "+geboortedatum+"\n";
		s+="Adres id= "+adres_id+"\n";
		s+="Actief= "+actief+"\n";
		return s;
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
	public Date getGeboortedatum() {
		return geboortedatum;
	}
	public void setGeboortedatum(Date geboortedatum) {
		this.geboortedatum = geboortedatum;
	}
	public int getAdres_id() {
		return adres_id;
	}
	public void setAdres_id(int adres_id) {
		this.adres_id = adres_id;
	}
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
	}

	public int getPersoon_id() {
		return persoon_id;
	}

	public void setPersoon_id(int persoon_id) {
		this.persoon_id = persoon_id;
	}
	
	
}
