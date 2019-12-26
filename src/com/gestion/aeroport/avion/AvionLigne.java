package com.gestion.aeroport.avion;

import java.util.ArrayList;
import java.util.List;

import com.gestion.aeroport.aeroport.Compagnie;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;

public class AvionLigne extends Avion {
	
	private int nbPersonnelsMin;
	private int nbPersonnels = 0;
	private Compagnie compagnie;

	private List<Personnel>   personnels = new ArrayList<Personnel>();
	
	public AvionLigne(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes, int nbPersonnels, Compagnie compagnie) {
		super(modele, capacite, poidsBagagesMax, volCarburant, nbPilotes);
		
		this.nbPersonnelsMin = nbPersonnels;
		this.compagnie = compagnie;
	}
	
	public void ajouterPersonnel (Personnel personnel) {
		this.nbPersonnels++;
		personnels.add(personnel);
	}//mettre dans la classe abstract
	
	public boolean Voler () {
		if (getNbPilotesMin() <= getNbPilotes()) {
			if (this.nbPersonnelsMin <= nbPersonnels) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return super.toString() + 
				"nbPersonnelsMin=" + nbPersonnelsMin + 
				"\nnbPersonnels=" + nbPersonnels + 
				"\npersonnels=" + personnels + "]";
	}
	
}
