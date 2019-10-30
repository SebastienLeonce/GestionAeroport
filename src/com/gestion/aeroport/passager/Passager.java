package com.gestion.aeroport.passager;

import java.util.Calendar;

public class Passager {
	
	private String prenom;
	private String nom;
	private Calendar anniversaire;
	private String nationalite;
	private long numeroPasseport;
	
	private String voyage; // depart|destination
	private String historique = null; //reflechir au fonctionnement 
	private int numeroVol = -1;
	
	public Passager (String prenom, String nom, Calendar anniversaire, String nationalite, long numeroPasseport, String voyage) {
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
		Passager passager = new Passager("Sebastien", "LEONCE", date, "français", 123456789, "Orly|Marseille");
		
		System.out.println(passager.toString());
	}
}
