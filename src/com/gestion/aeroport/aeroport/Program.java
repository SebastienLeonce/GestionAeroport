package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.avion.AvionDiplomatique;
import com.gestion.aeroport.avion.AvionLigne;
import com.gestion.aeroport.passager.Diplomate;
import com.gestion.aeroport.passager.Passager;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;
public class Program {

	public static final int NB_COMPAGNIES = 5;
	
	/**
	 * Contains all nationalites
	 */
	public static ArrayList<String> allNationalites = new ArrayList<String>();
	/**
	 * retrun a random nationalite
	 * @return String
	 */
	public static String randomNationalite() {
		return allNationalites.get((int)(Math.random() * allNationalites.size()));
	}
	
	/**
	 * Compagnie Diplomatique index 0
	 * Compagnie Privee index 1
	 */
	public static ArrayList<Compagnie> compagnies;
	public static ArrayList<Avion> avionEnVol;
	public static Aeroport orly;
	public static ArrayList<Aeroport> autresAeroports;
	public static boolean programRunning = true;
	
	public static enum Destination {
        LONDRES(new Aeroport("Londres")),
        NY(new Aeroport("New-York")), 
        SINGAPOUR(new Aeroport("Singapour")),
        SIDNEY(new Aeroport("Sidney")),
        MOSCOU(new Aeroport("Moscou")),
        BRATISLAVA(new Aeroport("Bratislava"));
        
		private Aeroport aeroport;
        private Destination(Aeroport d) {
            this.aeroport = d;
        }
       
        
        private static final java.util.List<Destination> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		private static final Random RANDOM = new Random();
	
		
		public Aeroport getAeroport() {
			return this.aeroport;
		}
		public static Destination randomDestination()  {
		    return VALUES.get(RANDOM.nextInt(SIZE));
		}        
        @Override
        public String toString(){
            return this.aeroport.getNom();
        }
    }
	
	
	
	public static void main (String[] args) throws IOException {
				
		orly = new Aeroport("Orly",
				new ArrayList<Piste>(Arrays.asList(  //Atterrisage
					new Piste(),
					new Piste())), 
				new ArrayList<Piste>(Arrays.asList(  //Decollage
						new Piste(),
						new Piste())));
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
		
		
		//Initialisation des Compagnies et des avions/personnels/pilotes dans l'aeroport
		compagnies = Compagnie.generate(NB_COMPAGNIES);
		for(int i = 0; i < NB_COMPAGNIES ; i++) {
			Compagnie c = compagnies.get(i);
			orly.getFileAttentePilote().put(c,new LinkedList<Pilote>());
			orly.getFileAttentePersonnel().put(c,new LinkedList<Personnel>());
			orly.getAvionsAuSol().put(c,new ArrayList<Avion>());
			
			//De 0 à 4 avions par compagnie en attente
			int random = 0 + (int)(Math.random() * ((4-0) + 0 ));
			for(int j = 0; j < random; j++) {
				Avion a;
				try {
					a = c.utiliserUnAvion();
					orly.getAvionsAuSol().get(c).add(a);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//Cas où Diplomatique
			if(i != 0) {
				//De 1 à 10 pilotes en attentes
				random = 1 + (int)(Math.random() * ((10-1) + 1 ));
				ArrayList<Pilote> pilotes;
				try {
					pilotes = c.utiliserDesPilotes(random);
					for(Pilote p : pilotes) {
						orly.getFileAttentePilote().get(c).add(p);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				//De 5 à 30 personnel en attentes
				random = 5 + (int)(Math.random() * ((30-5) + 1 ));
				ArrayList<Personnel> personnels;
				try {
					personnels = c.utiliserDuPersonnel(random);
					for(Personnel p : personnels) {
						orly.getFileAttentePersonnel().get(c).add(p);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		}
		avionEnVol = new ArrayList<Avion>();			
		
		while(programRunning) {
			//Repos des Pilotes
			orly.repos();
			
			//Atterrissage
			System.out.println("==========Demande d'Atterissage==========");
			Program.DemandeAtterissage();
			System.out.println("==========Atterissage==========");
			orly.Atterrissage(); 
			
			
			/*
			//Decollage);
			System.out.println("==========Decollage==========");
			orly.Decollage();
			
			
			
			
			//Arrivï¿½e de Passager et remplissage des vols	
			System.out.println("==========Arrivée de Passager dans l'aeroport===========");
			orly.ArriveePassagerDansAeroport();
			System.out.println("==========Création de vol==========");
			if(!orly.generateVol()) {
				System.out.println("Aucun vol n'a été créer par manque de personnel/pilote/avion");
			}
			
			
			
			
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
			System.out.println("==========Consommation de Carburant==========");
			orly.consommationCarburant();
			
			*/
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
		//Compagnie c = compagnies.get((int)(Math.random() * compagnies.size()));
		Compagnie c = compagnies.get(0);
		Avion a = null;
		try {
			a = c.utiliserUnAvion();
			//Recherches de pilotes de la même nationalité pour la compagnie diplomatique
			if(c.getNom().equals(Compagnie.NOM_COMPAGNIE_DIPLOMATIQUE)) {
				AvionDiplomatique ad = (AvionDiplomatique)a;
				String etat = Program.randomNationalite();
				ad.setEtat(etat);
				int compt = 0;
				for(int i = 1; i < compagnies.size(); i++) {
					if(compt >= ad.getNbPilotesMin()) {
						break;
					}
					Iterator<Pilote> iterator = compagnies.get(i).getPilotesDispo().iterator();
					while(iterator.hasNext() && compt < ad.getNbPilotesMin()) {
						Pilote p = iterator.next();
						if(p.getNationalite().equals(etat)) {
							iterator.remove();
							compagnies.get(i).getPilotesUtilise().add(p);
							ad.ajouterPilote(p);
							compt++;
						}
					}
				}
				if(ad.getNbPilotes() < ad.getNbPilotesMin()) {
					a = null;
				}
			}
			else {
				ArrayList<Pilote> pilotes = c.utiliserDesPilotes(a.getNbPilotesMin());
				for(Pilote p: pilotes) {
					a.ajouterPilote(p);
				}
				
			}
			if(a instanceof AvionLigne) {
				AvionLigne avion = (AvionLigne)a;
				ArrayList<Personnel> personnels = c.utiliserDuPersonnel(avion.getNbPersonnelsMin());
				for(Personnel p : personnels) {
					avion.ajouterPersonnel(p);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a = null;
		}
		
		if(a != null) {
			
			switch (c.getNom()) {
				case Compagnie.NOM_COMPAGNIE_DIPLOMATIQUE:
					if(a instanceof AvionDiplomatique) {
						AvionDiplomatique ad = (AvionDiplomatique)a;
						//Genere un nombre de passager entre 1 et le maximum de passager possible dans l'avion
						int random = 1 + (int)(Math.random() * (((a.getCapaciteMax()-a.getCapacite())-1) + 1 ));
						for(int j = a.getCapacite(); j < a.getCapaciteMax(); j++) {
							 //Secret code is 42 don't tell anyone
							ad.ajouterPassager(new Diplomate("Orly", 42, ad.getEtat())); 
						}					
					}
					break;
				case Compagnie.NOM_COMPAGNIE_PRIVEE:
					//Genere un nombre de passager entre 1 et le maximum de passager possible dans l'avion
					int random = 1 + (int)(Math.random() * (((a.getCapaciteMax()-a.getCapacite())-1) + 1 ));
					for(int j = a.getCapacite(); j < a.getCapaciteMax(); j++) {
						a.ajouterPassager(new Passager("Orly", true));
					}
					break;
				default:
					for(int j = a.getCapacite(); j < a.getCapaciteMax(); j++) {
						a.ajouterPassager(new Passager("Orly", false));
					}
					break;
			}
			
			
			//Set Carburant low 
			a.setVolCarburant(a.getVolCarburant() - (Avion.CARBURANT_MIN-10)); // au min l'avion a 10 de carburant
			Aeroport aer = autresAeroports.get((int)(Math.random() * autresAeroports.size()));				
			Vol v = new Vol(a,orly,aer, c);
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
	
	public static void DemandeDecollage(Vol v) {
		orly.getRadar().add(v);
		System.out.println("Le " + v + " a demande l'autorisation de decoller.");
		
		//Choix Piste
		int piste = -1;
		boolean pisteValidee = false;
		while(!pisteValidee) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Sur quelle piste de decollage faut-il faire envoyer cet avion ?");
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
