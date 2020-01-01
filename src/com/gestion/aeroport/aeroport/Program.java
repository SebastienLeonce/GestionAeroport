package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Pilote;
import com.gestion.aeroport.aeroport.Piste;
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
				 
		
		System.out.println("Bienvenue dans le syst�me de gestion de l'a�roport d'Orly.");
		System.out.println("Initialisation du syst�me, veuillez patientez...");
		
		compagnies = Compagnie.generate(Program.NB_COMPAGNIES);
		for(int i = 0; i < Program.NB_COMPAGNIES ; i++) {
			orly.getFileAttentePilote().put(compagnies.get(i),new ArrayList<Pilote>());
		}
		
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
			System.out.println("==========D�collage==========");
			orly.Decollage();
			
			//Arriv�e de Passager et remplissage des vols
			orly.ArriveePassagerDansAeroport();
			orly.RemplissageVol();
			
			
			
			
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
				System.out.println("0 : Fermeture temporaire d'une piste");
				System.out.println("1 : R�ouverture d'une piste");
				System.out.println("2 : Annulation d'un vol");
				System.out.println("3 : Passage en priorit� d'un vol");
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
							//Penser au modification des files d'attentes
							System.out.println("R�ouverture d'une piste");
							Program.action1();
							break;
						case 2:
							System.out.println("Annulation d'un vol");
							break;
						case 3:
							//modification file d'attente de l'avion
							System.out.println("Passage en priorit� d'un vol");
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
				System.out.println("Entrer un id de piste valide ou �criver \"exit\"\n");
			} else if (sc.hasNext("exit")) {
				break;
			}
		}
		
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}
	
	//Ouverture d'une piste
	private static void action1 () {
		System.out.println("PistesAtterissage");
		System.out.println(orly.getPistesAtterissage());
			
		System.out.println("PistesDecollage");
		System.out.println(orly.getPistesDecollage());

		System.out.println("Entrer l'id de la piste que vous voulez ouvrir:\n");
			
		boolean actionValidee = false;
		while(!actionValidee) {
			Scanner sc = new Scanner(System.in);
				
			if(sc.hasNextInt()) {
				actionValidee = orly.ouvrirPiste(sc.nextInt());	
				System.out.println("Entrer un id de piste valide ou �criver \"exit\"\n");
			} else if (sc.hasNext("exit")) {
				break;
			}
		}
			
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
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
						int position = Aeroport.calculPosition(orly.getPistesAtterissage().get(piste), v);
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
			Vol v = new Vol(a,aer,orly);
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
						int position = Aeroport.calculPosition(orly.getPistesDecollage().get(piste), v);
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
