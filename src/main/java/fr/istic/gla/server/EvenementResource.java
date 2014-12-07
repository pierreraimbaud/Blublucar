package fr.istic.gla.server;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Evenement;
import fr.istic.gla.shared.EvenementItf;
import fr.istic.gla.shared.Personne;

/*
 * This class is an example of services class
 */

@Path("/evenements")
public class EvenementResource implements EvenementService {

	//private List<Evenement> evenements = new ArrayList<Evenement>();

	EntityManager manager;

	public EvenementResource() {
		manager = EntityManagerSingleton.getInstance().getManager();	
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#list()
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Evenement> list() {
		return manager.createQuery("select e from Evenement as e").getResultList();
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

	@POST
	@Path("/addPersonneToEvenement/{idPersonne}/{idEvenement}")
	@Produces({ MediaType.APPLICATION_JSON })
	public EvenementItf addPersonneToEvenement(@PathParam("idPersonne") String arg0, @PathParam("idEvenement") String arg1) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Personne p = manager.find(Personne.class, Integer.parseInt(arg0));
		Evenement b = manager.find(Evenement.class, Integer.parseInt(arg1));
		b.getLp().add(p);
		b.setEtat_places(b.getEtat_places()+p.getNb_places_dispos()-1);
		t.commit();
		return b;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
}