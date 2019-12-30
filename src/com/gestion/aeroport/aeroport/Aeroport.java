package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

import com.gestion.aeroport.passager.Passager;

public class Aeroport {
 
	
	private String nom;
	private ArrayList<Piste> pistesDecollage;
	private ArrayList<Piste> pistesAtterissage; 
	private ArrayList<Vol> radar = new ArrayList<Vol>(); 
	
	private ArrayList<Passager> passagersDansAeroport;
	
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistesAtterissage, ArrayList<Piste> pistesDecollage) {
		this.nom = nom;
		
		//
		this.passagersDansAeroport = new ArrayList<Passager>();
		int random = 20 + (int)(Math.random() * ((50-20) + 20 ));
		for(int i = 0; i < random; i++) {
			passagersDansAeroport.add(new Passager(Program.Destination.randomDestination().toString()));
		}	
		
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
	
	
	public void Atterissage() {
		for(Piste p: pistesAtterissage) {
			if(p.getFileDAttente().size()>0) {
				Vol v = p.getFileDAttente().remove(0);
				System.out.println("Le vol " + v + " vient d'attérir");
			}			
		}
	}
	public void Decollage() {
		for(Piste p: pistesDecollage) {
			if(p.getFileDAttente().size()>0) {
				Vol v = p.getFileDAttente().remove(0);
				System.out.println("Le vol " + v + " vient de décoller");
			}			
		}
	}
	
	
	//Calcul et insère à la position optimal un avion dans la file d'attente
	public static int calculPosition(Piste p, Vol v) {
		int position = p.getFileDAttente().size();
		for(int i = 0; i< p.getFileDAttente().size(); i++) {
			if(v.getAvion().getVolCarburant() < p.getFileDAttente().get(i).getAvion().getVolCarburant()) {
				position = i;
				break;
			}
		}
		p.getFileDAttente().add(position, v);
		return position;
	}
	
	
	
}
