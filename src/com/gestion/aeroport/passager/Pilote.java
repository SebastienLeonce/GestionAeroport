package com.gestion.aeroport.passager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.gestion.aeroport.aeroport.Compagnie;

public class Pilote extends Passager {
	
	
	private Compagnie employeur;
	private int passagersMax;
	private int tempsPause;
	private boolean enVol;
	
	/**
	 * 
	 * @param prenom
	 * @param nom
	 * @param anniversaire
	 * @param nationalite
	 * @param numeroPasseport
	 * @param voyage
	 * @param employeur
	 * @param passagersMax
	 * @param tempsPause
	 */
	public Pilote(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, Compagnie employeur, int passagersMax, int tempsPause) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.employeur = employeur;
		this.passagersMax = passagersMax;
		this.tempsPause = tempsPause;
		this.enVol = true;
	}
	
	/**
	 * 
	 * @param employeur
	 */
	public Pilote (Compagnie employeur) {
		super(null);
		this.employeur = employeur;
		this.passagersMax = new Random().nextInt(500 - 200 + 1)  + 200;
		this.tempsPause = new Random().nextInt(20 - 5 + 1)  + 20;
		this.enVol = true;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public Compagnie getEmployeur() {
		return employeur;
	}

	/**
	 * 
	 * @return
	 */
	public int getPassagersMax() {
		return passagersMax;
	}

	/**
	 * 
	 * @return
	 */
	public int getTempsPause() {
		return tempsPause;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return 	super.toString() + 
				"\temployeur       = " + employeur.getNom() + "\n" +
				"\tpassagersMax    = " + passagersMax + "\n" + 
				"\ttempsPause      = " + tempsPause;
	}
	
	public static ArrayList<Pilote> generate(int n, Compagnie c){
		ArrayList<Pilote> pilotes = new ArrayList<Pilote>();
		for (int i = 0 ; i < n ; i++) {
			pilotes.add(new Pilote(c));
		}
		return pilotes;
	}
}
