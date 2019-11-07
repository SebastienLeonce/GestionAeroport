package com.gestion.aeroport.aeroport;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;

import java.util.ArrayList;
import java.util.List;


import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

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


	public static void main (String[] args) throws IOException {
		URL url = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/airlines.dat");
		Scanner sc = new Scanner(url.openStream()); 
		
		String[] data;
		
		List<Avion>  flotte = new ArrayList<Avion>();
		List<Pilote> pilotes = new ArrayList<Pilote>();
		List<Personnel> personnels = new ArrayList<Personnel>();
		
		Compagnie compagnie;
		boolean flag = true;
		
		while (sc.hasNextLine()) {
			data = sc.nextLine().split(","); 
			
			if (flag) {flag = false; continue;}
			
			compagnie = new Compagnie(data[1], data[6], flotte, pilotes, personnels);
			System.out.println(compagnie.toString());
		}
		
		sc.close();
	}
}
