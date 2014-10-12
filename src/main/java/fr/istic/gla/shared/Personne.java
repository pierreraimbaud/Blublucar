package fr.istic.gla.shared;

//import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Personne implements PersonneItf {

	@Id
	@GeneratedValue
	private Integer idPersonne;

	private String name;
	private String firstname;
	private boolean vientAvecVoiture;
	@OneToOne(mappedBy="proprietaire")
	private Voiture voiture;

	public Personne() {
	}

	public Personne(String name, String firstname, Voiture voiture) {
		this.name = name;
		this.firstname = firstname;
		this.vientAvecVoiture = false;
		this.voiture = voiture;
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

	public boolean isVientAvecVoiture() {
		return vientAvecVoiture;
	}

	public void setVientAvecVoiture(boolean vientAvecVoiture) {
		this.vientAvecVoiture = vientAvecVoiture;
	}

	public Voiture getVoitures() {
		return voiture;
	}

	public void setVoitures(Voiture voiture) {
		this.voiture = voiture;
	}

	/*public void participe (Evenement e, boolean chauffeur, int num_voiture){
		ArrayList <Personne> lp = e.getLp();
		//Parametrage de la notion de chauffeur ou passager
		this.setVientAvecVoiture(chauffeur);
		lp.add(this);
		e.setLp(lp);
	}*/
}
