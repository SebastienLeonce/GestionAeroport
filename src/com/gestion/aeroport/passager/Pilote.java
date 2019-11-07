package com.gestion.aeroport.passager;

import java.util.Calendar;
import java.util.Random;

import com.gestion.aeroport.aeroport.Compagnie;

public class Pilote extends Passager {
	
	
	private Compagnie employeur;
	private int passagersMax;
	private int tempsPause;

	public Pilote(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, Compagnie employeur, int passagersMax, int tempsPause) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.employeur = employeur;
		this.passagersMax = passagersMax;
		this.tempsPause = tempsPause;
	}
	
	public Pilote (Compagnie employeur) {
		super(null);
		this.employeur = employeur;
		this.passagersMax = new Random().nextInt(500 - 200 + 1)  + 400;
		this.tempsPause = new Random().nextInt(20 - 5 + 1)  + 20;
	}
	
	public Compagnie getEmployeur() {
		return employeur;
	}

	public int getPassagersMax() {
		return passagersMax;
	}

	public int getTempsPause() {
		return tempsPause;
	}

	@Override
	public String toString() {
		return 	super.toString() + 
				"\ttypeEmployeur   = " + employeur + "\n" +
				"\temployeur       = " + employeur.getNom() + "\n" +
				"\tpassagersMax    = " + passagersMax + "\n" + 
				"\ttempsPause      = " + tempsPause;
	}
}
