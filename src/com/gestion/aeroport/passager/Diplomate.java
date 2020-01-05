package com.gestion.aeroport.passager;

import java.util.Calendar;

import com.gestion.aeroport.aeroport.Program.Destination;

/**
 * Diplomate herite de la classe Passager et représente un passager diplomate
 */
public class Diplomate extends Passager {
	
	private int code;

	/**
	 * Creer un diplomate en specifiant tous les parametres
	 * @param prenom String
	 * @param nom String
	 * @param anniversaire Calendar
	 * @param nationalite String
	 * @param numeroPasseport String
	 * @param voyage Destination
	 * @param volPrive boolean
	 * @param code int
	 */
	public Diplomate(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, Destination voyage, boolean volPrive, int code) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage, volPrive);
		this.code = code;
		this.setPriorite(true);
		this.setVolPrive(false);
	}
	
	/**
	 * Creer un diplomate en specifiant : voyage, code et nationalite
	 * @param voyage Destination
	 * @param code int
	 * @param nationalite String
	 */
	public Diplomate (Destination voyage,int code, String nationalite) {
		super(voyage, false, nationalite);
		this.code = code;
		this.setPriorite(true);
	}
	
	/**
	 * Permet l'affichage d'un diplomate en verifiant que l'on possède bien le code confidentiel
	 * @param code int
	 * @return String Un message d'erreur ou les informations du diplomate
	 */
	public String toString(int code) {
		if (code != this.code) return "Information confidentielle";
		return 	super.toString();
	}
}
