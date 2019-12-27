package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Program {

	public static void main (String[] args) throws IOException {
				
		Aeroport orly = new Aeroport("Orly", new ArrayList<Piste>(Arrays.asList(
			new Piste(2, Piste.TypePiste.ATTERISSAGE),
			new Piste(3, Piste.TypePiste.ATTERISSAGE),
			new Piste(2, Piste.TypePiste.DECOLLAGE),
			new Piste(3, Piste.TypePiste.DECOLLAGE)
		)));
		
		System.out.println(orly);
			
		System.out.println("Bienvenue dans le système de gestion de l'aéroport d'Orly.");
		boolean programRunning = true;
		
		while(programRunning) {
			System.out.println("Voici les avions présents dans le radar : ");
			System.out.println(orly.getRadar());
			
			
			int action = -1;
			boolean actionValidee = false;
			while(!actionValidee) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Choisissez une action : ");
				System.out.println("0 : Blabla\t1 : Quitter");			
				if(sc.hasNextInt()) {
					action = sc.nextInt();
					actionValidee = true;
					switch(action) {
						case 0:
							System.out.println("Blabla");
							break;
						case 1:
							System.out.println("Fin du programme");
							programRunning = false;
							break;
						default:
							System.out.println("Ce n'est pas une action valide");
							actionValidee = false;
							break;
					}		
				}
				else {
					System.out.println("Ce n'est pas une action valide");
				}
				
			}
			
				
			 			
		}
		
	
	}
}
