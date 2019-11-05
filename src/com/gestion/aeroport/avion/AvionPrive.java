package com.gestion.aeroport.avion;

public class AvionPrive extends Avion {
	
	private String proprietaire;

	public AvionPrive(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes, String proprietaire) {
		super(modele, capacite, poidsBagagesMax, volCarburant, nbPilotes);
		
		this.proprietaire = proprietaire;
	}
}
