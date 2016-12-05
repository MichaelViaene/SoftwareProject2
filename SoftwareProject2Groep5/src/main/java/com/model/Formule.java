package com.model;

public class Formule {
	String formule;
	boolean active;
	
	public Formule(){
		formule=null;
		active=false;
	}
	
	public Formule(String formule, boolean active){
		this.formule=formule;
		this.active=active;
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
