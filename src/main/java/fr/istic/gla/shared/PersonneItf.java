package fr.istic.gla.shared;

public interface PersonneItf {

	public Integer getIdPersonne();

	public void setIdPersonne(Integer idPersonne);

	public String getName();

	public void setName(String name) ;

	public String getFirstname();

	public void setFirstname(String firstname);

	public int getNb_places_dispos();

	public void setNb_places_dispos(int nb_places_dispos);

	public boolean isChauff();

	public void setChauff(boolean chauff);
}
