package com.gestion.aeroport.aeroport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Passager;
import com.gestion.aeroport.passager.Pilote;


public class Aeroport {

	private static final int  MIN_ARRIVEE_PASSAGER = 20;
	private static final int  MAX_ARRIVEE_PASSAGER = 50;
	
	private String nom;
	
	private ArrayList<Piste> pistesDecollage;
	private ArrayList<Piste> pistesAtterissage; 
	private ArrayList<Vol> radar;
	
	private ArrayList<Vol> volsEnPreparation;
	private ArrayList<Passager> passagersDansAeroport;
	
	private HashMap<Compagnie, ArrayList<Pilote>> fileAttentePilote;
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistesAtterissage, ArrayList<Piste> pistesDecollage) {
		this.nom = nom;
		
		//
		this.passagersDansAeroport = new ArrayList<Passager>();
		this.ArriveePassagerDansAeroport();
		
		this.radar = new ArrayList<Vol>(); 
		this.volsEnPreparation = new ArrayList<Vol>();		
		
		this.pistesDecollage = pistesDecollage;
		this.pistesAtterissage = pistesAtterissage;
		this.fileAttentePilote = new HashMap<Compagnie, ArrayList<Pilote>>();
	}
	
	
	public ArrayList<Vol> getVolsEnPreparation() {
		return volsEnPreparation;
	}
	public void setVolsEnPreparation(ArrayList<Vol> volsEnPreparation) {
		this.volsEnPreparation = volsEnPreparation;
	}
	public ArrayList<Passager> getPassagersDansAeroport() {
		return passagersDansAeroport;
	}
	public void setPassagersDansAeroport(ArrayList<Passager> passagersDansAeroport) {
		this.passagersDansAeroport = passagersDansAeroport;
	}
	
	public HashMap<Compagnie, ArrayList<Pilote>> getFileAttentePilote() {
		return fileAttentePilote;
	}
	public void setFileAttentePilote(HashMap<Compagnie, ArrayList<Pilote>> fileAttentePilote) {
		this.fileAttentePilote = fileAttentePilote;
	}
	public ArrayList<Piste> getPistesDecollage() {
		return pistesDecollage;
	}
	public void setPistesDecollage(ArrayList<Piste> pistesDecollage) {
		this.pistesDecollage = pistesDecollage;
	}
	public ArrayList<Piste> getPistesAtterissage() {
		return pistesAtterissage;
	}
	public void setPistesAtterissage(ArrayList<Piste> pistesAtterissage) {
		this.pistesAtterissage = pistesAtterissage;
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
		return "Aeroport [nom=" + nom + ", pistesDecollage=" + pistesDecollage + ", pistesAtterissage="
				+ pistesAtterissage + ", radar=" + radar + "]";
	}
	
	
	/**
	 * Genere un vol
	 * MANQUE DE PILOTES NON VERIFIE
	 * @param depart
	 */
	public void generateVol(Aeroport depart) {
		
		this.fileAttentePilote.forEach((k, v) ->{
			System.out.format("key: %s, value: %d%n", k, v);
		});
		
		Compagnie c = Program.compagnies.get((int)(Math.random() * Program.compagnies.size()));
		ArrayList<Avion> f = c.getFlotte();	
		
		Avion a = f.get((int)(Math.random() * f.size()));
		ArrayList<Pilote> pilotes = c.getPilotes();
		
		int compt = 0; 
		while(compt < a.getNbPilotesMin()) {
			Pilote p = pilotes.get((int)(Math.random() * f.size()));
			if(!p.isEnVol()) {
				a.ajouterPilote(p);
				p.setEnVol(true);
				compt++;
			}
		}
		
		
		Aeroport aer = Program.autresAeroports.get((int)(Math.random() * Program.autresAeroports.size()));				
		Vol v = new Vol(a,depart,aer);
		this.volsEnPreparation.add(v);
	}
	
	/**
	 * Cr�er de nouveaux passagers dans l'a�roport avec une destination random
	 * @return nombre de passager cr��
	 */
	public int ArriveePassagerDansAeroport() {
		int random = MIN_ARRIVEE_PASSAGER + (int)(Math.random() * ((MAX_ARRIVEE_PASSAGER-MIN_ARRIVEE_PASSAGER) + MIN_ARRIVEE_PASSAGER ));
		for(int i = 0; i < random; i++) {
			this.passagersDansAeroport.add(new Passager(Program.Destination.randomDestination().toString()));
		}
		return random;
	}
	
	/**
	 * Rempli les vols en attente avec des passagers dans l'a�roport
	 */
	public void RemplissageVol() {
		for(Vol v : this.volsEnPreparation) {
			for(Passager p : this.passagersDansAeroport) {
				if(p.getVoyage() == v.getArrivee().getNom()) {
					v.getOccupants().add(p);
					this.passagersDansAeroport.remove(p);
				}
			}
		}
	}
	
	
	/**
	 * Fait att�rir les avion en premi�re place de la file d'attente
	 * ESPACEMENT NON G�R�
	 */
	public void Atterissage() {
		for(Piste p: pistesAtterissage) {
			if(p.getFileDAttente().size()>0) {
				Vol v = p.getFileDAttente().remove(0);
				System.out.println("Le vol " + v + " vient d'att�rir");
				
				//Seul les pilotes restent dans l'aeroport
				for(Passager passager : v.getAvion().getPassagers()) {
					if(passager.getClass() == Pilote.class) {
						Pilote pilote = (Pilote)passager;
						this.passagersDansAeroport.add(pilote);
						pilote.setEnVol(false);
					}
				}
				
			}			
		}
	}
	/**
	 * Fait d�coler les avion en premi�re place de la file d'attente
	 * ESPACEMENT NON G�R�
	 */
	public void Decollage() {
		for(Piste p: pistesDecollage) {
			if(p.getFileDAttente().size()>0) {
				Vol v = p.getFileDAttente().remove(0);
				System.out.println("Le vol " + v + " vient de d�coller");
			}			
		}
	}
	
	
	/**
	 * Calcul et ins�re un vol sur une piste en foction de son carburant
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
		
		for (int i = 0; i < pistesAtterissage.size(); i++) {
	        if (pistesAtterissage.get(i).getEnMarche()) {
	        	enMarche++;
	        }
	        
	        if (pistesAtterissage.get(i).getId() == id) {
	        	piste = pistesAtterissage.get(i);
	        }
	    }
		
		// on ferme une piste d'atterissage
		if (piste != null && enMarche > 1 && piste.getEnMarche() == true) { 
			piste.setEnMarche(false);
			
			parts = chopped(piste.getFileDAttente(), enMarche - 1);
			
			piste.setFileDAttente(new ArrayList<Vol>());
			
			for (int i = 0; i < pistesAtterissage.size(); i++) {
				if (pistesAtterissage.get(i).getEnMarche()) {
					fileAttente.addAll(pistesAtterissage.get(i).getFileDAttente());
					fileAttente.addAll(parts.get(k));

					pistesAtterissage.get(i).setFileDAttente(fileAttente);
					
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
		
		for (int i = 0; i < pistesAtterissage.size(); i++) {
	       
	        if (pistesAtterissage.get(i).getId() == id) {
	        	piste = pistesAtterissage.get(i);
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
