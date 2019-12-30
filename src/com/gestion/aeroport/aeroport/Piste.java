package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

public class Piste {

    private ArrayList<Vol> fileDAttente;
    private int espacement;
    private boolean enMarche;
    private int id;
    private  static  int compteur =  0 ;
    

    Piste(final int espacement){
        this.fileDAttente = new ArrayList<Vol>();
        this.espacement = espacement;
        this.enMarche = true;
        this.id = compteur;
        
        compteur++;
    }


    public ArrayList<Vol> getFileDAttente(){
        return this.fileDAttente;
    }
    public int getEspacement(){
        return this.espacement;
    }
    
	public boolean getEnMarche() {
		return enMarche;
	}


	public void setEnMarche(boolean enMarche) {
		this.enMarche = enMarche;
	}

	public int getId() {
		return id;
	}


	@Override
	public String toString() {
		return "\n" + 
					"\tid=" + id + "\n" + 
					"\tfileDAttente = " + fileDAttente + "\n" + 
					"\tespacement   = " + espacement   + "\n" + 
					"\tenMarche     = " + enMarche     + "\n";
	}
    
}
