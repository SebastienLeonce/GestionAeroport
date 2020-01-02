package com.gestion.aeroport.aeroport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Passager;
import com.gestion.aeroport.passager.Pilote;


public class Aeroport {

	private static final int  MIN_ARRIVEE_PASSAGER = 100;
	private static final int  MAX_ARRIVEE_PASSAGER = 200;
	
	private String nom;
	
	private ArrayList<Piste> pistesDecollage;
	private ArrayList<Piste> pistesAtterrissage; 
	private ArrayList<Vol> radar;
	
	
	private ArrayList<Vol> volsEnPreparation;
	private ArrayList<Passager> passagersDansAeroport;
	
	private HashMap<Compagnie, ArrayList<Avion>> avionsAuSol;
	private HashMap<Compagnie, Queue<Pilote>> fileAttentePilote;
	private HashMap<Program.Destination, Queue<Passager>> fileAttentePassager;
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistesAtterrissage, ArrayList<Piste> pistesDecollage) {
		this.nom = nom;
		
		//
		this.passagersDansAeroport = new ArrayList<Passager>();
		this.ArriveePassagerDansAeroport();
		
		this.radar = new ArrayList<Vol>(); 
		this.volsEnPreparation = new ArrayList<Vol>();		
		
		this.pistesDecollage = pistesDecollage;
		this.pistesAtterrissage = pistesAtterrissage;
		this.fileAttentePilote = new HashMap<Compagnie, Queue<Pilote>>();
		this.fileAttentePassager = new HashMap<Program.Destination, Queue<Passager>>();
		this.avionsAuSol = new HashMap<Compagnie, ArrayList<Avion>>();
	}
	
	
	public HashMap<Compagnie, ArrayList<Avion>> getAvionsAuSol() {
		return avionsAuSol;
	}
	public void setAvionsAuSol(HashMap<Compagnie, ArrayList<Avion>> avionsAuSol) {
		this.avionsAuSol = avionsAuSol;
	}
	public ArrayList<Vol> getVolsEnPreparation() {
		return volsEnPreparation;
	}
	public void setVolsEnPreparation(ArrayList<Vol> volsEnPreparation) {
		this.volsEnPreparation = volsEnPreparation;
	}
	public void setPassagersDansAeroport(ArrayList<Passager> passagersDansAeroport) {
		this.passagersDansAeroport = passagersDansAeroport;
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
	public ArrayList<Passager> getPassagersDansAeroport() {
		return passagersDansAeroport;
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
		
		Compagnie c = null;
		int maxPiloteAttente = 0;
		for(Compagnie comp : Program.compagnies) {
			if(this.fileAttentePilote.get(comp).size() > maxPiloteAttente) {
				c = comp;
				maxPiloteAttente = this.fileAttentePilote.get(comp).size();
			}
		}
		if(c != null) {
			Avion a;
			try {
				a = c.utiliserUnAvion();
				if(this.fileAttentePilote.get(c).size() >= a.getNbPilotesMin()) {
					for (int i  = 0 ; i < a.getNbPilotesMin(); i++) {
						a.ajouterPilote(this.fileAttentePilote.get(c).remove());
					}
					
					Aeroport aer = Program.autresAeroports.get((int)(Math.random() * Program.autresAeroports.size()));				
					Vol v = new Vol(a,aer,this);
					this.volsEnPreparation.add(v);
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;		
	}
	
	/**
	 * Crï¿½er de nouveaux passagers dans l'aï¿½roport avec une destination random
	 * @return nombre de passager crï¿½ï¿½
	 */
	public int ArriveePassagerDansAeroport() {
		int random = MIN_ARRIVEE_PASSAGER + (int)(Math.random() * ((MAX_ARRIVEE_PASSAGER-MIN_ARRIVEE_PASSAGER) + MIN_ARRIVEE_PASSAGER ));
		for(int i = 0; i < random; i++) {
			this.passagersDansAeroport.add(new Passager(Program.Destination.randomDestination().toString()));
		}
		return random;
	}
	
	/**
	 * Rempli les vols en attente avec des passagers dans l'aï¿½roport
	 */
	public void RemplissageVol() {		
		Iterator<Vol> i = this.volsEnPreparation.iterator();
		while (i.hasNext()) {
			Vol v = i.next();
			Iterator<Passager> j = this.passagersDansAeroport.iterator();
			while (j.hasNext()) {
				Passager p = j.next();			
				if(p.getVoyage() == v.getArrivee().getNom()) {
					v.getAvion().ajouterPassager(p);
					j.remove();
				}
				if(v.getNbPersonneABord() == v.getAvion().getCapaciteMax()) {
					Program.DemandeDecollage(v);
					i.remove();
					break;
				}
			}
		}
	}
	
	
	/**
	 * Fait attï¿½rir les avion en premiï¿½re place de la file d'attente
	 */
	public void Atterissage() {
		for(Piste p: pistesAtterrissage) {
			if(p.getCooldown() >= p.getEspacement()) {
				if(p.getFileDAttente().size()>0) {
					Vol v = p.getFileDAttente().remove(0);
					System.out.println("Le vol " + v + " vient d'attï¿½rir");
					
					//Seul les pilotes restent dans l'aeroport
					for(Passager passager : v.getAvion().getPassagers()) {
						if(passager.getClass() == Pilote.class) {
							Pilote pilote = (Pilote)passager;
							this.passagersDansAeroport.add(pilote);
							this.fileAttentePilote.get(pilote.getEmployeur()).add(pilote);
						}
					}
					p.setCooldown(0);
				}
			}
			else {
				p.setCooldown(p.getCooldown()+1);
				System.out.println("Piste en cours de préparation\\n=======================");
			}
		}
	}
	/**
	 * Fait decoler les avion en premiere place de la file d'attente
	 * Rend l'avion à nouveau utilisable
	 */
	public void Decollage() {
		for(Piste p: pistesDecollage) {
			if(p.getCooldown() >= p.getEspacement()) {
				if(p.getFileDAttente().size()>0) {
					Vol v = p.getFileDAttente().remove(0);
					System.out.println("Le vol " + v + " vient de dï¿½coller");
					//Recupere la compagnie via l'employeur du pilote (Tous les pilotes étant de la même compagnie)
					v.getAvion().getPilotes().get(0).getEmployeur().setUtilisable(v.getAvion());
				}
			}
			else {
				p.setCooldown(p.getCooldown()+1);
				System.out.println("Piste en cours de préparation\n=======================");
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
		ArrayList<ArrayList<Vol>> parts;
		ArrayList<Vol> fileAttente = new ArrayList<Vol>();
		int k = 0;
		
		for (int i = 0; i < pistesDecollage.size(); i++) {
	        if (pistesDecollage.get(i).getEnMarche()) {
	        	enMarche++;
	        }
	        
	        if (pistesDecollage.get(i).getId() == id) {
	        	piste = pistesDecollage.get(i);
	        }
	    }
		
		// on ferme une piste de decollage
		if (piste != null && enMarche > 1 && piste.getEnMarche() == true) { 
			piste.setEnMarche(false);
			
			parts = chopped(piste.getFileDAttente(), enMarche - 1);
			
			piste.setFileDAttente(new ArrayList<Vol>());
			
			for (int i = 0; i < pistesDecollage.size(); i++) {
				if (pistesDecollage.get(i).getEnMarche()) {
					fileAttente.addAll(pistesDecollage.get(i).getFileDAttente());
					fileAttente.addAll(parts.get(k));

					pistesDecollage.get(i).setFileDAttente(fileAttente);
					
					k++;
				}
			}
			
			return true;
		}
		
		enMarche = 0;
		piste = null;
		
		for (int i = 0; i < pistesAtterrissage.size(); i++) {
	        if (pistesAtterrissage.get(i).getEnMarche()) {
	        	enMarche++;
	        }
	        
	        if (pistesAtterrissage.get(i).getId() == id) {
	        	piste = pistesAtterrissage.get(i);
	        }
	    }
		
		// on ferme une piste d'atterissage
		if (piste != null && enMarche > 1 && piste.getEnMarche() == true) { 
			piste.setEnMarche(false);
			
			parts = chopped(piste.getFileDAttente(), enMarche - 1);
			
			piste.setFileDAttente(new ArrayList<Vol>());
			
			for (int i = 0; i < pistesAtterrissage.size(); i++) {
				if (pistesAtterrissage.get(i).getEnMarche()) {
					fileAttente.addAll(pistesAtterrissage.get(i).getFileDAttente());
					fileAttente.addAll(parts.get(k));

					pistesAtterrissage.get(i).setFileDAttente(fileAttente);
					
					k++;
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	////////////////////////////////////////////////////
	//       partage de file d'attente a faire        //
	////////////////////////////////////////////////////
	public boolean ouvrirPiste(int id) {
		Piste piste = null;
		
		for (int i = 0; i < pistesDecollage.size(); i++) {
	        
	        if (pistesDecollage.get(i).getId() == id) {
	        	piste = pistesDecollage.get(i);
	        }
	        //if en marche // enmarche++ // 
	    }
		
		// on ouvre une piste de decollage
		if (piste != null && piste.getEnMarche() == false) { 
			piste.setEnMarche(true);
			// for // piste a prendre enMarche / (n - 1) // rajouter dans la piste ouverte
			return true;
		}
	
		piste = null;
		
		for (int i = 0; i < pistesAtterrissage.size(); i++) {
	       
	        if (pistesAtterrissage.get(i).getId() == id) {
	        	piste = pistesAtterrissage.get(i);
	        }
	    }
		
		// on ouvre une piste d'atterissage
		if (piste != null && piste.getEnMarche() == false) { 
			piste.setEnMarche(true);
			return true;
		}
		
		return false;
	}
	
	// Decoupe une arrayList en L arrayList
	static <T> ArrayList<ArrayList<T>> chopped(ArrayList<T> list, final int L) {
		ArrayList<ArrayList<T>> parts = new ArrayList<ArrayList<T>>();
	    final int N = list.size();
	    for (int i = 0; i < N; i += L) {
	        parts.add(new ArrayList<T>(
	            list.subList(i, Math.min(N, i + L)))
	        );
	    }
	    return parts;
	}
	
	
}
