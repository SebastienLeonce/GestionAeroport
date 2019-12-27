package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;

public class Program {

	public static void main (String[] args) throws IOException {
				
		Aeroport orly = new Aeroport("Orly",
				new ArrayList<Piste>(Arrays.asList(  //Atterrisage
					new Piste(2),
					new Piste(3))), 
				new ArrayList<Piste>(Arrays.asList(  //Decollage
						new Piste(2),
						new Piste(3))));
		ArrayList<Aeroport> autresAeroports = new ArrayList<Aeroport>(Arrays.asList(
				new Aeroport("Londres"),
				new Aeroport("New-York"),
				new Aeroport("Singapour"),
				new Aeroport("Sidney"),
				new Aeroport("Moscou"),
				new Aeroport("Bratislava")
				));
				 
		
		System.out.println("Bienvenue dans le système de gestion de l'aéroport d'Orly.");
		System.out.println("Initialisation du système, veuillez patientez...");
		
		ArrayList<Compagnie> compagnies = Compagnie.generate(10);
		ArrayList<Avion> avionEnVol = new ArrayList<Avion>();		
		
		boolean programRunning = true;
		
		while(programRunning) {
			
			//Attérissage
			//Entre 1 et 5 nouveaux avions voulants attérir
			int random = 1 + (int)(Math.random() * ((2-1) + 1 ));
			for(int i = 1; i <= random;i++) {
				Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
				ArrayList<Avion> f = c.getFlotte();	
				Avion a = f.get((int)(Math.random() * f.size()));
				Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
				Vol v = new Vol(a,orly,aer);
				orly.getRadar().add(v);
				
				System.out.println("Le " + v + " a demandé l'autorisation d'attérir.");
							
				System.out.println();
				
				
			}
			
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
