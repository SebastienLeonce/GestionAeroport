package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

import com.gestion.aeroport.avion.Avion;
import com.gestion.aeroport.passager.Passager;

public class Vol {

    private Avion avion;
    private ArrayList<Passager> occupants;
    private Aeroport aeroportArrivee;
    private Aeroport aeroportDepart;

    public Vol(Avion avion, Aeroport arrivee, Aeroport depart){
        this.occupants = new ArrayList<Passager>();
        this.aeroportArrivee = arrivee;
        this.aeroportDepart = depart;
        this.avion = avion;
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
}
