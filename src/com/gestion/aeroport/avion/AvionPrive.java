package com.gestion.aeroport.avion;

public class AvionPrive extends Avion {
	
	private String proprietaire;

	public AvionPrive(int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes, String proprietaire) {
		super(capacite, poidsBagagesMax, volCarburant, nbPilotes);
		
		this.proprietaire = proprietaire;
	}
}
