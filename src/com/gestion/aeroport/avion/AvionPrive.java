package com.gestion.aeroport.avion;

import com.gestion.aeroport.passager.Passager;

public class AvionPrive extends Avion {
	
	private Passager proprietaire;

	public AvionPrive(String modele, int capacite, float poidsBagagesMax, float volCarburant, float consommationCarburant, int nbPilotes , int espacement, Passager proprietaire) {
		super(modele, capacite, poidsBagagesMax, volCarburant,consommationCarburant, nbPilotes, espacement);
		
		this.proprietaire = proprietaire;
	}

	@Override
	public String toString() {
		return "Avion Privé : " + super.toString() +"\n proprietaire:" + proprietaire ;
	}
	
	
}
