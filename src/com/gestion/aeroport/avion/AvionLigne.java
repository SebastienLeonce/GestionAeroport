package com.gestion.aeroport.avion;

public class AvionLigne extends Avion {
	
	private int nbPersonnels;
	private String compagnie;

	public AvionLigne(int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes, int nbPersonnels, String compagnie) {
		super(capacite, poidsBagagesMax, volCarburant, nbPilotes);
		
		this.nbPersonnels = nbPersonnels;
		this.compagnie = compagnie;
	}
}
