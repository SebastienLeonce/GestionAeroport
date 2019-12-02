package com.gestion.aeroport.passager;

import java.util.Calendar;

import com.gestion.aeroport.aeroport.Compagnie;

public class Personnel extends Passager {
	
	private Compagnie compagnie;

	/**
	 * 
	 * @param prenom
	 * @param nom
	 * @param anniversaire
	 * @param nationalite
	 * @param numeroPasseport
	 * @param voyage
	 * @param compagnie
	 */
	public Personnel(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, Compagnie compagnie) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.compagnie = compagnie;
	}
	
	/**
	 * 
	 * @param compagnie
	 */
	public Personnel (Compagnie compagnie) {
		super(null);
		this.compagnie = compagnie;
	}

	/**
	 * 
	 * @return
	 */
	public Compagnie getCompagnie() {
		return compagnie;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return 	super.toString() + 
				"\tcompagnie       = " + compagnie;
	}
}
