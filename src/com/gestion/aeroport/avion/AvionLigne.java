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
	
	public AvionLigne(String modele, int capacite, float poidsBagagesMax, float volCarburant,float consommationCarburant, int nbPilotes, int espacement, int nbPersonnels, Compagnie compagnie) {
		super(modele, capacite, poidsBagagesMax, volCarburant, consommationCarburant, nbPilotes, espacement);
		
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
	
	
	public int getNbPersonnelsMin() {
		return nbPersonnelsMin;
	}

	public void setNbPersonnelsMin(int nbPersonnelsMin) {
		this.nbPersonnelsMin = nbPersonnelsMin;
	}

	public int getNbPersonnels() {
		return nbPersonnels;
	}

	public List<Personnel> getPersonnels() {
		return personnels;
	}

	@Override
	public String toString() {
		return "Avion de Ligne :  " +super.toString();
	}
	
}
