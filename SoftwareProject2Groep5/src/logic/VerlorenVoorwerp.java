package logic;

public class VerlorenVoorwerp {

	private int voorwerpid;
	private String naam;
	private String omschrijving;
	private String datum;
	private int persoonid;
	private boolean aanwezig;
	
	@Override
	public String toString() {
		return "VerlorenVoorwerp voorwerpid=" + voorwerpid + ", naam=" + naam + ", omschrijving=" + omschrijving
				+ ", datum=" + datum + ", persoonid=" + persoonid + ", aanwezig=" + aanwezig ;
	}
	public VerlorenVoorwerp(){
	}
	public VerlorenVoorwerp(int voorwerpid, String naam, String omschrijving, String datum, int persoonid,
			boolean aanwezig) {
		super();
		this.voorwerpid = voorwerpid;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.datum = datum;
		this.persoonid = persoonid;
		this.aanwezig = aanwezig;
	}
	public int getVoorwerpid() {
		return voorwerpid;
	}
	public void setVoorwerpid(int voorwerpid) {
		this.voorwerpid = voorwerpid;
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
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public int getPersoonid() {
		return persoonid;
	}
	public void setPersoonid(int persoonid) {
		this.persoonid = persoonid;
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
