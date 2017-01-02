package com.persistentie;

import java.util.List;

import com.database.FormuleDAO;
import com.database.KortingDAO;
import com.model.Formule;
import com.model.Korting;
import com.model.Routes.Stationinfo;

// deze klasse staat in voor het bijhouden van data afkomstig uit de database, die gebruikt wordt wanneer er geen informatie online kan gevonden worden.
public class Cache {
	private boolean binnengehaald = false;
	private List<Korting> kortingen = null;
	private Formule formule = null;
	private List<Stationinfo> stationsInfo = null;
	
	public Cache(){
		setKortingen(KortingDAO.getActieveKortingen());
		//TODO: IMPLEMENTEREN FUNCTIES
		setFormule(FormuleDAO.getFormuleActive());
		setStationsInfo(GtfsCSV.getStationsLijst());
		if(kortingen != null && formule != null){
			binnengehaald = true;
		}
	}

	private List<Stationinfo> getStationsInfo() {
		return stationsInfo;
	}

	private void setStationsInfo(List<Stationinfo> stationsInfo) {
		this.stationsInfo = stationsInfo;
	}

	private boolean isBinnengehaald() {
		return binnengehaald;
	}

	private void setBinnengehaald(boolean binnengehaald) {
		this.binnengehaald = binnengehaald;
	}

	private List<Korting> getKortingen() {
		return kortingen;
	}

	private void setKortingen(List<Korting> kortingen) {
		this.kortingen = kortingen;
	}

	private Formule getFormule() {
		return formule;
	}

	private void setFormule(Formule formule) {
		this.formule = formule;
	}
}
