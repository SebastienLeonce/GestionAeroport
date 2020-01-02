package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.avion.AvionLigne;
import com.gestion.aeroport.passager.Passager;

public class Vol {

    private static int compteur = 1;
    private int numeroDeVol;
	private Avion avion;
    private Aeroport aeroportArrivee;
    private Aeroport aeroportDepart;

    public Vol(Avion avion, Aeroport arrivee, Aeroport depart){
        this.aeroportArrivee = arrivee;
        this.aeroportDepart = depart;
        this.avion = avion;
        this.numeroDeVol = compteur;
        compteur++;
    }

    public Avion getAvion(){
        return this.avion;
    }
    public int getNbPersonneABord() {
		int nb = this.avion.getNbPilotes() + this.avion.getPassagers().size();
		if(this.avion.getClass() == AvionLigne.class) {
			AvionLigne a = (AvionLigne)this.avion;
			nb += a.getNbPersonnels();
		}
		return nb;
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
				"\tAvec " + this.getNbPersonneABord() + " occupants à bord \n";
	}
    
}
