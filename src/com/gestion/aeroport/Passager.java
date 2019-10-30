package com.gestion.aeroport;

import java.util.Date;

public abstract class Passager {
	
	private String prenom;
	private String nom;
	private Date anniversaire;
	private String nationalite;
	private int numeroPasseport;
	
	private String voyage;
	//private historic avec l'aeroport
	private int numeroVol;
	
	public Passager (String prenom, String nom, Date anniversaire, String nationalite, int numeroPasseport, String voyage) {
		this.prenom = prenom;
		this.nom = nom;
		this.anniversaire = anniversaire;
		this.nationalite = nationalite;
		this.numeroPasseport = numeroPasseport;
		
		this.voyage = voyage;
	}
}
