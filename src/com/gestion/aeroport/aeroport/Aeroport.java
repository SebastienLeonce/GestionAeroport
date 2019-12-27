package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

public class Aeroport {
 
	
	private String nom;
	private ArrayList<Piste> pistes; 
	private ArrayList<Vol> radar = new ArrayList<Vol>(); 
	
	
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistes) {
		this.nom = nom;
		this.pistes = pistes;
	}
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Piste> getPistes() {
		return pistes;
	}
	public void setPistes(ArrayList<Piste> pistes) {
		this.pistes = pistes;
	}
	public ArrayList<Vol> getRadar() {
		return radar;
	}
	public void setRadar(ArrayList<Vol> radar) {
		this.radar = radar;
	}
	@Override
	public String toString() {
		return "Aeroport [nom=" + nom + ", pistes=" + pistes + ", radar=" + radar + "]";
	}
	
	
}
