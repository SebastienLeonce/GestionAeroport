package com.gestion.aeroport.passager;

import java.util.Calendar;

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
	private boolean priorite = false;
	
	/**
	 * Créer une personne en spécifiant sois même tous les paramètres
	 * @param prenom String
	 * @param nom String
	 * @param anniversaire Calendar
	 * @param nationalite String
	 * @param numeroPasseport String
	 * @param voyage String "depart|arriver"
	 */
	public Passager (String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage) {
		this.prenom = prenom;
		this.nom = nom;
		this.anniversaire = anniversaire;
		this.nationalite = nationalite;
		this.numeroPasseport = numeroPasseport;
		
		this.voyage = voyage;
	}
	
	/**
	 * Créer une personne aléatoire juste besoins de spécifier son voyage
	 * @param voyage String "depart|arriver"
	 */
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
	
	/**
	 * Permet d'obtenir le voyage du passager
	 * @return Le voyage du passager
	 */
	public String getVoyage() {
		return voyage;
	}

	/**
	 * Permet de modifier le voyage du passager
	 * @param voyage String "depart|arriver"
	 */
	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	/**
	 * Permet d'obtenir l'historique des voyages du passager
	 * @return L'historique des voyages du passager
	 */
	public String getHistorique() {
		return historique;
	}

	/**
	 * Permet de mettre à jour l'historique des voyages du passager
	 * @param historique String
	 */
	public void setHistorique(String historique) {
		this.historique = historique;
	}

	/**
	 * Permet d'obtenir le numéro de vol du passager
	 * @return int Le numéro de vol du passager
	 */
	public int getNumeroVol() {
		return numeroVol;
	}

	/**
	 * Permet de mettre à jour le numéro de vol du passager
	 * @param numeroVol int
	 */
	public void setNumeroVol(int numeroVol) {
		this.numeroVol = numeroVol;
	}

	/**
	 * Obtenir le prénom du passager
	 * @return String Le prénom du passager
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Obtenir le nom du passager
	 * @return String Le nom du passager
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Obtenir la date d'anniversaire du Passager
	 * @return Calendar La date d'anniversaire du Passager
	 */
	public Calendar getAnniversaire() {
		return anniversaire;
	}

	/**
	 * Obtenir la nationalité du passager
	 * @return String La nationalité du passager
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * Obtenir le numéro de passeport du passager
	 * @return String Le numéro de passeport du passager
	 */
	public String getNumeroPasseport() {
		return numeroPasseport;
	}

	/**
	 * Savoir si le passager est prioritaire pour prendre un vol
	 * @return Boolean
	 */
	public boolean isPriorite() {
		return priorite;
	}

	/**
	 * Permet de dire si le passager est prioritaire ou non
	 * @param priorite Boolean
	 */
	public void setPriorite(boolean priorite) {
		this.priorite = priorite;
	}

	/**
	 * Permet d'afficher tous les données d'un passager en console
	 * @return String Les données du passagers
	 */
	@Override
	public String toString() {
		return 	this.getClass().getSimpleName() + " : \n" +
				"\tprenom_nom      = " + prenom + "_" + nom + "\n" +
				"\tanniversaire    = " + anniversaire.get(Calendar.YEAR) + "/" + anniversaire.get(Calendar.MONTH) + "/" +  anniversaire.get(Calendar.DAY_OF_MONTH) + "\n" +
				"\tnationalite     = " + nationalite + "\n" ;/*+
				"\tnumeroPasseport = " + numeroPasseport +  "\n" +
				"\tvoyage          = " + voyage + "\n" +
				"\thistorique      = " + historique + "\n" +
				"\tnumeroVol       = " + numeroVol + "\n" +
				"\tpriorite        = " + priorite + "\n\n";*/
	}
}
