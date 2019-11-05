package com.gestion.aeroport.aeroport;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Compagnie {
	
	private String nom;
	private String nationalite;
	private List<Avion>  flotte = new ArrayList<Avion>();
	private List<Pilote> pilotes = new ArrayList<Pilote>();
	private List<Personnel> personnels = new ArrayList<Personnel>();
	
	public Compagnie(String nom, String nationalite, List<Avion> flotte, List<Pilote> pilotes, List<Personnel> personnels) {
		this.nom = nom;
		this.nationalite = nationalite;
		this.flotte = flotte;
		this.pilotes = pilotes;
		this.personnels = personnels;
	}
}
