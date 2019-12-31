package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

import com.gestion.aeroport.passager.Passager;

public class Aeroport {
 
	
	private String nom;
	
	private ArrayList<Piste> pistesDecollage;
	private ArrayList<Piste> pistesAtterissage; 
	
	private ArrayList<Vol> radar = new ArrayList<Vol>(); 
	
	private ArrayList<Passager> passagersDansAeroport;
	
	
	public Aeroport(String nom) {
		this.nom = nom;
	}
	public Aeroport(String nom, ArrayList<Piste> pistesAtterissage, ArrayList<Piste> pistesDecollage) {
		this.nom = nom;
		
		//
		this.passagersDansAeroport = new ArrayList<Passager>();
		int random = 20 + (int)(Math.random() * ((50-20) + 20 ));
		for(int i = 0; i < random; i++) {
			passagersDansAeroport.add(new Passager(Program.Destination.randomDestination().toString()));
		}	
		
		this.pistesDecollage = pistesDecollage;
		this.pistesAtterissage = pistesAtterissage;
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
	
	
	public void Atterissage() {
		for(Piste p: pistesAtterissage) {
			if(p.getFileDAttente().size()>0) {
				Vol v = p.getFileDAttente().remove(0);
				System.out.println("Le vol " + v + " vient d'attérir");
			}			
		}
	}
	public void Decollage() {
		for(Piste p: pistesDecollage) {
			if(p.getFileDAttente().size()>0) {
				Vol v = p.getFileDAttente().remove(0);
				System.out.println("Le vol " + v + " vient de décoller");
			}			
		}
	}
	
	
	//Calcul et insère à la position optimal un avion dans la file d'attente
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
