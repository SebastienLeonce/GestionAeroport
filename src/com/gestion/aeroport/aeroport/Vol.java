package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Passager;

public class Vol {

    private static int compteur = 1;
    private int numeroDeVol;
	private Avion avion;
    private ArrayList<Passager> occupants;
    private Aeroport aeroportArrivee;
    private Aeroport aeroportDepart;

    public Vol(Avion avion, Aeroport arrivee, Aeroport depart){
        this.occupants = new ArrayList<Passager>();
        this.aeroportArrivee = arrivee;
        this.aeroportDepart = depart;
        this.avion = avion;
        this.numeroDeVol = compteur;
        compteur++;
    }

    public Avion getAvion(){
        return this.avion;
    }
    public ArrayList<Passager> getOccupants(){
        return this.occupants;
    }
    public Aeroport getDepart(){
        return this.aeroportDepart;
    }
    public Aeroport getArrivee(){
        return this.aeroportArrivee;
    }
    
    @Override
	public String toString() {
		return "Vol numéro : " + numeroDeVol + "\n" +
				"\t" + avion + "\n" +
				"\tEn provenance de : " + aeroportDepart.getNom() + "\n" +
				"\tA destination de : " + aeroportArrivee.getNom() + "\n" +
				"\tAvec " + occupants.size() + " occupants à bord \n";
	}
    
}
