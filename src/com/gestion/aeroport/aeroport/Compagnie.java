package com.gestion.aeroport.aeroport;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.avion.AvionDiplomatique;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;



/**
 * Afin de faciliter l'utilisation des Compagnies,
 * les vols privés sont considérés comme de la Compagnie 'Private Flight' (déjà présente dans notre fichier de compagnies)
 * les vols diplomatiques sont considérés comme de la Compagnie 'Diplomatic Filght' (que nous avons créée)
 * 
 * Les Compagnie sont générées au début du programme et ne changent pas après.
 * De nouveaux pilotes ou avions ou personnels NE peuvent PAS être créer
 */
public class Compagnie {
	/**
	 * Nombre de pilote mininmum crée dans une compagnie
	 */
	public static final int PILOTE_MIN = 50;
	/**
	 * Nombre de pilote maximum crée dans une compagnie
	 */
	public static final int PILOTE_MAX = 150;
	/**
	 * Nombre de personnel mininmum crée dans une compagnie
	 */
	public static final int PERSONNEL_MIN = 200;
	/**
	 * Nombre de personnel maximum crée dans une compagnie
	 */
	public static final int PERSONNEL_MAX = 300;
	/**
	 * Nombre d'avion mininmum crée dans une compagnie
	 */
	public static final int AVION_MIN = 10;
	/**
	 * Nombre d'avion maximum crée dans une compagnie
	 */
	public static final int AVION_MAX = 50;	
	
	/**
	 * Les vols privé sont repésentés comme affectés à la compagnie privée
	 */
	public static final String NOM_COMPAGNIE_PRIVEE = "\"Private flight\"";
	/**
	 * Les vols diplomatique sont repésentés comme affectés à la compagnie diplomatique
	 */
	public static final String NOM_COMPAGNIE_DIPLOMATIQUE = "\"Diplomatic Flight\"";
	
	private String nom;
	private String nationalite;
	private ArrayList<Avion>  flotteDispo;
	private ArrayList<Avion>  flotteUtilise;
	private ArrayList<Pilote> pilotesDispo;
	private ArrayList<Pilote> pilotesUtilise;
	private ArrayList<Personnel> personnelsDispo;
	private ArrayList<Personnel> personnelsUtilise;
	
	public Compagnie(String nom, String nationalite) {
		this.nom = nom;
		this.nationalite = nationalite;
		this.flotteUtilise = new ArrayList<Avion>();
		this.pilotesUtilise = new ArrayList<Pilote>();
		this.personnelsUtilise = new ArrayList<Personnel>();
	}
	
	public Compagnie(String nom, String nationalite, ArrayList<Avion> flotte, ArrayList<Pilote> pilotes, ArrayList<Personnel> personnels) {
		this.nom = nom;
		this.nationalite = nationalite;
		this.flotteDispo = flotte;
		this.pilotesDispo = pilotes;
		this.personnelsDispo = personnels;
		this.flotteUtilise = new ArrayList<Avion>();
		this.pilotesUtilise = new ArrayList<Pilote>();
		this.personnelsUtilise = new ArrayList<Personnel>();
	}
		
	/**
	 * Retourne un avion de la flotte disponible et le met dans la flotte en cours d'utilisation 
	 * @return Avion
	 * @throws Exception if compagnie n'a pas d'avion disponible
	 */
	public Avion utiliserUnAvion() throws Exception{
		ArrayList<Avion> f = this.getFlotteDispo();	
		if(f.size() > 0) {
			Avion a = f.get((int)(Math.random() * f.size()));
			this.getFlotteDispo().remove(a);
			this.getFlotteUtilise().add(a);
			return a;	
		}
		else {
			throw new Exception("Tentative d'utiliser un avion de la Compagnie " + this.getNom() + " mais il n'y a plus d'avion disponible");
		}
	}
	
	/**
	 * Retourne un pilote de la liste disponible et le met dans la liste en cours d'utilisation 
	 * @return Pilote
	 * @throws Exception if compagnie n'a pas de pilote disponible
	 */
	public Pilote utiliserUnPilote() throws Exception{
		ArrayList<Pilote> pilotes = this.getPilotesDispo();	
		if(pilotes.size() > 0) {
			Pilote p = pilotes.get((int)(Math.random() * pilotes.size()));
			this.getPilotesDispo().remove(p);
			this.getPilotesUtilise().add(p);
			return p;	
		}
		else {
			throw new Exception("Tentative d'utiliser un pilote de la Compagnie " + this.getNom() + " mais il n'y a plus de pilotes disponible");
		}
	}
	
	/**
	 * Retourne une liste de pilote de la liste disponible et le met dans la liste en cours d'utilisation 
	 * @param nb nombre de pilote a utiliser
	 * @return ArrayList<Pilote>
	 * @throws Exception if compagnie n'a pas assez de pilotes disponibles
	 */
	public ArrayList<Pilote> utiliserDesPilotes(int nb) throws Exception{
		if(this.getPilotesDispo().size() >= nb) {
			ArrayList<Pilote> pilotes = new ArrayList<Pilote>();
			for(int i = 0; i < nb; i++) {
				pilotes.add(this.utiliserUnPilote());
			}
			return pilotes;
		}
		else {
			throw new Exception("Tentative d'utiliser " +nb + " pilotes de la Compagnie " + this.getNom() + " mais il n'y a pas assez de pilotes disponibles");
		}
	}
	
	
	/**
	 * Retourne un personnel de la liste disponible et le met dans la liste en cours d'utilisation 
	 * @return Personnel
	 * @throws Exception if compagnie n'a pas de pilote disponible
	 */
	public Personnel utiliserUnPersonnel () throws Exception{
		ArrayList<Pilote> personnels = this.getPilotesDispo();	
		if(personnels.size() > 0) {
			Personnel p = personnelsDispo.get((int)(Math.random() * personnels.size()));
			this.getPersonnelsDispo().remove(p);
			this.getPersonnelsUtilise().add(p);
			return p;	
		}
		else {
			throw new Exception("Tentative d'utiliser un personnel de la Compagnie " + this.getNom() + " mais il n'y a plus de personnel disponible");
		}
	}
	
	/**
	 * Retourne une liste de Personnels de la liste disponible et le met dans la liste en cours d'utilisation 
	 * @param nb nombre de Personnels a utiliser
	 * @return ArrayList<Personnels>
	 * @throws Exception if compagnie n'a pas assez de Personnels disponibles
	 */
	public ArrayList<Personnel> utiliserDuPersonnel(int nb) throws Exception{
		if(this.getPersonnelsDispo().size() >= nb) {
			ArrayList<Personnel> personnels = new ArrayList<Personnel>();
			for(int i = 0; i < nb; i++) {
				personnels.add(this.utiliserUnPersonnel());
			}
			return personnels;
		}
		else {
			throw new Exception("Tentative d'utiliser " +nb + " Personnels de la Compagnie " + this.getNom() + " mais il n'y a pas assez de Personnels disponibles");
		}
	}
	
	/**
	 * Utilise un pilote en particulier 
	 * @param Pilote doit appartenir à la compagnie
	 */
	public void setUtilise(Pilote o) {
		if(this.pilotesDispo.contains(o)) {
			this.pilotesUtilise.add(o);
			this.pilotesDispo.remove(o);
		}
		else {
			System.out.println("Le pilote : " + o + " n'appartient pas à la compagnie " + this.getNom());
		}
	}
	
	
	/**
	 * Rend l'objet o utilisable en le mettant dans la liste dispo
	 * @param Avion, doit appartenir à la compagnie
	 */
	public void setUtilisable(Avion o) {
		if(this.flotteUtilise.contains(o)) {
			this.flotteDispo.add(o);
			this.flotteUtilise.remove(o);
		}
		else {
			System.out.println("L'avion : " + o + " n'appartient pas à la compagnie " + this.getNom());
		}
	}
	/**
	 * Rend l'objet o utilisable en le mettant dans la liste dispo
	 * @param Pilote, doit appartenir à la compagnie
	 */
	public void setUtilisable(Pilote o) {
		if(this.pilotesUtilise.contains(o)) {
			this.pilotesDispo.add(o);
			this.pilotesUtilise.remove(o);
		}
		else {
			System.out.println("Le pilote : " + o + " n'appartient pas à la compagnie " + this.getNom());
		}
	}
	/**
	 * Rend l'objet o utilisable en le mettant dans la liste dispo
	 * @param Pilote, doit appartenir à la compagnie
	 */
	public void setUtilisable(Personnel o) {
		if(this.personnelsUtilise.contains(o)) {
			this.personnelsDispo.add(o);
			this.personnelsUtilise.remove(o);
		}
		else {
			System.out.println("Le pilote : " + o + " n'appartient pas à la compagnie " + this.getNom());
		}
	}
	
	
	/**
	 * Genere un nombre de compagnie via le fichier de données en ligne
	 * @param int n nombre de compagnie créer 
	 * @return ArrayList<Compagnie>
	 * @throws IOException si le fichier n'est pas accessible
	 */
	public static ArrayList<Compagnie> generate(int n) throws IOException {
		URL url = new URL("https://raw.githubusercontent.com/jpatokal/openflights/master/data/airlines.dat");
		ArrayList<Compagnie> compagnies = new ArrayList<Compagnie>();
		
		ArrayList<String[]> data = new ArrayList<String[]>();
		Scanner sc = new Scanner(url.openStream());
		if(sc.hasNextLine()) { sc.nextLine(); } else { throw new IOException("File Empty"); } //Première ligne inutile
		int i = 0;
		while(sc.hasNextLine()) {
			data.add(sc.nextLine().split(","));
			if(!Program.allNationalites.contains(data.get(i)[6]) && !data.get(i)[6].equals("\"\"") && data.get(i)[6].matches("\"[A-Z][a-z].*\"")) {
				Program.allNationalites.add(data.get(i)[6]);
			}
			i++;
		}
		
		//Compagnie Diplomatique index 0
		Compagnie diplo = new Compagnie(Compagnie.NOM_COMPAGNIE_DIPLOMATIQUE, "\"\"");
		int nbPilotes = PILOTE_MIN + (int)(Math.random() * ((PILOTE_MAX - PILOTE_MIN) + 1 ));
		int nbAvions = (AVION_MIN + (int)(Math.random() * ((AVION_MAX-AVION_MIN) + 1 )));
		int nbPersonnels = (PERSONNEL_MIN + (int)(Math.random() * ((PERSONNEL_MAX-PERSONNEL_MIN) + 1 )));
		ArrayList<Pilote> pilotes = null;
		ArrayList<Avion>  avions = Avion.generate(nbAvions, diplo);
		ArrayList<Personnel>  personnels = null;
		diplo.setPilotesDispo(pilotes);
		diplo.setFlotteDispo(avions);
		diplo.setPersonnelsDispo(personnels);
		compagnies.add(diplo);
		
		//Compagnie Privée Index 1
		//n-1 car compagnie diplomatique cree 
		for (int j = 0 ; j < n -1 ; j++) { 		
			nbPilotes = PILOTE_MIN + (int)(Math.random() * ((PILOTE_MAX - PILOTE_MIN) + 1 ));
			nbAvions = (AVION_MIN + (int)(Math.random() * ((AVION_MAX-AVION_MIN) + 1 )));
			nbPersonnels = (PERSONNEL_MIN + (int)(Math.random() * ((PERSONNEL_MAX-PERSONNEL_MIN) + 1 )));
			
			Compagnie comp = new Compagnie(data.get(j)[1], data.get(j)[6]);
			
			pilotes = Pilote.generate(nbPilotes, comp);
			avions = Avion.generate(nbAvions, comp);
			personnels = Personnel.generate(nbPersonnels, comp);
			comp.setPilotesDispo(pilotes);
			comp.setFlotteDispo(avions);
			comp.setPersonnelsDispo(personnels);
			
			compagnies.add(comp);
		}
		return compagnies;
	}


	@Override
	public String toString() {
		return "Compagnie: {" + "\n"+ 
					"\tnom: " + nom + "\n"+ 
					"\tnationalite: " + nationalite + "\n"+ 
				"}";
	}

	/**
	 * Retoune le nom de la CompagnieS
	 * @return String
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * Retourne les avions disponible (non utilisés dans le programme)
	 * @return ArrayList<Avion>
	 */
	public ArrayList<Avion> getFlotteDispo() {
		return flotteDispo;
	}

	private void setFlotteDispo(ArrayList<Avion> flotteDispo) {
		this.flotteDispo = flotteDispo;
	}

	/**
	 * Retourne les avions utilisés dans le programme
	 * @return ArrayList<Avion>
	 */
	public ArrayList<Avion> getFlotteUtilise() {
		return flotteUtilise;
	}

	private void setFlotteUtilise(ArrayList<Avion> flotteUtilise) {
		this.flotteUtilise = flotteUtilise;
	}

	/**
	 * Retourne les pilotes disponible (non utilisés dans le programme)
	 * @return ArrayList<Pilote>
	 */
	public ArrayList<Pilote> getPilotesDispo() {
		return pilotesDispo;
	}

	private void setPilotesDispo(ArrayList<Pilote> pilotesDispo) {
		this.pilotesDispo = pilotesDispo;
	}

	/**
	 * Retourne les pilotes utilisés dans le programme
	 * @return ArrayList<Pilote>
	 */
	public ArrayList<Pilote> getPilotesUtilise() {
		return pilotesUtilise;
	}

	private void setPilotesUtilise(ArrayList<Pilote> pilotesUtilise) {
		this.pilotesUtilise = pilotesUtilise;
	}

	/**
	 * Retourne le personnel disponnible (non utilisés dans le programme)
	 * @return ArrayList<Personnel>
	 */
	public ArrayList<Personnel> getPersonnelsDispo() {
		return personnelsDispo;
	}

	/**
	 * Retourne le personnel utilisés dans le programme
	 * @return ArrayList<Personnel>
	 */
	private void setPersonnelsDispo(ArrayList<Personnel> personnelsDispo) {
		this.personnelsDispo = personnelsDispo;
	}

	public ArrayList<Personnel> getPersonnelsUtilise() {
		return personnelsUtilise;
	}

	private void setPersonnelsUtilise(ArrayList<Personnel> personnelsUtilise) {
		this.personnelsUtilise = personnelsUtilise;
	}
}
