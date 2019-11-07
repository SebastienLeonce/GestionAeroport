package com.gestion.aeroport.passager;

import java.util.Calendar;
import java.util.Locale;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;

public class Passager {
	
	private String prenom;
	private String nom;
	private Calendar anniversaire;
	private String nationalite;
	private String numeroPasseport;
	
	private String voyage; 
	private String historique = null; 
	private int numeroVol = -1;
	
	public Passager (String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage) {
		this.prenom = prenom;
		this.nom = nom;
		this.anniversaire = anniversaire;
		this.nationalite = nationalite;
		this.numeroPasseport = numeroPasseport;
		
		this.voyage = voyage;
	}
	
	public Passager (String voyage) {
		Fairy fairy = Fairy.create();
		Person person = fairy.person(PersonProperties.ageBetween(18, 50));
		
		this.prenom = person.firstName();
		this.nom = person.lastName();
		this.anniversaire = new Calendar.Builder(). setFields(Calendar.YEAR, Integer.parseInt(person.dateOfBirth().toString().substring(0, 4)), Calendar.MONTH, Integer.parseInt(person.dateOfBirth().toString().substring(5, 7)) -1,Calendar.DAY_OF_MONTH, Integer.parseInt(person.dateOfBirth().toString().substring(8, 10))).build();
		this.nationalite = "fr";
		this.numeroPasseport = person.passportNumber();

		this.voyage = voyage;
	}
	
	public String getVoyage() {
		return voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	public String getHistorique() {
		return historique;
	}

	public void setHistorique(String historique) {
		this.historique = historique;
	}

	public int getNumeroVol() {
		return numeroVol;
	}

	public void setNumeroVol(int numeroVol) {
		this.numeroVol = numeroVol;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getNom() {
		return nom;
	}

	public Calendar getAnniversaire() {
		return anniversaire;
	}

	public String getNationalite() {
		return nationalite;
	}

	public String getNumeroPasseport() {
		return numeroPasseport;
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
		//Calendar date = new Calendar.Builder(). setFields(Calendar.YEAR, 1999, Calendar.MONTH, Calendar.MARCH,Calendar.DAY_OF_MONTH, 13).build();
		//Passager passager = new Passager("Sebastien", "LEONCE", date, "fr", "abc123456789", "Orly|Marseille");
		
		
		Passager passager = new Passager("Orly|Marseille");
		
		System.out.println(passager.toString());
	}
}
