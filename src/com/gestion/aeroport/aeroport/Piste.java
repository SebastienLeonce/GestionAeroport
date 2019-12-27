package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

public class Piste {

    
	public enum TypePiste{
        DECOLLAGE, ATTERISSAGE
    }

    private ArrayList<Vol> fileDAttente;
    private int espacement;
    private TypePiste type;

    Piste(final int espacement, TypePiste type){
        this.fileDAttente = new ArrayList<Vol>();
        this.espacement = espacement;
        this.type = type;
    }


    public ArrayList<Vol> getFileDAttente(){
        return this.fileDAttente;
    }
    public int getEspacement(){
        return this.espacement;
    }
    public TypePiste getTypePiste(){
        return this.type;
    }

    
    @Override
	public String toString() {
		return "Piste [fileDAttente=" + fileDAttente + ", espacement=" + espacement + ", type=" + type + "]";
	}
    
}
