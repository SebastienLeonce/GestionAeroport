package com.gestion.aeroport;

import java.util.Calendar;

public class Diplomate extends Passager {
	
	//private int priorite = 1;
	private int code;

	public Diplomate(String prenom, String nom, Calendar anniversaire, String nationalite, long numeroPasseport, String voyage, int code) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage);
		this.code = code;
	}
	
	public String toString(int code) {
		if (code != this.code) return "Information confidentielle";
		return 	super.toString();
	}
	
	public static void main (String[] args) {
		Calendar date = new Calendar.Builder(). setFields(Calendar.YEAR, 1999, Calendar.MONTH, Calendar.MARCH ,Calendar.DAY_OF_MONTH, 13).build();
		Diplomate diplomate = new Diplomate("Sebastien", "LEONCE", date, "français", 123456789, "Orly|Marseille", 1234);
		
		System.out.println(diplomate.toString(1234));
	}
}
