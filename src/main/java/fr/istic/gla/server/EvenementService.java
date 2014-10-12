package fr.istic.gla.server;

import java.util.Collection;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Evenement;
import fr.istic.gla.shared.EvenementItf;


public interface EvenementService {

	public abstract Collection<Evenement> list();

	public abstract EvenementItf findById(String arg0);

	public abstract EvenementItf deleteById(String arg0);

}