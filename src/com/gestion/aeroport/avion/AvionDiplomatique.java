package com.gestion.aeroport.avion;

import com.gestion.aeroport.passager.Diplomate;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;

public class AvionDiplomatique extends Avion {
	//dans le addPassager verifier  passagager diplo
	//dans le addPersonnel verifier passeport du pays
	
	private String etat;
	
	public AvionDiplomatique(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes, String etat) {
		super(modele, capacite, poidsBagagesMax, volCarburant, nbPilotes);
		
		this.etat = etat;
		
	}
	
	public boolean ajouterPassager (Diplomate passager) {
		return super.ajouterPassager(passager);
	}
	
	public void ajouterPilote (Pilote pilote) {
		if (pilote.getNationalite() == this.etat) {
			super.ajouterPilote(pilote);
		}
	}
	
	/*public void ajouterPersonnel (Personnel personnel) {
		this.nbPersonnels++;
		personnels.add(personnel);
	}*/
}
