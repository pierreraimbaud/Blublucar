package fr.istic.gla.shared;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.codehaus.jackson.annotate.JsonCreator;

@Entity
public class Voiture implements VoitureItf, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2673606268605752636L;
	@Id
	@GeneratedValue
	private Integer idVoiture;
	private int nb_places_dispos;

	@JsonCreator
	public Voiture(){
		nb_places_dispos = 3;
	}

	public Voiture(int nb, Personne prop){
		nb_places_dispos = nb;
	}

	public Integer getIdVoiture() {
		return idVoiture;
	}

	public void setIdVoiture(Integer idVoiture) {
		this.idVoiture = idVoiture;
	}

	public int getNb_places_dispos() {
		return nb_places_dispos;
	}

	public void setNb_places_dispos(int nb_places_dispos) {
		this.nb_places_dispos = nb_places_dispos;
	}
}