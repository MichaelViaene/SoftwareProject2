package com.model;

public class Medewerker {
	private int medewerker_id;
	private int adres_id;
	private int login_id;
	private boolean actief;
	private String voornaam;
	private String naam;
	
	public Medewerker(){
		voornaam=naam="";
		actief=true;	
	}
	
	public Medewerker(int medewerker_id,int adres_id,int login_id,boolean actief,String voornaam,String naam){
		this.medewerker_id=medewerker_id;
		this.adres_id=adres_id;
		this.login_id=login_id;
		this.actief=actief;
		this.voornaam=voornaam;
		this.naam=naam;
	}
	
	public String toString(){
		String s="Medewerker id= " + medewerker_id + "\n";
		s+="Adres id= " + adres_id+ "\n";
		s+="Login id= " + login_id + "\n";
		s+="Actief= " + actief+ "\n";
		s+="voornaam= " + voornaam+ "\n";
		s+="naam= " + naam+ "\n";
		return s;	
	}
	
	public int getMedewerker_id() {
		return medewerker_id;
	}
	public void setMedewerker_id(int medewerker_id) {
		this.medewerker_id = medewerker_id;
	}
	public int getAdres_id() {
		return adres_id;
	}
	public void setAdres_id(int adres_id) {
		this.adres_id = adres_id;
	}
	public int getLogin_id() {
		return login_id;
	}
	public void setLogin_id(int login_id) {
		this.login_id = login_id;
	}
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
}
