package fr.istic.gla.shared;

import java.util.Date;

public interface EvenementItf {

	public Integer getIdEvenement();
	
	public void setIdEvenement(Integer idEvenement);
	
	public Date getDate();
	
	public void setDate(Date date);
	
	public String getLieu();
	
	public void setLieu(String lieu);
	
	public int getHeure();
	
	public void setHeure(int heure);

}

