package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

public class Aeroport {
 
	
	private String nom;
	private ArrayList<Piste> pistesDecollage;
	private ArrayList<Piste> pistesAtterissage; 
	private ArrayList<Vol> radar = new ArrayList<Vol>(); 
	
	
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistesAtterissage, ArrayList<Piste> pistesDecollage) {
		this.nom = nom;
		this.pistesDecollage = pistesDecollage;
		this.pistesAtterissage = pistesAtterissage;
	}
	
	
	public ArrayList<Piste> getPistesDecollage() {
		return pistesDecollage;
	}
	public void setPistesDecollage(ArrayList<Piste> pistesDecollage) {
		this.pistesDecollage = pistesDecollage;
	}
	public ArrayList<Piste> getPistesAtterissage() {
		return pistesAtterissage;
	}
	public void setPistesAtterissage(ArrayList<Piste> pistesAtterissage) {
		this.pistesAtterissage = pistesAtterissage;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Vol> getRadar() {
		return radar;
	}
	public void setRadar(ArrayList<Vol> radar) {
		this.radar = radar;
	}
	@Override
	public String toString() {
		return "Aeroport [nom=" + nom + ", pistesDecollage=" + pistesDecollage + ", pistesAtterissage="
				+ pistesAtterissage + ", radar=" + radar + "]";
	}
	
	
	/*WIP
	
	public int calculPositionAtterissage() {
		int tempsMinimum = this.pistesAtterissage.get(0).getEspacement() * ;
		int position = 0;
		for(Piste p : this.pistesAtterissage) {
			int nouveauTemps = p.getEspacement() * p.getFileDAttente().size();
			if(nouveauTemps < tempsMinimum) {
				tempsMinimum = nouveauTemps;
			}
		}
		return tempsMinimum;
	}
	*/
	
	
}
