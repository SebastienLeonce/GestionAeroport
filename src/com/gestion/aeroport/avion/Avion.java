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
	
	private String modele;
	private int capaciteMax;
	private int capacite = 0;
	private float poidsBagagesMax;
	private float poidsBagages = 0;
	private float volCarburant;
	private int nbPilotesMin;
	private int nbPilotes = 0;
	
	private ArrayList<Passager> passagers = new ArrayList<Passager>();
	private ArrayList<Pilote>   pilotes = new ArrayList<Pilote>();
	
	public Avion(String modele, int capacite, float poidsBagagesMax, float volCarburant, int nbPilotes) {
		this.modele = modele;
		this.capaciteMax = capacite;
		this.poidsBagagesMax = poidsBagagesMax;
		this.volCarburant = volCarburant;
		this.nbPilotesMin = nbPilotes;
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

	
	public static ArrayList<Avion> generate(int n) throws IOException{
		URL url = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/plane.dat");
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
				
				//A FAIRE Variables Globales
				
				int capacite = 10 + (int)(Math.random() * ((50-10) + 1 ));
				int nbPilotes = 10 + (int)(Math.random() * ((50-10) + 1 ));
				int nbPilotes = 10 + (int)(Math.random() * ((50-10) + 1 ));
				int nbPilotes = 10 + (int)(Math.random() * ((50-10) + 1 ));
				
				
				
				avions.add(new Avion());
			}
			else {
				break;
			}
		}
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
