package fr.istic.gla.server;

import java.util.Collection;

import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;


public interface PersonneService {

	public abstract Collection<Personne> list();

	public abstract PersonneItf findById(String arg0);

	public abstract PersonneItf deleteById(String arg0);

	public abstract String add(Personne p);

	//public abstract PersonneItf add(Personne p);
	
	//public abstract PersonneItf addPersonneVoiture (String arg0, String arg1);

}
