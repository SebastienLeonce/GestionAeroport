package com.gestion.aeroport.passager;

import java.util.Calendar;

import com.gestion.aeroport.aeroport.Program;
import com.gestion.aeroport.aeroport.Program.Destination;

import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import io.codearte.jfairy.producer.person.PersonProperties;


/**
 * Passager est la classe representant un individu dans l'aeroport
 */
public class Passager {
	
private String prenom;
	private String nom;
	private Calendar anniversaire;
	private String nationalite;
	private String numeroPasseport;
	
	private Destination voyage; 
	private String historique = null; 
	private float poidsBagage;
	private int numeroVol = -1;
	private boolean priorite = false;
	private boolean volPrive;
	
	/**
	 * Creer un passager en specifiant tous les parametres
	 * @param prenom String
	 * @param nom String
	 * @param anniversaire Calendar
	 * @param nationalite String
	 * @param numeroPasseport String
	 * @param voyage Destination 
	 * @param volPrive boolean : Permet de definir si le passager vol dans un avion prive
	 */
	public Passager (String prenom, String nom, Calendar anniversaire, String nationalite, String numeroPasseport, Destination voyage, boolean volPrive) {
		this.prenom = prenom;
		this.nom = nom;
		this.anniversaire = anniversaire;
		this.nationalite = nationalite;
		this.numeroPasseport = numeroPasseport;

		this.poidsBagage = 10 + (int)(Math.random() * ((20-10) + 10 ));
		
		this.voyage = voyage;
		this.volPrive = volPrive;
	}
	
	/**
	 * Creer un passager aleatoire juste besoin de specifier son voyage et s'il recherche un vol prive ou non
	 * @param voyage Destination 
	 * @param volPrive boolean : Permet de definir si le passager vol dans un avion prive
	 */
	public Passager (Destination voyage, boolean volPrive) {
		this.nationalite = Program.randomNationalite();
		
		Fairy fairy = Fairy.create();
		Person person = fairy.person(PersonProperties.ageBetween(18, 50));
		
		this.prenom = person.firstName();
		this.nom = person.lastName();
		this.anniversaire = new Calendar.Builder(). setFields(Calendar.YEAR, Integer.parseInt(person.dateOfBirth().toString().substring(0, 4)), Calendar.MONTH, Integer.parseInt(person.dateOfBirth().toString().substring(5, 7)) -1,Calendar.DAY_OF_MONTH, Integer.parseInt(person.dateOfBirth().toString().substring(8, 10))).build();
		
		this.numeroPasseport = person.passportNumber();
		
		this.poidsBagage = 10 + (int)(Math.random() * ((20-10) + 10 ));

		this.voyage = voyage;
		this.volPrive = volPrive;
		
	}
	
	/**
	 *  Creer un passager aleatoire en spécifiant : voyage, volPrive et nationalite
	 * @param voyage Destination 
	 * @param volPrive boolean : Permet de definir si le passager vol dans un avion prive
	 * @param nationalite String
	 */
	public Passager(Destination voyage, boolean volPrive, String nationalite) {
		this(voyage, volPrive);
		this.nationalite = nationalite;
	}
	

	/**
	 * Renvoie le poids des bagages du passager
	 * @return float Le poids des bagages
	 */
	public float getPoidsBagage() {
		return poidsBagage;
	}
	
	/**
	 * Permet de définir si le passager prend un vol prive
	 * @param volPrive boolean
	 */
	public void setVolPrive(boolean volPrive) {
		this.volPrive = volPrive;
	}

	/**
	 * Permet de savoir si le passager prend un vol prive
	 * @return boolean
	 */
	public boolean isVolPrive() {
		return volPrive;
	}

	/**
	 * Permet d'obtenir le voyage du passager
	 * @return Le voyage du passager
	 */
	public Destination getVoyage() {
		return voyage;
	}

	/**
	 * Permet de modifier le voyage du passager
	 * @param voyage Destination
	 */
	public void setVoyage(Destination voyage) {
		this.voyage = voyage;
	}

	/**
	 * Permet d'obtenir l'historique des voyages du passager
	 * @return L'historique des voyages du passager
	 */
	public String getHistorique() {
		return historique;
	}

	/**
	 * Permet de mettre a jour l'historique des voyages du passager
	 * @param historique String
	 */
	public void setHistorique(String historique) {
		this.historique += historique;
	}

	/**
	 * Permet d'obtenir le numero de vol du passager
	 * @return int Le numero de vol du passager
	 */
	public int getNumeroVol() {
		return numeroVol;
	}

	/**
	 * Permet de mettre a  jour le numero de vol du passager
	 * @param numeroVol int
	 */
	public void setNumeroVol(int numeroVol) {
		this.numeroVol = numeroVol;
	}

	/**
	 * Obtenir le prenom du passager
	 * @return String Le prenom du passager
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * Obtenir le nom du passager
	 * @return String Le nom du passager
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Obtenir la date d'anniversaire du Passager
	 * @return Calendar La date d'anniversaire du Passager
	 */
	public Calendar getAnniversaire() {
		return anniversaire;
	}

	/**
	 * Obtenir la nationalite du passager
	 * @return String La nationalite du passager
	 */
	public String getNationalite() {
		return nationalite;
	}

	/**
	 * Obtenir le numero de passeport du passager
	 * @return String Le numero de passeport du passager
	 */
	public String getNumeroPasseport() {
		return numeroPasseport;
	}

	/**
	 * Savoir si le passager est prioritaire pour prendre un vol
	 * @return Boolean
	 */
	public boolean isPriorite() {
		return priorite;
	}

	/**
	 * Permet de dire si le passager est prioritaire ou non
	 * @param priorite Boolean
	 */
	public void setPriorite(boolean priorite) {
		this.priorite = priorite;
	}

	/**
	 * Permet d'afficher tous les donnees d'un passager en console
	 * @return String Les donnees du passagers
	 */
	@Override
	public String toString() {
		return 	this.getClass().getSimpleName() + " : \n" +
				"\tprenom_nom      = " + prenom + "_" + nom + "\n" +
				"\tanniversaire    = " + anniversaire.get(Calendar.YEAR) + "/" + anniversaire.get(Calendar.MONTH) + "/" +  anniversaire.get(Calendar.DAY_OF_MONTH) + "\n" +
				"\tnationalite     = " + nationalite + "\n" ;/*+
				"\tnumeroPasseport = " + numeroPasseport +  "\n" +
				"\tvoyage          = " + voyage + "\n" +
				"\thistorique      = " + historique + "\n" +
				"\tnumeroVol       = " + numeroVol + "\n" +
				"\tpriorite        = " + priorite + "\n\n";*/
	}
}
