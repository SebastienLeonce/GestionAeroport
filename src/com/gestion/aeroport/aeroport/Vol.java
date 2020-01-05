package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.avion.AvionLigne;
import com.gestion.aeroport.passager.Passager;

/**
 * Représente un Avion en avec ses occupants, un Aeroport de départ et un d'arrivée
 */
public class Vol {

    private static int compteur = 1;
    private int numeroDeVol;
	private Avion avion;
    private Aeroport aeroportArrivee;
    private Aeroport aeroportDepart;
    private Compagnie compagnie;

	public Vol(Avion avion, Aeroport arrivee, Aeroport depart, Compagnie compagnie){
        this.aeroportArrivee = arrivee;
        this.aeroportDepart = depart;
        this.avion = avion;
        this.compagnie = compagnie;
        this.numeroDeVol = compteur;
        compteur++;
        
        setInfoVol();
    }

    public Avion getAvion(){
        return this.avion;
    }
	public Aeroport getDepart(){
        return this.aeroportDepart;
    }
    public Aeroport getArrivee(){
        return this.aeroportArrivee;
    }
    public Compagnie getCompagnie() {
		return compagnie;
	}
    public int getNumeroDeVol() {
		return numeroDeVol;
	}
    
    private void setInfoVol () {
    	ArrayList<Passager> liste = getOccupants();
    	
    	for (Passager passager : liste) {
    		passager.setNumeroVol(numeroDeVol);
    		passager.setHistorique(aeroportDepart.getNom() + "->" + aeroportArrivee + ";");
    	}
    }

	/**
     * Recupere tout les occupants du vol
     * @return ArrayList<Passager>
     */
    public ArrayList<Passager> getOccupants(){
    	Avion a = this.avion;
    	ArrayList<Passager> result = new ArrayList<Passager>();
    	result.addAll(a.getPassagers());
    	result.addAll(a.getPilotes());
    	if(a instanceof  AvionLigne) {
    		AvionLigne av = (AvionLigne)a;
    		result.addAll(av.getPersonnels());
    	}
    	return result;
    }
    /**
     * Retourne le nombre de personne a bord
     * @return int
     */
    public int getNbPersonneABord() {
		return this.getOccupants().size();
	}
    
    
    @Override
	public String toString() {
		return "Vol numéro : " + numeroDeVol + "\n" +
				"\t" + avion + "\n" +
				"\tAffreté par la compagnie " + compagnie.getNom() + "\n" +
				"\tEn provenance de : " + aeroportDepart.getNom() + "\n" +
				"\tA destination de : " + aeroportArrivee.getNom() + "\n" +
				"\tAvec " + this.getNbPersonneABord() + " occupants à bord \n";
	}
    
}
