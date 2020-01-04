package com.gestion.aeroport.passager;

import java.util.Calendar;

/**
 * Diplomate vol toujours dans un avion diplomatique
 */
public class Diplomate extends Passager {
	
	private int code;

	/**
	 * 
	 * @param prenom
	 * @param nom
	 * @param anniversaire
	 * @param nationalite
	 * @param numeroPasseport
	 * @param voyage
	 * @param code
	 */
	public Diplomate(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, boolean volPrive, int code) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage, volPrive);
		this.code = code;
		this.setPriorite(true);
		this.setVolPrive(false);
	}
	
	/**
	 * 
	 * @param voyage
	 * @param code
	 */
	public Diplomate (String voyage, int code) {
		super(voyage);
		this.code = code;
		this.setPriorite(true);
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public String toString(int code) {
		if (code != this.code) return "Information confidentielle";
		return 	super.toString();
	}
}
