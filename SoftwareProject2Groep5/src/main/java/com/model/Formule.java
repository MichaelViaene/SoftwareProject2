package com.model;



public class Formule {
	
	public enum FormuleType {
	TICKET, ABONNEMENT
	}
	
	int id;
	String formule;
	boolean active;
	boolean online;
	int medewerkerID;
	FormuleType type;
	
	public Formule(){
		formule=null;
		active=false;
	}
	
	public Formule(int id, String formule, boolean active, boolean online, int medewerkerID, FormuleType type){
		this.id=id;
		this.formule=formule;
		this.active=active;
		this.online=online;
		this.medewerkerID=medewerkerID;
		this.type=type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public int getMedewerkerID() {
		return medewerkerID;
	}

	public void setMedewerkerID(int medewerkerID) {
		this.medewerkerID = medewerkerID;
	}

	public FormuleType getType() {
		return type;
	}

	public void setType(FormuleType type) {
		this.type = type;
	}

	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	

}
