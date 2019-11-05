package com.gestion.aeroport.passager;

import java.util.Calendar;

public class Passager {
	
	private String prenom;
	private String nom;
	private Calendar anniversaire;
	private String nationalite;
	private String numeroPasseport;
	
	private String voyage; // depart|destination // peux être un accès a une instance de l'aeroport
	private String historique = null; //reflechir au fonctionnement // créer une classe ou juste une String
	private int numeroVol = -1; // -1 si pas de vol
	
	public Passager (String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage) {
		this.prenom = prenom;
		this.nom = nom;
		this.anniversaire = anniversaire;
		this.nationalite = nationalite;
		this.numeroPasseport = numeroPasseport;
		
		this.voyage = voyage;
	}
	
	@Override
	public String toString() {
		return 	this.getClass().getSimpleName() + " : \n" +
				"\tprenom_nom      = " + prenom + "_" + nom + "\n" +
				"\tanniversaire    = " + anniversaire.get(Calendar.YEAR) + "/" + anniversaire.get(Calendar.MONTH) + "/" +  anniversaire.get(Calendar.DAY_OF_MONTH) + "\n" +
				"\tnationalite     = " + nationalite + "\n" +
				"\tnumeroPasseport = " + numeroPasseport +  "\n" +
				"\tvoyage          = " + voyage + "\n" +
				"\thistorique      = " + historique + "\n" +
				"\tnumeroVol       = "+ numeroVol + "\n\n";
	}

	public static void main (String[] args) {
		Calendar date = new Calendar.Builder(). setFields(Calendar.YEAR, 1999, Calendar.MONTH, Calendar.MARCH,Calendar.DAY_OF_MONTH, 13).build();
		Passager passager = new Passager("Sebastien", "LEONCE", date, "français", "abc123456789", "Orly|Marseille");
		
		System.out.println(passager.toString());
	}
}
