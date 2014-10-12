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

import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;

@Path("/personnes")
public class PersonneResource implements PersonneService{

	private List<Personne> personnes = new ArrayList<Personne>();

	EntityManager manager;

	public PersonneResource() {
		
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		EntityTransaction t = manager.getTransaction();
		t.begin();
		
		for (int i = 0; i < 20; i++) {
			/*Voiture v = new Voiture();
			Personne p = new Personne("Michel"+i, "Renard"+i, v);
			v.setNb_places_dispos(i);
			v.setProprietaire(p);
			manager.persist(v);*/
			manager.persist(new Personne());
		}
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.PersonneService#list()
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Personne> list() {
		return manager.createQuery("select e from Personne as p").getResultList();
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.PersonneService#findById(java.lang.String)
	 */
	@GET
	@Path("search/{idPersonne}")
	@Produces({ MediaType.APPLICATION_JSON })
	public PersonneItf findById(@PathParam("idPersonne") String arg0) {
		return manager.find(Personne.class, Integer.parseInt(arg0));
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.PersonneService#deleteById(java.lang.String)
	 */
	@DELETE
	@Path("delete/{idPersonne}")
	@Produces({ MediaType.APPLICATION_JSON })
	public PersonneItf deleteById(@PathParam("idPersonne") String arg0) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Personne p = manager.find(Personne.class, Integer.parseInt(arg0));
		manager.remove(p);
		t.commit();
		return p;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
}
