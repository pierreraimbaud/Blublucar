package fr.istic.gla.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
public class Evenement implements EvenementItf, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2861342899100812585L;
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

	@JsonCreator
	public Evenement(@JsonProperty("date") Date d, @JsonProperty("lieu") String li, @JsonProperty("heure") int h){
		date = d;
		lieu = li;
		heure = h;
		lp = new ArrayList<Personne>();;
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
}