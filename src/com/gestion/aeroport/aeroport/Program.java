package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.avion.AvionLigne;
import com.gestion.aeroport.passager.Passager;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;
public class Program {

	public static final int NB_COMPAGNIES = 5;
	
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
				 
		
		System.out.println("Bienvenue dans le systeme de gestion de l'aeroport d'Orly.");
		System.out.println("Initialisation du systeme, veuillez patientez...");
		
		compagnies = Compagnie.generate(NB_COMPAGNIES);
		for(int i = 0; i < NB_COMPAGNIES ; i++) {
			Compagnie c = compagnies.get(i);
			orly.getFileAttentePilote().put(c,new LinkedList<Pilote>());
			orly.getAvionsAuSol().put(c,new ArrayList<Avion>());
			
			//De 1 à 8 pilotes en attentes
			int random = 1 + (int)(Math.random() * ((8-1) + 1 ));
			for(int j = 0; j < random; j++) {
				orly.getFileAttentePilote().get(c).add(new Pilote(c));
			}
			
			//De 0 à 4 avions par compagnie en attente
			random = 0 + (int)(Math.random() * ((4-0) + 0 ));
			for(int j = 0; j < random; j++) {
				Avion a;
				try {
					a = c.utiliserUnAvion();
					orly.getAvionsAuSol().get(c).add(a);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		avionEnVol = new ArrayList<Avion>();		
		boolean programRunning = true;
		
		
		
		
		while(programRunning) {
			
			//Attï¿½rissage
			System.out.println("==========Demande d'Atterissage==========");
			Program.DemandeAtterissage();
			System.out.println("==========Atterissage==========");
			orly.Atterissage();
			
			//Dï¿½collage);
			System.out.println("==========Decollage==========");
			orly.Decollage();
			
			//Arrivï¿½e de Passager et remplissage des vols
			orly.generateVol();
			
			orly.ArriveePassagerDansAeroport();
			System.out.println(orly.getPassagersDansAeroport().size());
			orly.RemplissageVol();
			System.out.println(orly.getPassagersDansAeroport().size());
			System.out.println(orly.getVolsEnPreparation());
			
			
			//file d'attente pour chaque destination
			//Generer des gros paquet de passager
			// Regarder si on peut faire un avion de ligne
			
			//Choix Action
			System.out.println("==========Action==========");
			int action = -1;
			boolean actionValidee = false;
			while(!actionValidee) {
				
				Scanner sc = new Scanner(System.in);
				System.out.println("Choisissez une action : ");
				System.out.println("0 : Ne Rien Faire");
				System.out.println("1 : Fermeture temporaire d'une piste");
				System.out.println("2 : Rï¿½ouverture d'une piste");
				System.out.println("3 : Annulation d'un vol");
				System.out.println("4 : Passage en prioritï¿½ d'un vol");
				System.out.println("5 : Fin du programme");
				
				if(sc.hasNextInt()) {
					action = sc.nextInt();
					actionValidee = true;
					switch(action) {
						case 0:
							System.out.println("===================================\n");
							break;
						case 1:
							System.out.println("Fermeture temporaire d'une piste\n");
							Program.fermeturePiste();
							break;
						case 2:
							//Penser au modification des files d'attentes
							System.out.println("Rï¿½ouverture d'une piste");
							Program.reouverturePiste();
							break;
						case 3:
							System.out.println("Annulation d'un vol");
							break;
						case 4:
							//modification file d'attente de l'avion
							System.out.println("Passage en prioritï¿½ d'un vol");
							break;
						case 5:
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
	private static void fermeturePiste () {
		System.out.println("pisteAtterrissage");
		System.out.println(orly.getPistesAtterrissage());
		
		System.out.println("PistesDecollage");
		System.out.println(orly.getPistesDecollage());

		System.out.println("Entrer l'id de la piste que vous voulez fermer:\n");
		
		boolean actionValidee = false;
		while(!actionValidee) {
			Scanner sc = new Scanner(System.in);
			
			if(sc.hasNextInt()) {
				actionValidee = orly.eteindrePiste(sc.nextInt());	
				System.out.println("Entrer un id de piste valide ou ï¿½criver \"exit\"\n");
			} else if (sc.hasNext("exit")) {
				break;
			}
		}
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	//Ouverture d'une piste
	private static void reouverturePiste () {
		System.out.println("pisteAtterrissage");
		System.out.println(orly.getPistesAtterrissage());
			
		System.out.println("PistesDecollage");
		System.out.println(orly.getPistesDecollage());

		System.out.println("Entrer l'id de la piste que vous voulez ouvrir:\n");
			
		boolean actionValidee = false;
		while(!actionValidee) {
			Scanner sc = new Scanner(System.in);
				
			if(sc.hasNextInt()) {
				actionValidee = orly.ouvrirPiste(sc.nextInt());	
				System.out.println("Entrer un id de piste valide ou ï¿½criver \"exit\"\n");
			} else if (sc.hasNext("exit")) {
				break;
			}
		}
			
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	
	public static void DemandeAtterissage() {
		//Entre 1 et 2 nouveaux avions souhaitant atterir
		int random = 1 + (int)(Math.random() * ((2-1) + 1 ));
		for(int i = 1; i <= random;i++) {
			Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
			
			Avion a = null;
			try {
				a = c.utiliserUnAvion();
				ArrayList<Pilote> pilotes = c.utiliserDesPilotes(a.getNbPilotesMin());
				for(Pilote p: pilotes) {
					a.ajouterPilote(p);
				}
				if(a.getClass() == AvionLigne.class) {
					AvionLigne avion = (AvionLigne)a;
					ArrayList<Personnel> personnels = c.utiliserDuPersonnel(avion.getNbPersonnelsMin());
					for(Personnel p : personnels) {
						avion.ajouterPersonnel(p);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
			if(a != null) {
				
				for(int j = a.getCapacite(); j < a.getCapaciteMax(); j++) {
					a.ajouterPassager(new Passager("Orly"));
				}
				
				//Set Carburant low 
				a.setVolCarburant(a.getVolCarburant() - (Avion.CARBURANT_MIN-1));				
				Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
				Vol v = new Vol(a,orly,aer);
				orly.getRadar().add(v);
				
				System.out.println("Le " + v + " a demande l'autorisation d'atterir.");
								
				
				//Choix Piste
				int piste = -1;
				boolean pisteValidee = false;
				while(!pisteValidee) {
					Scanner sc = new Scanner(System.in);
					System.out.println("Sur quelle piste d'atterrissage faut-il faire atterir cet avion ?");
					for(int j = 0; j< orly.getPistesAtterrissage().size(); j++) {
						System.out.print(j + " : ");
						System.out.print(orly.getPistesAtterrissage().get(j));
					}		
					if(sc.hasNextInt()) {
						piste = sc.nextInt();
						pisteValidee = true;
						
						if(piste >= 0 &&  piste < orly.getPistesAtterrissage().size()) {
							int position = Aeroport.calculPosition(orly.getPistesAtterrissage().get(piste), v);
							System.out.println("Le vol a ete place dans la file d'attente a la position : " + (position+1));
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
	
	public static void DemandeDecollage(Vol v) {
		orly.getRadar().add(v);
		System.out.println("Le " + v + " a demandï¿½ l'autorisation de dï¿½coller.");
		
		//Choix Piste
		int piste = -1;
		boolean pisteValidee = false;
		while(!pisteValidee) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Sur quelle piste de dï¿½collage faut-il faire envoyer cet avion ?");
			for(int j = 0; j< orly.getPistesDecollage().size(); j++) {
				System.out.print(j + " : ");
				System.out.print(orly.getPistesDecollage().get(j));
			}		
			if(sc.hasNextInt()) {
				piste = sc.nextInt();
				pisteValidee = true;
				
				if(piste >= 0 &&  piste < orly.getPistesDecollage().size()) {
					int position = Aeroport.calculPosition(orly.getPistesDecollage().get(piste), v);
					System.out.println("Le vol a ete place dans la file d'attente a la position : " + (position+1));
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
