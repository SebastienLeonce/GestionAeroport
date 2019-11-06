package com.gestion.aeroport.avion;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import com.gestion.aeroport.passager.Passager;

public abstract class Avion {
	
	private String modele;
	private int capaciteMax;
	private int capacite = 0;
	private float poidsBagagesMax;
	private float poidsBagages = 0;
	private float volCarburant;
	private int nbPilotesMin;
	private int nbPilotes = 0;
	
	private List<Passager> passagers = new ArrayList<Passager>();
	
	public Avion(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes) {
		this.modele = modele;
		this.capaciteMax = capacite;
		this.poidsBagagesMax = poidsBagagesMax;
		this.volCarburant = volCarburant;
		this.nbPilotesMin = nbPilotes;
	}
}
