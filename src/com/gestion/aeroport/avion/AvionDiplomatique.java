package com.gestion.aeroport.avion;

public class AvionDiplomatique extends Avion {
	//dans le addPassager verifier  passagager diplo
	//dans le addPersonnel verifier passeport du pays
	
	private String etat;
	
	public AvionDiplomatique(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes, String etat) {
		super(modele, capacite, poidsBagagesMax, volCarburant, nbPilotes);
		
		this.etat = etat;
		
	}

}
