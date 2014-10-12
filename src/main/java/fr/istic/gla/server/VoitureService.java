package fr.istic.gla.server;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Voiture;
import fr.istic.gla.shared.VoitureItf;

public interface VoitureService {

	public abstract Collection<Voiture> list();

	public abstract VoitureItf findById(String arg0);

	public abstract VoitureItf deleteById(String arg0);

}
