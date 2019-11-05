package com.gestion.aeroport.passager;

import java.util.Calendar;

public class Diplomate extends Passager {
	
	//private int priorite = 1; //ajouter un syst�me de priorite
	private int code;

	public Diplomate(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, String voyage, int code) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.code = code;
	}
	
	public String toString(int code) {
		if (code != this.code) return "Information confidentielle";
		return 	super.toString();
	}
	
	public static void main (String[] args) {
		Calendar date = new Calendar.Builder(). setFields(Calendar.YEAR, 1999, Calendar.MONTH, Calendar.MARCH ,Calendar.DAY_OF_MONTH, 13).build();
		Diplomate diplomate = new Diplomate("Sebastien", "LEONCE", date, "fran�ais", "123456789", "Orly|Marseille", 1234);
		
		System.out.println(diplomate.toString(1234));
	}
}
