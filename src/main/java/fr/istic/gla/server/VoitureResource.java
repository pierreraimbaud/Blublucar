package fr.istic.gla.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Voiture;
import fr.istic.gla.shared.VoitureItf;

/*
 * This class is an example of services class
 */

@Path("/voitures")
public class VoitureResource implements VoitureService {

	private List<Voiture> voitures = new ArrayList<Voiture>();

	EntityManager manager;

	public VoitureResource() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		EntityTransaction t = manager.getTransaction();
		t.begin();
		for (int i = 0; i < 20; i++) {
			manager.persist(new Voiture());
		}
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#list()
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Voiture> list() {
		return manager.createQuery("select e from Voiture as v").getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#findById(java.lang.String)
	 */
	@GET
	@Path("search/{idVoiture}")
	@Produces({ MediaType.APPLICATION_JSON })
	public VoitureItf findById(@PathParam("idVoiture") String arg0) {
		return manager.find(Voiture.class, Integer.parseInt(arg0));
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#deleteById(java.lang.String)
	 */
	@DELETE
	@Path("delete/{idVoiture}")
	@Produces({ MediaType.APPLICATION_JSON })
	public VoitureItf deleteById(@PathParam("idVoiture") String arg0) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Voiture v = manager.find(Voiture.class, Integer.parseInt(arg0));
		manager.remove(v);
		t.commit();
		return v;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

}
