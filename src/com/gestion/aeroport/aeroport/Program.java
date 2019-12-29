package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;

public class Program {

	public static ArrayList<Compagnie> compagnies;
	public static ArrayList<Avion> avionEnVol;
	public static Aeroport orly;
	public static ArrayList<Aeroport> autresAeroports;
	
	
	
	
	public static void main (String[] args) throws IOException {
				
		orly = new Aeroport("Orly",
				new ArrayList<Piste>(Arrays.asList(  //Atterrisage
					new Piste(1),
					new Piste(1))), 
				new ArrayList<Piste>(Arrays.asList(  //Decollage
						new Piste(2),
						new Piste(3))));
		autresAeroports = new ArrayList<Aeroport>(Arrays.asList(
				new Aeroport("Londres"),
				new Aeroport("New-York"),
				new Aeroport("Singapour"),
				new Aeroport("Sidney"),
				new Aeroport("Moscou"),
				new Aeroport("Bratislava")
				));
				 
		
		System.out.println("Bienvenue dans le syst�me de gestion de l'a�roport d'Orly.");
		System.out.println("Initialisation du syst�me, veuillez patientez...");
		
		compagnies = Compagnie.generate(5);
		avionEnVol = new ArrayList<Avion>();		
		
		boolean programRunning = true;
		
		while(programRunning) {
			
			//Att�rissage
			System.out.println("==========Demande d'Att�rissage==========");
			Program.DemandeAtterissage();
			System.out.println("==========Att�rissage==========");
			orly.Atterissage();
			
			//D�collage
			System.out.println("==========Demande de D�collage==========");
			Program.DemandeDecollage();
			
			//Choix Action
			System.out.println("==========Action==========");
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
	
	
	
	private static void DemandeAtterissage() {
		//Entre 1 et 2 nouveaux avions souhaitant att�rir
		int random = 1 + (int)(Math.random() * ((2-1) + 1 ));
		for(int i = 1; i <= random;i++) {
			Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
			ArrayList<Avion> f = c.getFlotte();	
			Avion a = f.get((int)(Math.random() * f.size()));
			Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
			Vol v = new Vol(a,orly,aer);
			orly.getRadar().add(v);
			
			System.out.println("Le " + v + " a demand� l'autorisation d'att�rir.");
			
			//Choix Piste
			int piste = -1;
			boolean pisteValidee = false;
			while(!pisteValidee) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Sur quelle piste d'atterissage faut-il faire att�rir cet avion ?");
				for(int j = 0; j< orly.getPistesAtterissage().size(); j++) {
					System.out.print(j + " : ");
					System.out.print(orly.getPistesAtterissage().get(j));
				}		
				if(sc.hasNextInt()) {
					piste = sc.nextInt();
					pisteValidee = true;
					
					if(piste >= 0 &&  piste < orly.getPistesAtterissage().size()) {
						int position = Aeroport.calculPositionAtterissage(orly.getPistesAtterissage().get(piste), v);
						System.out.println("Le vol � �t� mis dans la file d'attente � la position : " + (position+1));
					}
					else {
						System.out.println("Cette piste n'existe pas");
						pisteValidee = false;
					}	
				}
				else {
					System.out.println("Cette piste n'existe pas");
				}
				System.out.println();
			}
		}
	}
	private static void DemandeDecollage() {
		//Entre 1 et 2 nouveaux avions souhaitant d�coller
		int random = 1 + (int)(Math.random() * ((2-1) + 1 ));
		for(int i = 1; i <= random;i++) {
			Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
			ArrayList<Avion> f = c.getFlotte();	
			Avion a = f.get((int)(Math.random() * f.size()));
			Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
			Vol v = new Vol(a,orly,aer);
			orly.getRadar().add(v);
			
			System.out.println("Le " + v + " a demand� l'autorisation de d�coller.");
			
			//Choix Piste
			int piste = -1;
			boolean pisteValidee = false;
			while(!pisteValidee) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Sur quelle piste de d�collage faut-il faire envoyer cet avion ?");
				for(int j = 0; j< orly.getPistesDecollage().size(); j++) {
					System.out.print(j + " : ");
					System.out.print(orly.getPistesDecollage().get(j));
				}		
				if(sc.hasNextInt()) {
					piste = sc.nextInt();
					pisteValidee = true;
					
					if(piste >= 0 &&  piste < orly.getPistesDecollage().size()) {
						int position = Aeroport.calculPositionAtterissage(orly.getPistesDecollage().get(piste), v);
						System.out.println("Le vol � �t� mis dans la file d'attente � la position : " + (position+1));
					}
					else {
						System.out.println("Cette piste n'existe pas");
						pisteValidee = false;
					}	
				}
				else {
					System.out.println("Cette piste n'existe pas");
				}
				System.out.println();
			}
		}
	}
	
	
}
