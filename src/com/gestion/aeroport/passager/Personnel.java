package com.gestion.aeroport.passager;

import java.util.Calendar;

public class Personnel extends Passager {
	
	private String compagnie;

	public Personnel(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, String compagnie) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.compagnie = compagnie; // créer une exeption pour vérifier si c'est une compagnie valide ?
	}

	@Override
	public String toString() {
		return 	super.toString() + 
				"\tcompagnie       = " + compagnie;
	}
	
	public static void main (String[] args) {
		Calendar date = new Calendar.Builder(). setFields(Calendar.YEAR, 1999, Calendar.MONTH, Calendar.MARCH,Calendar.DAY_OF_MONTH, 13).build();
		Personnel personnel = new Personnel("Sebastien", "LEONCE", date, "français", "123456789", "Orly|Marseille", "AirFrance");
		
		System.out.println(personnel.toString());
	}
}
