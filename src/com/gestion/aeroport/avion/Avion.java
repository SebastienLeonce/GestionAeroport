package com.gestion.aeroport.avion;

public abstract class Avion {
	
	private String modele;
	private int capacite;
	private float poidsBagagesMax;
	private float volCarburant;
	private int nbPilotes;
	
	public Avion(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes) {
		this.modele = modele;
		this.capacite = capacite;
		this.poidsBagagesMax = poidsBagagesMax;
		this.volCarburant = volCarburant;
		this.nbPilotes = nbPilotes;
	}
}
