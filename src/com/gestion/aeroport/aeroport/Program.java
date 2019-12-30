package com.gestion.aeroport.aeroport;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.aeroport.Piste;

public class Program {

	public static ArrayList<Compagnie> compagnies;
	public static ArrayList<Avion> avionEnVol;
	public static Aeroport orly;
	public static ArrayList<Aeroport> autresAeroports;
	
	
	public static enum Destination {
        LONDRES("Londres"),
        NY("New-York"), 
        SINGAPOUR("Singapour"),
        SIDNEY("Sidney"),
        MOSCOU("Moscou"),
        BRATISLAVA("Bratislava");
        
		private String destName;
        private Destination(String d) {
            this.destName = d;
        }
       
        private static final java.util.List<Destination> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();
	
		public static Destination randomDestination()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		}        
        @Override
        public String toString(){
            return destName;
        }
    }
	
	
	
	public static void main (String[] args) throws IOException {
				
		orly = new Aeroport("Orly",
				new ArrayList<Piste>(Arrays.asList(  //Atterrisage
					new Piste(1),
					new Piste(1))), 
				new ArrayList<Piste>(Arrays.asList(  //Decollage
						new Piste(2),
						new Piste(3))));
		autresAeroports = new ArrayList<Aeroport>(Arrays.asList(
				new Aeroport(Destination.LONDRES.toString()),
				new Aeroport(Destination.NY.toString()),
				new Aeroport(Destination.SINGAPOUR.toString()),
				new Aeroport(Destination.SIDNEY.toString()),
				new Aeroport(Destination.MOSCOU.toString()),
				new Aeroport(Destination.BRATISLAVA.toString())
				));
				 
		
		System.out.println("Bienvenue dans le système de gestion de l'aéroport d'Orly.");
		System.out.println("Initialisation du système, veuillez patientez...");
		
		compagnies = Compagnie.generate(5);
		avionEnVol = new ArrayList<Avion>();		
		
		boolean programRunning = true;
		
		while(programRunning) {
			
			//Attérissage
			/*System.out.println("==========Demande d'Attérissage==========");
			Program.DemandeAtterissage();
			System.out.println("==========Attérissage==========");
			orly.Atterissage();
			
			//Décollage
			System.out.println("==========Demande de Décollage==========");
			Program.DemandeDecollage();
			System.out.println("==========Décollage==========");
			orly.Decollage();*/
			
			
			
			
			
			
			
			//Choix Action
			System.out.println("==========Action==========");
			int action = -1;
			boolean actionValidee = false;
			while(!actionValidee) {
				
				Scanner sc = new Scanner(System.in);
				System.out.println("Choisissez une action : ");
				System.out.println("0 : Fermeture temporaire d'une piste");
				System.out.println("1 : Réouverture d'une piste");
				System.out.println("2 : Annulation d'un vol");
				System.out.println("3 : Passage en priorité d'un vol");
				System.out.println("4 : Fin du programme");
				
				if(sc.hasNextInt()) {
					action = sc.nextInt();
					actionValidee = true;
					switch(action) {
						case 0:
							System.out.println("Fermeture temporaire d'une piste\n");
							Program.action0();
							break;
						case 1:
							System.out.println("Réouverture d'une piste");
							break;
						case 2:
							System.out.println("Annulation d'un vol");
							break;
						case 3:
							System.out.println("Passage en priorité d'un vol");
							break;
						case 4:
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
	
	//Fermeture temporaire d'une piste
	private static void action0 () {
		System.out.println("PistesAtterissage");
		System.out.println(orly.getPistesAtterissage());
		
		System.out.println("PistesDecollage");
		System.out.println(orly.getPistesDecollage());

		System.out.println("Entrer l'id de la piste que vous voulez fermer:\n");
		
		boolean actionValidee = false;
		while(!actionValidee) {
			Scanner sc = new Scanner(System.in);
			
			if(sc.hasNextInt()) {
				actionValidee = orly.eteindrePiste(sc.nextInt());	
				System.out.println("Entrer un id de piste valide ou écriver \"exit\"\n");
			} else if (sc.hasNext("exit")) {
				break;
			}
		}
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	
	
	private static void DemandeAtterissage() {
		//Entre 1 et 2 nouveaux avions souhaitant attérir
		int random = 1 + (int)(Math.random() * ((2-1) + 1 ));
		for(int i = 1; i <= random;i++) {
			Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
			ArrayList<Avion> f = c.getFlotte();	
			Avion a = f.get((int)(Math.random() * f.size()));
			Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
			Vol v = new Vol(a,orly,aer);
			orly.getRadar().add(v);
			
			System.out.println("Le " + v + " a demandé l'autorisation d'attérir.");
			
			//Choix Piste
			int piste = -1;
			boolean pisteValidee = false;
			while(!pisteValidee) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Sur quelle piste d'atterissage faut-il faire attérir cet avion ?");
				for(int j = 0; j< orly.getPistesAtterissage().size(); j++) {
					System.out.print(j + " : ");
					System.out.print(orly.getPistesAtterissage().get(j));
				}		
				if(sc.hasNextInt()) {
					piste = sc.nextInt();
					pisteValidee = true;
					
					if(piste >= 0 &&  piste < orly.getPistesAtterissage().size()) {
						int position = Aeroport.calculPosition(orly.getPistesAtterissage().get(piste), v);
						System.out.println("Le vol à été mis dans la file d'attente à la position : " + (position+1));
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
		//Entre 1 et 2 nouveaux avions souhaitant décoller
		int random = 1 + (int)(Math.random() * ((2-1) + 1 ));
		for(int i = 1; i <= random;i++) {
			Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
			ArrayList<Avion> f = c.getFlotte();	
			Avion a = f.get((int)(Math.random() * f.size()));
			Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
			Vol v = new Vol(a,aer,orly);
			orly.getRadar().add(v);
			
			System.out.println("Le " + v + " a demandé l'autorisation de décoller.");
			
			//Choix Piste
			int piste = -1;
			boolean pisteValidee = false;
			while(!pisteValidee) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Sur quelle piste de décollage faut-il faire envoyer cet avion ?");
				for(int j = 0; j< orly.getPistesDecollage().size(); j++) {
					System.out.print(j + " : ");
					System.out.print(orly.getPistesDecollage().get(j));
				}		
				if(sc.hasNextInt()) {
					piste = sc.nextInt();
					pisteValidee = true;
					
					if(piste >= 0 &&  piste < orly.getPistesDecollage().size()) {
						int position = Aeroport.calculPosition(orly.getPistesDecollage().get(piste), v);
						System.out.println("Le vol à été mis dans la file d'attente à la position : " + (position+1));
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
