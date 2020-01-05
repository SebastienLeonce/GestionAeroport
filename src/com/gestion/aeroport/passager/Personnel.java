package com.gestion.aeroport.passager;

import java.util.ArrayList;
import java.util.Calendar;

import com.gestion.aeroport.aeroport.Compagnie;
import com.gestion.aeroport.aeroport.Program.Destination;

/**
 * Personnel herite de la classe Passager et représente un personnel d'une compagnie
 */
public class Personnel extends Passager {
	
	private Compagnie compagnie;

	/**
	 * Creer un personnel en specifiant tous les parametres
	 * @param prenom String
	 * @param nom String
	 * @param anniversaire Calendar
	 * @param nationalite String
	 * @param numeroPasseport String
	 * @param voyage Destination
	 * @param volPrive boolean
	 * @param compagnie Compagnie
	 */
	public Personnel(String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, Destination voyage, boolean volPrive, Compagnie compagnie) {
		super(prenom, nom, anniversaire, nationalite, numeroPasseport, voyage, volPrive);
		this.compagnie = compagnie;
	}
	
	/**
	 * Creer un personnel en specifiant : compagnie et volPrive
	 * @param compagnie Compagnie
	 * @param volPrive boolean
	 */
	public Personnel (Compagnie compagnie, boolean volPrive) {
		super(null, volPrive);
		this.compagnie = compagnie;
	}

	/**
	 * Renvoie la compagnie du personnel
	 * @return Compagnie
	 */
	public Compagnie getCompagnie() {
		return compagnie;
	}
	
	/**
	 * Permet de generer n personnel 
	 * @param n int Le nombre de personnels a generer
	 * @param c Compagnie Permet de determiner si c'est une compagnie prive ou non
	 * @return ArrayList(Personnel) personnels
	 */
	public static ArrayList<Personnel> generate(int n, Compagnie c){
		ArrayList<Personnel> personnels = new ArrayList<Personnel>();
		for (int i = 0 ; i < n ; i++) {
			if(c.getNom().equals(Compagnie.NOM_COMPAGNIE_PRIVEE)) {
				personnels.add(new Personnel(c, true));
			}
			else {
				personnels.add(new Personnel(c, false));
			}
		}
		return personnels;
	}

	/**
	 * Affiche un membre du personnel
	 * @return String
	 */
	@Override
	public String toString() {
		return 	super.toString(); /*+ 
				"\tcompagnie       = " + compagnie;*/
	}
}
