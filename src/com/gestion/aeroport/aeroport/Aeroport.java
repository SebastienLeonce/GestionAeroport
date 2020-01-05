package com.gestion.aeroport.aeroport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.avion.AvionLigne;
import com.gestion.aeroport.avion.AvionPrive;
import com.gestion.aeroport.passager.Diplomate;
import com.gestion.aeroport.passager.Passager;
import com.gestion.aeroport.passager.Personnel;
import com.gestion.aeroport.passager.Pilote;


public class Aeroport {

	private static final int  MIN_ARRIVEE_PASSAGER = 100;
	private static final int  MAX_ARRIVEE_PASSAGER = 200;
	private static final int  MIN_ARRIVEE_PERSONNEL = 10;
	private static final int  MAX_ARRIVEE_PERSONNEL = 30;
	
	
	private String nom;
	
	private ArrayList<Piste> pistesDecollage;
	private ArrayList<Piste> pistesAtterrissage; 
	private ArrayList<Vol> radar;
	
	
	private HashMap<Compagnie, ArrayList<Avion>> avionsAuSol;
	private HashMap<Compagnie, Queue<Pilote>> fileAttentePilote;
	private HashMap<Compagnie, Queue<Personnel>> fileAttentePersonnel;
	private HashMap<Program.Destination, Queue<Passager>> fileAttentePassager;
	private HashMap<Program.Destination, Queue<Passager>> fileAttentePassagerPrive;
	private HashMap<Program.Destination, Queue<Diplomate>> fileAttentePassagerDiplomatique;
	private ArrayList<Pilote> reposPilote;
	
	
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistesAtterrissage, ArrayList<Piste> pistesDecollage) {
		this.nom = nom;
		
		
		this.radar = new ArrayList<Vol>(); 
		
		this.pistesDecollage = pistesDecollage;
		this.pistesAtterrissage = pistesAtterrissage;
		this.fileAttentePilote = new HashMap<Compagnie, Queue<Pilote>>();
		this.fileAttentePersonnel = new HashMap<Compagnie, Queue<Personnel>>();
		this.fileAttentePassager = new HashMap<Program.Destination, Queue<Passager>>();
		this.fileAttentePassagerPrive = new HashMap<Program.Destination, Queue<Passager>>();
		this.fileAttentePassagerDiplomatique = new HashMap<Program.Destination, Queue<Diplomate>>();
		this.avionsAuSol = new HashMap<Compagnie, ArrayList<Avion>>();
		this.reposPilote = new ArrayList<Pilote>();
		
		
		for(Program.Destination d : Program.Destination.values()) {
			this.fileAttentePassager.put(d, new LinkedList<Passager>());
			this.fileAttentePassagerPrive.put(d, new LinkedList<Passager>());
			this.fileAttentePassagerDiplomatique.put(d, new LinkedList<Diplomate>());
		}
		
	}
	
	
	public HashMap<Program.Destination, Queue<Passager>> getFileAttentePassagerPrive() {
		return fileAttentePassagerPrive;
	}
	public HashMap<Program.Destination, Queue<Diplomate>> getFileAttentePassagerDiplomatique() {
		return fileAttentePassagerDiplomatique;
	}
	public HashMap<Compagnie, Queue<Personnel>> getFileAttentePersonnel() {
		return fileAttentePersonnel;
	}
	public HashMap<Program.Destination, Queue<Passager>> getFileAttentePassager() {
		return fileAttentePassager;
	}
	public void setFileAttentePassager(HashMap<Program.Destination, Queue<Passager>> fileAttentePassager) {
		this.fileAttentePassager = fileAttentePassager;
	}
	public HashMap<Compagnie, ArrayList<Avion>> getAvionsAuSol() {
		return avionsAuSol;
	}
	public void setAvionsAuSol(HashMap<Compagnie, ArrayList<Avion>> avionsAuSol) {
		this.avionsAuSol = avionsAuSol;
	}
	public HashMap<Compagnie, Queue<Pilote>> getFileAttentePilote() {
		return fileAttentePilote;
	}
	public void setFileAttentePilote(HashMap<Compagnie, Queue<Pilote>> fileAttentePilote) {
		this.fileAttentePilote = fileAttentePilote;
	}
	public ArrayList<Piste> getPistesDecollage() {
		return pistesDecollage;
	}
	public void setPistesDecollage(ArrayList<Piste> pistesDecollage) {
		this.pistesDecollage = pistesDecollage;
	}
	public ArrayList<Piste> getPistesAtterrissage() {
		return pistesAtterrissage;
	}
	public void setPistesAtterrissage(ArrayList<Piste> pistesAtterrissage) {
		this.pistesAtterrissage = pistesAtterrissage;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public ArrayList<Vol> getRadar() {
		return radar;
	}
	public void setRadar(ArrayList<Vol> radar) {
		this.radar = radar;
	}
	@Override
	public String toString() {
		return "Aeroport [nom=" + nom + ", pistesDecollage=" + pistesDecollage + ", pistesAtterrissage="
				+ pistesAtterrissage + ", radar=" + radar + "]";
	}
	
	
	/**
	 * Genere un vol de la compagnies qui a le plus de pilote en attente
	 * Prend les pilotes d'une file d'attente d'une compagnie et les places dans un vol
	 * Si un vol a été créer il en enregistré dans this.volsEnPreparation
	 * @return true si un vol a été créer, false sinon
	 */
	public boolean generateVol() {
		
		//Compagnie avec le plus de pilotes en attente et au moins 1 avion au sol
		Compagnie c = null;
		int maxPiloteAttente = 0;
		//Toutes les compagnies sauf Prive et Diplomatique
		for(int i = 2; i < Program.compagnies.size();i++) {
			Compagnie comp = Program.compagnies.get(i);
			if(this.fileAttentePilote.get(comp).size() > maxPiloteAttente && this.avionsAuSol.get(comp).size() > 0) {
				c = comp;
				maxPiloteAttente = this.fileAttentePilote.get(comp).size();
			}
		}
		if(c != null) {
			//Destination avec le plus de passager en attente 
			Program.Destination dest = null;
			int maxPassagerAttente  = 0;
			for(Program.Destination d : Program.Destination.values()) {
				if(this.fileAttentePassager.get(d).size() > maxPassagerAttente) {
					dest = d;
					maxPassagerAttente = this.fileAttentePassager.get(d).size();
				}
			}
		
			if(dest != null) {
				//Selectionne avion avec la capacite la plus proche du nombre de personnes en attente
				ArrayList<Avion> avions = this.avionsAuSol.get(c);
				Avion a = avions.get(0);
				int differenceCapaciteAttente = Math.abs(a.getCapaciteMax() - maxPassagerAttente);
				for(int i = 1; i < avions.size(); i++) {
					if(Math.abs(avions.get(i).getCapaciteMax() - maxPassagerAttente) < differenceCapaciteAttente){
						differenceCapaciteAttente = Math.abs(avions.get(i).getCapaciteMax() - maxPassagerAttente);
						a = avions.get(i);
					}
				}	
				
				//Remplissage du Vol
				try {
					for(int i = 0; i < a.getNbPilotesMin(); i++) {
						a.ajouterPilote(this.fileAttentePilote.get(c).remove());
					}
					if(a instanceof AvionLigne) {
						AvionLigne avion = (AvionLigne)a;
						ArrayList<Personnel> personnels = c.utiliserDuPersonnel(avion.getNbPersonnelsMin());
						for(Personnel p : personnels) {
							avion.ajouterPersonnel(p);
						}
					}
				} catch (Exception e) {
					//e.printStackTrace();
					return false;
				}
				int n;
				if(a.getCapaciteMax() - a.getCapacite() < maxPassagerAttente) {
					n = a.getCapaciteMax() - a.getCapacite();
				}
				else {
					n =  maxPassagerAttente;
				}			
				for(int i = 0; i < n; i++) {
					a.ajouterPassager(new Passager(dest.toString(), false));
				}
				
				Vol v = new Vol(a, dest.getAeroport(), this, c);
				Program.DemandeDecollage(v);
				return true;
			}
		}
		return false;		
	}
	
	public void generateVolPrive() {
		System.out.println("=========Preparation d'un Vol Prive==========");
		Compagnie c = Program.compagnies.get(1);
		for(Program.Destination d : Program.Destination.values()) {
			if(this.fileAttentePassagerPrive.get(d).size() > 0) {
				if(this.avionsAuSol.get(c).size() > 0) {
					AvionPrive a = (AvionPrive)this.avionsAuSol.get(c).get(0);
					if(this.fileAttentePilote.get(c).size() >= a.getNbPilotesMin()) {
						for(int i = 0 ; i <a.getNbPilotesMin(); i++) {
							a.ajouterPilote(this.getFileAttentePilote().get(c).remove());
						}
						for(int i = 0; i <= this.fileAttentePassagerPrive.get(d).size(); i++) {
							a.ajouterPassager(this.fileAttentePassagerPrive.get(d).remove());
						}
						this.avionsAuSol.get(c).remove(a);
						Vol v = new Vol(a, d.getAeroport(), this, c);
						Program.DemandeDecollage(v);
					}
					else {
						System.out.println("En attente de " + (a.getNbPilotesMin() - this.fileAttentePilote.get(c).size()) +  " pilote(s) disponible(s) pour un vol privé à destination de " + d.toString());
					}
				}
				else {
					System.out.println("En attente d'un avion disponible pour un vol privé à destination de " + d.toString());
				}
			}
		}
	}
	public void generateVolDiplomatique() {
		System.out.println("=========Preparation d'un Vol Diplomatique==========");
	}
	
	
	/**
	 * Creer de nouveaux passagers dans l'aeroport avec une destination random
	 * @return nombre de passager crï¿½ï¿½
	 */
	public void ArriveePassagerDansAeroport() {
		int random = MIN_ARRIVEE_PASSAGER + (int)(Math.random() * ((MAX_ARRIVEE_PASSAGER-MIN_ARRIVEE_PASSAGER) + MIN_ARRIVEE_PASSAGER ));
		boolean prive = false;
		boolean diplomate = false;
		
		for(int i = 0; i < random; i++) {
			Program.Destination d = Program.Destination.randomDestination();
			//1 chance sur 100 que le passager souhaite prendre un vol privé
			int r = 1 + (int)(Math.random() * ((100-1) + 1 ));
			if(r == 1) {
				Passager p = new Passager(d.toString(), true);
				this.getFileAttentePassagerPrive().get(d).add(p);
				prive = true;
			}
			else if (r == 2){
				Diplomate p = new Diplomate(d.toString(), 42, Program.randomNationalite());
				this.getFileAttentePassagerDiplomatique().get(d).add(p);
				diplomate = true;
			}
			else {
				Passager p = new Passager(d.toString(), false);
				this.getFileAttentePassager().get(d).add(p);
			}	
		}
		for(Compagnie c : Program.compagnies) {
			random = MIN_ARRIVEE_PERSONNEL + (int)(Math.random() * ((MAX_ARRIVEE_PERSONNEL - MIN_ARRIVEE_PERSONNEL) + MIN_ARRIVEE_PERSONNEL ));
			for(int i = 0; i < random; i++) {
				try {
					this.getFileAttentePersonnel().get(c).add(c.utiliserUnPersonnel());
				} catch (Exception e) {
					//Plus de personnel disponible
					//e.printStackTrace();
					break;
				}
			}
		}
		//Si un ou plusieurs passagers a vol prive sont arrives son vol est pepare
		if(prive) {
			this.generateVolPrive();
		}
		//Si un ou plusieurs diplomate a vol prive sont arrives son vol est pepare
		if(diplomate) {
			this.generateVolDiplomatique();
		}
		
		
	}
		
	
	/**
	 * Fait atterir les avion en premiere place de la file d'attente
	 */
	public void Atterrissage() {
		for(Piste p: pistesAtterrissage) {
			if(p.getEspacement() <= 0) {
				if(p.getFileDAttente().size()>0) {
					Vol v = p.getFileDAttente().remove(0);
					this.radar.remove(v);
					System.out.println("Le vol " + v + " vient d'atterir\n");
					
					//Seul les pilotes restent dans l'aeroport
					for(Passager passager : v.getOccupants()) {
						if(passager instanceof Pilote) {
							Pilote pilote = (Pilote)passager;
							this.reposPilote.add(pilote);
						}
					}
					p.setEspacement(v.getAvion().getEspacement());;
				}
			}
			else {
				p.setEspacement(p.getEspacement() - 1);
				System.out.println("Piste n°" + p.getId() +" en cours de préparation\nElle sera prete dans " + p.getEspacement() + " intervale(s)." );
			}
		}
	}
	/**
	 * Fait decoler les avion en premiere place de la file d'attente
	 * Rend l'avion à nouveau utilisable
	 */
	public void Decollage() {
		for(Piste p: pistesDecollage) {
			if(p.getEspacement() <= 0) {
				if(p.getFileDAttente().size()>0) {
					Vol v = p.getFileDAttente().remove(0);
					System.out.println("Le vol " + v + " vient de decoller\n");
					Avion a = v.getAvion();
					this.avionsAuSol.get(v.getCompagnie()).remove(a);
					v.getCompagnie().setUtilisable(a);
					for(Pilote pilote : a.getPilotes()) {
						pilote.getEmployeur().setUtilisable(pilote);
					}
				
					
					if(a instanceof AvionLigne) {
						AvionLigne al = (AvionLigne)a;
						for(Personnel personnel : al.getPersonnels()) {
							v.getCompagnie().setUtilisable(personnel);
						}
					}
					p.setEspacement(a.getEspacement());;
				}
			}
			else {
				p.setEspacement(p.getEspacement() - 1);
				System.out.println("Piste n°" + p.getId() +" en cours de préparation\nElle sera prete dans " + p.getEspacement() + " intervale(s)." );
			}
		}
		
		
	}
	
	
	public void consommationCarburant() {
		for(Vol v : this.radar) {
			Avion a = v.getAvion();
			a.setVolCarburant(a.getVolCarburant() - a.getConsomamtionCarburant());
			if(a.getConsomamtionCarburant() <= 0) {
				System.out.println("Le vol " + v + " s'est écrasé par manque de carburant, vous avez à votre tache !");
				Program.programRunning = false;
			}
				
		}
	}
	/**
	 * Incrémente le cooldown des pilotes jusqu'a ce que leur temps de repos soit terminé
	 */
	public void repos() {
		Iterator<Pilote> i = this.reposPilote.iterator();
		while(i.hasNext()) {
			Pilote p = i.next();
			if(p.getCooldown() >= p.getTempsPause()) {
				this.fileAttentePilote.get(p.getEmployeur()).add(p);
				i.remove();
				p.setCooldown(0);
			}
			else {
				p.setCooldown(p.getCooldown() + 1);
			}
		}
	}
	
	
	
	
	/**
	 * Calcul et insï¿½re un vol sur une piste en foction de son carburant
	 */
	public static int calculPosition(Piste p, Vol v) {
		int position = p.getFileDAttente().size();
		for(int i = 0; i< p.getFileDAttente().size(); i++) {
			if(v.getAvion().getVolCarburant() < p.getFileDAttente().get(i).getAvion().getVolCarburant()) {
				position = i;
				break;
			}
		}
		p.getFileDAttente().add(position, v);
		return position;
	}
	
	
	public boolean eteindrePiste(int id) {
		int enMarche = 0;
		Piste piste = null;
		int nbAvionsPiste = 99999;
		int idPisteVide = -1;
		ArrayList<Vol> liste;

		//////////////////////////
		//Fermeture piste decollage
		for (int i = 0; i < pistesDecollage.size(); i++) {
			
			if (pistesDecollage.get(i).getId() == id && pistesDecollage.get(i).getEnMarche()) {
				piste = pistesDecollage.get(i);
			} else if (pistesDecollage.get(i).getEnMarche()) {
				enMarche++;
			}
		}
		
		if (enMarche >= 1 && piste != null) {
		
			for (int i = 0; i < piste.getFileDAttente().size(); i++) {
				
				for (int j = 0; j < pistesDecollage.size(); j++) {
					if (pistesDecollage.get(j).getEnMarche() && pistesDecollage.get(j).getFileDAttente().size() < nbAvionsPiste) {
						idPisteVide = j;
						nbAvionsPiste = pistesDecollage.get(j).getFileDAttente().size();
					}
				}
				
				liste = pistesDecollage.get(idPisteVide).getFileDAttente();
				liste.add(piste.getFileDAttente().get(i));
				pistesDecollage.get(idPisteVide).setFileDAttente(liste);
			}
			
			piste.setFileDAttente(new ArrayList<Vol>());
			piste.setEnMarche(false);
			
			return true;
		}
		
		enMarche = 0;
		
		/////////////////////////////
		//Fermeture piste atterissage
		for (int i = 0; i < pistesAtterrissage.size(); i++) {
			
			if (pistesAtterrissage.get(i).getId() == id && pistesAtterrissage.get(i).getEnMarche()) {
				piste = pistesAtterrissage.get(i);
			} else if (pistesAtterrissage.get(i).getEnMarche()) {
				enMarche++;
			}
		}
		
		if (enMarche >= 1 && piste != null) {
		
			for (int i = 0; i < piste.getFileDAttente().size(); i++) {
				
				for (int j = 0; j < pistesAtterrissage.size(); j++) {
					if (pistesAtterrissage.get(j).getEnMarche() && pistesAtterrissage.get(j).getFileDAttente().size() < nbAvionsPiste) {
						idPisteVide = j;
						nbAvionsPiste = pistesAtterrissage.get(j).getFileDAttente().size();
					}
				}
				
				liste = pistesAtterrissage.get(idPisteVide).getFileDAttente();
				liste.add(piste.getFileDAttente().get(i));
				pistesAtterrissage.get(idPisteVide).setFileDAttente(liste);
			}
			
			piste.setFileDAttente(new ArrayList<Vol>());
			piste.setEnMarche(false);
			
			return true;
		}
		
		return false;
	}
	
	
	public boolean ouvrirPiste(int id) {
		Piste piste = null;
		int moyenne = 0;
		int enMarche = 0;
		int nbAvionsPiste = -1;
		int idPiste = -1;
		ArrayList<Vol> liste;
		
		// Ouverture piste decollage
		for (int i = 0; i < pistesDecollage.size(); i++) {
	        
	        if (pistesDecollage.get(i).getId() == id && !pistesDecollage.get(i).getEnMarche()) {
	        	piste = pistesDecollage.get(i);
	        } else if (pistesDecollage.get(i).getEnMarche()) {
	        	enMarche++;
	        	moyenne += pistesDecollage.get(i).getFileDAttente().size();
	        }
	    }
		
		if (piste != null) {
			moyenne /= enMarche;
			
			while (piste.getFileDAttente().size() < moyenne) {
				for (int i = 0; i < pistesDecollage.size(); i++) {
					
					if (pistesDecollage.get(i).getEnMarche() && pistesDecollage.get(i).getFileDAttente().size() > nbAvionsPiste) {
						idPiste = i;
						nbAvionsPiste = pistesDecollage.get(i).getFileDAttente().size();
					}
				}
				liste = piste.getFileDAttente();
				liste.add(pistesDecollage.get(idPiste).getFileDAttente().get(0));
				piste.setFileDAttente(liste);
				
				liste = pistesDecollage.get(idPiste).getFileDAttente();
				liste.remove(0);
				pistesDecollage.get(idPiste).setFileDAttente(liste);			
			}
			
			piste.setEnMarche(true);
			return true;	
		}
		
		moyenne = 0;
		enMarche = 0;
		
		// Ouverture piste atterissage
		for (int i = 0; i < pistesAtterrissage.size(); i++) {
		        
			if (pistesAtterrissage.get(i).getId() == id && !pistesAtterrissage.get(i).getEnMarche()) {
	        	piste = pistesAtterrissage.get(i);
	        } else if (pistesAtterrissage.get(i).getEnMarche()) {
	        	enMarche++;
	        	moyenne += pistesAtterrissage.get(i).getFileDAttente().size();
		    }
	    }
				
		if (piste != null) {
			moyenne /= enMarche;
					
			while (piste.getFileDAttente().size() < moyenne) {
				for (int i = 0; i < pistesAtterrissage.size(); i++) {
							
					if (pistesAtterrissage.get(i).getEnMarche() && pistesAtterrissage.get(i).getFileDAttente().size() > nbAvionsPiste) {
						idPiste = i;
						nbAvionsPiste = pistesAtterrissage.get(i).getFileDAttente().size();
					}
				}
				liste = piste.getFileDAttente();
				liste.add(pistesAtterrissage.get(idPiste).getFileDAttente().get(0));
				piste.setFileDAttente(liste);
					
				liste = pistesAtterrissage.get(idPiste).getFileDAttente();
				liste.remove(0);
				pistesAtterrissage.get(idPiste).setFileDAttente(liste);			
			}
				
			piste.setEnMarche(true);
			return true;	
		}			
		
		return false;
	}
	public boolean annulerVol(int id) {
		ArrayList<Passager> listeOccupants;

		for (Piste piste : getPistesDecollage()) {
			for (Vol vol : piste.getFileDAttente()) {
				if (vol.getNumeroDeVol() == id) {
					listeOccupants = vol.getOccupants();
					
					for (Passager occupant : listeOccupants) {
						this.getFileAttentePassager();
						this.getFileAttentePassagerDiplomatique();
						this.getFileAttentePassagerPrive();
						this.getFileAttentePersonnel();
						this.getFileAttentePilote();
						////////////////////////////////////////////
					}
					
					return true;
				}
			}
		}
		
		return false;
	}	
}
