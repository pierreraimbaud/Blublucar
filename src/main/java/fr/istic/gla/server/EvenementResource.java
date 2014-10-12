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

import fr.istic.gla.shared.Evenement;
import fr.istic.gla.shared.EvenementItf;

/*
 * This class is an example of services class
 */

@Path("/evenements")
public class EvenementResource implements EvenementService {

	private List<Evenement> evenements = new ArrayList<Evenement>();

	EntityManager manager;

	public EvenementResource() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		EntityTransaction t = manager.getTransaction();
		t.begin();
		for (int i = 0; i < 20; i++) {
			manager.persist(new Evenement());
		}


	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#list()
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Evenement> list() {
		return manager.createQuery("select e from Evenement").getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#findById(java.lang.String)
	 */
	@GET
	@Path("search/{idEvenement}")
	@Produces({ MediaType.APPLICATION_JSON })
	public EvenementItf findById(@PathParam("idEvenement") String arg0) {
		return manager.find(Evenement.class, Integer.parseInt(arg0));
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#deleteById(java.lang.String)
	 */
	@DELETE
	@Path("delete/{idEvenement}")
	@Produces({ MediaType.APPLICATION_JSON })
	public EvenementItf deleteById(@PathParam("idEvenement") String arg0) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Evenement b = manager.find(Evenement.class, Integer.parseInt(arg0));
		manager.remove(b);
		t.commit();
		return b;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}

}