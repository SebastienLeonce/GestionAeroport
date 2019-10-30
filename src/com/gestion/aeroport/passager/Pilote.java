package com.gestion.aeroport.passager;

import java.util.Calendar;

public class Pilote extends Passager {
	
	/*private final int COMPAGNIE = 0;
	private final int ETAT = 1;
	private final int PRIVE = 2;*/
	
	private int employeur;
	private String employeur_name;
	private int passagersMax;
	private int tempsPause;

	public Pilote(String prenom, String nom, Calendar anniversaire, String nationalite, long numeroPasseport, String voyage, String employeur, int passagersMax, int tempsPause) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.employeur = Integer.parseInt(employeur.substring(0, 1));
		this.employeur_name = employeur.substring(2, employeur.length());
		this.passagersMax = passagersMax;
		this.tempsPause = tempsPause;
	}
	
	@Override
	public String toString() {
		return 	super.toString() + 
				"\ttypeEmployeur   = " + employeur + "\n" +
				"\temployeur       = " + employeur_name + "\n" +
				"\tpassagersMax    = " + passagersMax + "\n" + 
				"\ttempsPause      = " + tempsPause;
	}

	public static void main (String[] args) {
		Calendar date = new Calendar.Builder(). setFields(Calendar.YEAR, 1999, Calendar.MONTH, Calendar.MARCH ,Calendar.DAY_OF_MONTH, 13).build();
		Pilote pilote = new Pilote("Sebastien", "LEONCE", date, "français", 123456789, "Orly|Marseille", "1|FRANCE", 250, 66);
		
		System.out.println(pilote.toString());
	}
}
