package com.model;

import java.util.List;

/**
 * @author Vik Mortier
 *
 */

public class Route {
	
	private double afstand;
	private List<Integer> perrons;
	private List<Station> stations;
	//Kunnen we hiervoor een map gebruiken met de stations als key en het perron dan als value?
	
	public Route() {
		afstand = 0;		
	}
	
	public Route(double afstand, List<Integer> perrons, List<Station> stations) {
		this.afstand = afstand;
		this.perrons = perrons;
		this.stations = stations;
	}
	
	public double getAfstand() {
		return afstand;
	}
	public void setAfstand(double afstand) {
		this.afstand = afstand;
	}
	public List<Integer> getPerrons() {
		return perrons;
	}
	public void setPerrons(List<Integer> perrons) {
		this.perrons = perrons;
	}
	public List<Station> getStations() {
		return stations;
	}
	public void setStations(List<Station> stations) {
		this.stations = stations;
	}
	
	@Override
	public String toString() {
		String s="Route [afstand= " + afstand + "\n stations:";
		for(int i=0;i<stations.size();i++){
			s+=stations.get(i).getNaam() + " op perron " + perrons.get(i);
		}
		s+="]";
		return s;
	}

	
}
