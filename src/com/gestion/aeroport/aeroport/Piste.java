package com.gestion.aeroport.aeroport;

import java.util.ArrayList;

public class Piste {

    private ArrayList<Vol> fileDAttente;
    private int espacement;
    private boolean enMarche;
    private int id;
    private  static int compteur =  0 ;
    

    public Piste(){
        this.fileDAttente = new ArrayList<Vol>();
        this.enMarche = true;
        this.id = compteur;
        this.espacement = 0;
        compteur++;
    }


	public ArrayList<Vol> getFileDAttente(){
        return this.fileDAttente;
    }
    
    public void setFileDAttente(ArrayList<Vol> fileDAttente) {
		this.fileDAttente = fileDAttente;
	}


	public int getEspacement(){
        return this.espacement;
    }
    public void setEspacement(int espacement) {
    	this.espacement = espacement;
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
					"\tNombre d'avion en file d'attente = " + fileDAttente.size() + "\n" + 
					"\tespacement   = " + espacement   + "\n" + 
					"\tenMarche     = " + enMarche     + "\n";
	}
    
}
