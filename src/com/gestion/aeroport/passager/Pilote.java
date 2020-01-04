package com.gestion.aeroport.passager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.gestion.aeroport.aeroport.Compagnie;

public class Pilote extends Passager {
	
	public static final int PASSAGERMAX_MIN = 200;
	public static final int PASSAGERMAX_MAX = 500;
	public static final int TEMPSPOSE_MIN = 1;
	public static final int TEMPSPOSE_MAX = 5;
	
	
	private Compagnie employeur;
	private int passagersMax;
	private int tempsPause;
	private boolean enVol;
	private int cooldown;
	
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
	public Pilote(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, boolean volPrive, Compagnie employeur, int passagersMax, int tempsPause) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage, volPrive);
		this.employeur = employeur;
		this.passagersMax = passagersMax;
		this.tempsPause = tempsPause;
		this.cooldown  = 0;
		this.enVol = true;
	}
	
	/**
	 * 
	 * @param employeur
	 */
	public Pilote (Compagnie employeur) {
		super(null);
		this.employeur = employeur;
		this.passagersMax = new Random().nextInt(PASSAGERMAX_MAX - PASSAGERMAX_MIN + 1)  + PASSAGERMAX_MIN;
		this.tempsPause = new Random().nextInt(TEMPSPOSE_MAX - TEMPSPOSE_MIN + 1)  + TEMPSPOSE_MIN;
		this.enVol = true;
	}
	
	
	
	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
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
