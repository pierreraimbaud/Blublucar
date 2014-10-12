package fr.istic.gla.server;

import java.util.Collection;
import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


public interface PersonneService {

	public abstract Collection<Personne> list();

	public abstract PersonneItf findById(String arg0);

	public abstract PersonneItf deleteById(String arg0);

}
