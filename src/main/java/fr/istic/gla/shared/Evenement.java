package fr.istic.gla.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonCreator;

@Entity
public class Evenement implements EvenementItf{

	@Id
	@GeneratedValue
	private Integer idEvenement;
	private Date date;
	private String lieu;
	private int heure;
	private int etat_places;
	@ManyToMany
	private List<Personne> lp ;

	@JsonCreator
	public Evenement(){
		date = new Date();
		lieu = "Rennes";
		heure = 12;
		lp = new ArrayList<Personne>();
		etat_places =0;
	}

	public Evenement(Date d, String li, int h, ArrayList<Personne> l){
		date = d;
		lieu = li;
		heure = h;
		lp = l;
		etat_places = 0;
	}

	public Integer getIdEvenement() {
		return idEvenement;
	}
	public void setIdEvenement(Integer idEvenement) {
		this.idEvenement = idEvenement;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public int getHeure() {
		return heure;
	}
	public void setHeure(int heure) {
		this.heure = heure;
	}

	public List<Personne> getLp() {
		return lp;
	}

	public void setLp(List<Personne> lp) {
		this.lp = lp;
	}

	public int getEtat_places() {
		return etat_places;
	}

	public void setEtat_places(int etat_places) {
		this.etat_places = etat_places;
	}
	
	/*public void covoiturage(){
		int nb_personnes= this.getLp().size();
		int nb_places = 0;
		for(int i = 0; i < nb_personnes; i++){
			Personne p = this.getLp().get(i);

			if (p.isVientAvecVoiture()){
				//nb_places += p.getList_voitures().get(p.getVoitureCourante()).getNb_places_dispos();
			}
		}
		if (nb_places< nb_personnes){
			System.err.println("Il n'y a pas assez de places : " + nb_personnes +" personnes veulent venir et "
					+ nb_places + " disponibles");
		}
	}*/
}