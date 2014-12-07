package fr.istic.gla.shared;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

@Entity
public class Personne implements PersonneItf, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8925177444154877282L;


	@Id
	@GeneratedValue
	private Integer idPersonne;
	private String name;
	private String firstname;
	private int nb_places_dispos;
	private boolean chauff;

	/*@OneToOne
	private Voiture voiture;*/

	@JsonCreator
	public Personne() {
		this.name = "Michel";
		this.firstname = "Martin";
		this.nb_places_dispos = 0;
		this.chauff = true;
	}

	@JsonCreator 
	public Personne(@JsonProperty("name") String name, @JsonProperty("firstname") String firstname, @JsonProperty("nb_places_dispos") int nb_places_dispos){
		this.name = name;
		this.firstname = firstname;
		this.nb_places_dispos = nb_places_dispos;
		this.chauff = true;
	}

	public Integer getIdPersonne() {
		return idPersonne;
	}
	public void setIdPersonne(Integer idPersonne) {
		this.idPersonne = idPersonne;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getNb_places_dispos() {
		return nb_places_dispos;
	}

	public void setNb_places_dispos(int nb_places_dispos) {
		this.nb_places_dispos = nb_places_dispos;
	}

	public boolean isChauff() {
		return chauff;
	}

	public void setChauff(boolean chauff) {
		this.chauff = chauff;
	}

	/*public void participe (Evenement e, boolean chauffeur, int num_voiture){
		ArrayList <Personne> lp = e.getLp();
		//Parametrage de la notion de chauffeur ou passager
		this.setVientAvecVoiture(chauffeur);
		lp.add(this);
		e.setLp(lp);
	}*/
}
