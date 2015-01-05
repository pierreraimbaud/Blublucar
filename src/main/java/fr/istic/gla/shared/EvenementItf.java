package fr.istic.gla.shared;

import java.util.Date;
import java.util.List;

public interface EvenementItf{

	public Integer getIdEvenement();

	public void setIdEvenement(Integer idEvenement);

	public Date getDate();

	public void setDate(Date date);

	public String getLieu();

	public void setLieu(String lieu);

	public int getHeure();

	public void setHeure(int heure);

	public List<Personne> getLp();

	public void setLp(List<Personne> lp);

	public int getEtat_places();

	public void setEtat_places(int etat_places);
}