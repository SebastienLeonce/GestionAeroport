package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

public class Piste {

    private ArrayList<Vol> fileDAttente;
    private int espacement;

    Piste(final int espacement){
        this.fileDAttente = new ArrayList<Vol>();
        this.espacement = espacement;
    }


    public ArrayList<Vol> getFileDAttente(){
        return this.fileDAttente;
    }
    public int getEspacement(){
        return this.espacement;
    }
    


	@Override
	public String toString() {
		return "Piste \n fileDAttente=" + fileDAttente + ", espacement=" + espacement +"\n";
	}
    
}
