package com.gestion.aeroport.passager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.gestion.aeroport.aeroport.Compagnie;
import com.gestion.aeroport.aeroport.Program.Destination;

/**
 * Pilote herite de la classe Passager et représente un pilote d'une compagnie
 */
public class Pilote extends Passager {
	
	public static final int PASSAGERMAX_MIN = 200;
	public static final int PASSAGERMAX_MAX = 500;
	public static final int TEMPSPOSE_MIN = 1;
	public static final int TEMPSPOSE_MAX = 5;
	
	
	private Compagnie employeur;
	private int passagersMax;
	private int tempsPause;
	private boolean enVol;
	private int cooldown;
	
	/**
	 * Creer un pilote en specifiant tous les parametres
	 * @param prenom String
	 * @param nom String
	 * @param anniversaire Calendar
	 * @param nationalite String
	 * @param numeroPasseport String
	 * @param voyage Destination
	 * @param volPrive boolean
	 * @param employeur Compagnie
	 * @param passagersMax int
	 * @param tempsPause int
	 */
	public Pilote(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, Destination voyage, boolean volPrive, Compagnie employeur, int passagersMax, int tempsPause) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage, volPrive);
		this.employeur = employeur;
		this.passagersMax = passagersMax;
		this.tempsPause = tempsPause;
		this.cooldown  = 0;
		this.enVol = true;
	}
	
	/**
	 * Creer un pilote en specifiant : employeur et volPrive
	 * @param employeur Compagnie
	 * @param volPrive boolean
	 */
	public Pilote (Compagnie employeur, boolean volPrive) {
		super(null, volPrive);
		this.employeur = employeur;
		this.passagersMax = new Random().nextInt(PASSAGERMAX_MAX - PASSAGERMAX_MIN + 1)  + PASSAGERMAX_MIN;
		this.tempsPause = new Random().nextInt(TEMPSPOSE_MAX - TEMPSPOSE_MIN + 1)  + TEMPSPOSE_MIN;
		this.enVol = true;
	}
	
	
	/**
	 * Permet d'obtenir le temps de repos du pilote
	 * @return int Temps de repos
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * Permet de fixer le temp de repos du pilote
	 * @param cooldown int
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	/**
	 * Permet d'obtenir la compagnie du pilote
	 * @return Compagnie
	 */
	public Compagnie getEmployeur() {
		return employeur;
	}

	/**
	 * Permet d'obtenir le nombre de passager max du pilote
	 * @return int
	 */
	public int getPassagersMax() {
		return passagersMax;
	}

	/**
	 * Permet d'obtenir le temps de pause du pilote
	 * @return int
	 */
	public int getTempsPause() {
		return tempsPause;
	}

	/**
	 * Permet d'afficher un pilote
	 * @return String
	 */
	@Override
	public String toString() {
		return 	super.toString() + 
				"\temployeur       = " + employeur.getNom() + "\n" +
				"\tpassagersMax    = " + passagersMax + "\n" + 
				"\ttempsPause      = " + tempsPause;
	}
	
	/**
	 * Permet de generer n pilotes 
	 * @param n int Le nombre de pilotes a generer
	 * @param c Compagnie Permet de determiner si c'est une compagnie prive ou non
	 * @return ArrayList(Pilote) pilotes
	 */
	public static ArrayList<Pilote> generate(int n, Compagnie c){
		ArrayList<Pilote> pilotes = new ArrayList<Pilote>();
		for (int i = 0 ; i < n ; i++) {
			if(c.getNom().equals(Compagnie.NOM_COMPAGNIE_PRIVEE)) {
				pilotes.add(new Pilote(c, true));
			}
			else {
				pilotes.add(new Pilote(c, false));
			}
		}
		return pilotes;
	}
}
