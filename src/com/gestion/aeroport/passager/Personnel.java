package com.gestion.aeroport.passager;

import java.util.ArrayList;
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
	public Personnel(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, boolean volPrive, Compagnie compagnie) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage, volPrive);
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
	
	public static ArrayList<Personnel> generate(int n, Compagnie c){
		ArrayList<Personnel> personnels = new ArrayList<Personnel>();
		for (int i = 0 ; i < n ; i++) {
			personnels.add(new Personnel(c));
		}
		return personnels;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return 	super.toString(); /*+ 
				"\tcompagnie       = " + compagnie;*/
	}
}
