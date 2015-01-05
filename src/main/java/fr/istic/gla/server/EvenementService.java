package fr.istic.gla.server;

import java.util.Collection;

import fr.istic.gla.shared.Evenement;
import fr.istic.gla.shared.EvenementItf;

public interface EvenementService {

	public abstract Collection<Evenement> list();

	public abstract EvenementItf findById(String arg0);

	public abstract EvenementItf deleteById(String arg0);

	public abstract EvenementItf addEvenement(Evenement e);

	public abstract EvenementItf addPersonneToEvenement(String arg0, String arg1);
}