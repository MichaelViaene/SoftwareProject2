package com.model;
/**
 * 
 * @author Michael
 *
 */
public class Korting {
	private int korting_id, percentage;
	private String naam, beschrijving;
	private boolean actief;
	
	public Korting(int korting_id,String naam,String beschrijving,int percentage,boolean actief){
		this.korting_id=korting_id;
		this.naam=naam;
		this.beschrijving=beschrijving;
		this.percentage=percentage;
		this.actief=actief;
	}
	
	public Korting(String naam,String beschrijving, int percentage, boolean actief){
		this.naam=naam;
		this.beschrijving=beschrijving;
		this.percentage=percentage;
		this.actief=actief;
	}
	
	public Korting() {
		super();
	}
	
	public int getKorting() {
		return korting_id;
	}
	public void setKorting(int korting) {
		this.korting_id = korting;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public String getNaam() {
		return naam;
	}
	public void setNaam(String naam) {
		this.naam = naam;
	}
	public String getBeschrijving() {
		return beschrijving;
	}
	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}
	public boolean isActief() {
		return actief;
	}
	public void setActief(boolean actief) {
		this.actief = actief;
	}
	
	public String toString(){
		String s;
		s=naam+ ", "+percentage+"%";
		return s;
	}
}
