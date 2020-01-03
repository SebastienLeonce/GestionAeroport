package com.gestion.aeroport.avion;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.gestion.aeroport.aeroport.Compagnie;
import com.gestion.aeroport.passager.Passager;
import com.gestion.aeroport.passager.Pilote;

public abstract class Avion {
	
	public static final int CAPACITE_MIN = 100;
	public static final int CAPACITE_MAX = 300;
	public static final int CARBURANT_MIN = 200;
	public static final int CARBURANT_MAX = 600;
	public static final int CONSOMMATION_MIN = 1;
	public static final int CONSOMMATION_MAX = 5;	
	public static final int PILOTE_MIN = 1;
	public static final int PILOTE_MAX = 3;
	public static final int PERSONNEL_MIN = 5;
	public static final int PERSONNEL_MAX = 15;
	
	/**
	 * True : utilisable dans le Programme
	 * False : Actuellement utilisé dans le Programme
	 */
	private boolean dispo;
	
	private String modele;
	private int capaciteMax;
	private int capacite = 0;
	private float poidsBagagesMax;
	private float poidsBagages = 0;
	private float volCarburant;
	private float consomamtionCarburant;
	private int nbPilotesMin;
	private int nbPilotes = 0;
	
	private ArrayList<Passager> passagers = new ArrayList<Passager>();
	private ArrayList<Pilote>   pilotes = new ArrayList<Pilote>();
	
	public Avion(String modele, int capacite, float poidsBagagesMax, float volCarburant, float consommationCarburant, int nbPilotes) {
		this.modele = modele;
		this.capaciteMax = capacite;
		this.poidsBagagesMax = poidsBagagesMax;
		this.volCarburant = volCarburant;
		this.consomamtionCarburant = consommationCarburant;
		this.nbPilotesMin = nbPilotes;
		this.dispo = true;
	}
		
	public boolean ajouterPassager (Passager passager) {
		if (this.capacite < this.capaciteMax) {
			this.capacite++;
			passagers.add(passager);
			return true;
		}
		return false;
	}
	
	public void ajouterPilote (Pilote pilote) {
		this.nbPilotes++;
		pilotes.add(pilote);
	}
	
	@Override
	public String toString() {
		return modele + "\n" +
				"\tCarburant restant : " + volCarburant + "\n" +
				"\tConsommation carburant : " + consomamtionCarburant + "\n";
	}

	
	
	
	//O prive 1 ligne 2 diplo
	public static ArrayList<Avion> generate(int n, Compagnie c) throws IOException{
		URL url = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/planes.dat");
		ArrayList<Avion> avions = new ArrayList<Avion>();
		Scanner sc = new Scanner(url.openStream());
		
		String[] data;
		
		if(sc.hasNextLine()) {
			sc.nextLine(); //Première ligne inutile
		}
		else {
			throw new IOException("File Empty");
		}
		
		for (int i = 0 ; i < n ; i++) {  
			if(sc.hasNextLine()) {
				data = sc.nextLine().split(","); 
				
				int capacite = CAPACITE_MIN + (int)(Math.random() * ((CAPACITE_MAX-CAPACITE_MIN) + 1 ));
				int nbPilotes = PILOTE_MIN + (int)(Math.random() * ((PILOTE_MAX-PILOTE_MIN) + 1 ));
				float poidsBagagesMax = capacite*40;
				float volCarburant = CARBURANT_MIN + (int)(Math.random() * ((CARBURANT_MAX-CARBURANT_MIN) + 1 ));
				float consommationCarburant= CONSOMMATION_MIN + (int)(Math.random() * ((CONSOMMATION_MAX-CONSOMMATION_MIN) + 1 ));
				
				
				if (c.getNom().equals("\"Private flight\"")) {
					avions.add(new AvionPrive(data[0], capacite, poidsBagagesMax, volCarburant, consommationCarburant, nbPilotes, new Passager("null")));
				} else {
					int nbPersonnels = PERSONNEL_MIN + (int)(Math.random() * ((PERSONNEL_MAX-PERSONNEL_MIN) + 1 ));
					avions.add(new AvionLigne(data[0], capacite, poidsBagagesMax, volCarburant, consommationCarburant, nbPilotes, nbPersonnels, c));
				}
			}
			else {
				break;
			}
		}
		return avions;
	}
	
	
	public boolean isDispo() {
		return dispo;
	}

	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}

	public float getConsomamtionCarburant() {
		return consomamtionCarburant;
	}

	public void setConsomamtionCarburant(float consomamtionCarburant) {
		this.consomamtionCarburant = consomamtionCarburant;
	}

	public void setVolCarburant(float volCarburant) {
		this.volCarburant = volCarburant;
	}

	public String getModele() {
		return modele;
	}

	public int getCapaciteMax() {
		return capaciteMax;
	}

	public int getCapacite() {
		return capacite;
	}
	
	public int setCapacite() {
		return capacite+1;
	}

	public float getPoidsBagagesMax() {
		return poidsBagagesMax;
	}

	public float getPoidsBagages() {
		return poidsBagages;
	}

	public float getVolCarburant() {
		return volCarburant;
	}

	public int getNbPilotesMin() {
		return nbPilotesMin;
	}

	public int getNbPilotes() {
		return nbPilotes;
	}

	public ArrayList<Passager> getPassagers() {
		return passagers;
	}

	public ArrayList<Pilote> getPilotes() {
		return pilotes;
	}
	
	
}
