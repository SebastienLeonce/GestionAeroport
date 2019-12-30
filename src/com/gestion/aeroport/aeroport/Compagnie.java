package com.gestion.aeroport.aeroport;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;

import java.util.ArrayList;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class Compagnie {
	
	private String nom;
	private String nationalite;
	private ArrayList<Avion>  flotte;
	private ArrayList<Pilote> pilotes;
	private ArrayList<Personnel> personnels;
	
	public Compagnie(String nom, String nationalite) {
		this.nom = nom;
		this.nationalite = nationalite;
	}
	
	public Compagnie(String nom, String nationalite, ArrayList<Avion> flotte, ArrayList<Pilote> pilotes, ArrayList<Personnel> personnels) {
		this.nom = nom;
		this.nationalite = nationalite;
		this.flotte = flotte;
		this.pilotes = pilotes;
		this.personnels = personnels;
	}
	
	
	@Override
	public String toString() {
		return "Compagnie: {" + "\n"+ 
					"\tnom: " + nom + "\n"+ 
					"\tnationalite: " + nationalite + "\n"+ 
					"\tflotte=" + flotte + "\n"+ 
					"\tpilotes=" + pilotes + "\n"+ 
					"\tpersonnels=" + personnels + "\n"+ 
				"}";
	}


	public String getNom() {
		return nom;
	}

	
	public static ArrayList<Compagnie> generate(int n) throws IOException {
		URL url = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/airlines.dat");
		ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
		Scanner sc = new Scanner(url.openStream());
		String[] data;
		
		if(sc.hasNextLine()) { sc.nextLine(); } else { throw new IOException("File Empty"); } //Première ligne inutile
		
		for (int i = 0 ; i < n ; i++) { 
			
			if(sc.hasNextLine()) {
				
				data = sc.nextLine().split(","); 			
				int nbPilotes = 10 + (int)(Math.random() * ((50-10) + 1 ));
				int nbAvions = (10 + (int)(Math.random() * ((50-10) + 1 )))/2;
				int nbPersonnels = (10 + (int)(Math.random() * ((50-10) + 1 )))*3;

				Compagnie comp = new Compagnie(data[1], data[6]);
				
				ArrayList<Pilote> pilotes = Pilote.generate(nbPilotes, comp);
				ArrayList<Avion>  avions = Avion.generate(nbAvions, comp);
				ArrayList<Personnel>  personnels = Personnel.generate(nbPersonnels, comp);
				//avion diplo ?
				comp.setPilotes(pilotes);
				comp.setFlotte(avions);
				comp.setPersonnels(personnels);
				
				compagnies.add(comp);
			}
			else {
				break;
			}
		}
		
		return compagnies;
	}


	public ArrayList<Avion> getFlotte() {
		return flotte;
	}


	private void setFlotte(ArrayList<Avion> flotte) {
		this.flotte = flotte;
	}


	public ArrayList<Pilote> getPilotes() {
		return pilotes;
	}


	private void setPilotes(ArrayList<Pilote> pilotes) {
		this.pilotes = pilotes;
	}


	public ArrayList<Personnel> getPersonnels() {
		return personnels;
	}


	private void setPersonnels(ArrayList<Personnel> personnels) {
		this.personnels = personnels;
	}

	public static void main (String[] args) throws IOException {
		ArrayList<Compagnie> compagnies = Compagnie.generate(2);
		
		System.out.println(compagnies);
	}
}
