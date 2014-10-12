package fr.istic.gla.shared;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Voiture implements VoitureItf {

	@Id
	@GeneratedValue
	private Integer idVoiture;

	private int nb_places_dispos;

	@OneToOne
	Personne proprietaire;

	public Voiture(){
	}

	public Voiture(int nb, Personne prop){
		nb_places_dispos = nb;
		proprietaire = prop;
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

	public Personne getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Personne proprietaire) {
		this.proprietaire = proprietaire;
	}
}

