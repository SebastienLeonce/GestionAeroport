package com.gestion.aeroport.avion;

public abstract class Avion {
	
	private int capacite;
	private float poidsBagagesMax;
	private float volCarburant;
	private int nbPilotes;
	
	public Avion(int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes) {
		this.capacite = capacite;
		this.poidsBagagesMax = poidsBagagesMax;
		this.volCarburant = volCarburant;
		this.nbPilotes = nbPilotes;
	}
}
