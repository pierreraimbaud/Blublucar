package fr.istic.gla.server;

import java.util.Collection;

import fr.istic.gla.shared.Voiture;
import fr.istic.gla.shared.VoitureItf;

public interface VoitureService {

	public abstract Collection<Voiture> list();

	public abstract VoitureItf findById(String arg0);

	public abstract VoitureItf deleteById(String arg0);
}
