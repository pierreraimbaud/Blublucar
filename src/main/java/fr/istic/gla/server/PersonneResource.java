package fr.istic.gla.server;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;

@Path("/personnes")
public class PersonneResource implements PersonneService{

	//private List<Personne> personnes = new ArrayList<Personne>();

	EntityManager manager;

	public PersonneResource() {

		manager = EntityManagerSingleton.getInstance().getManager();	

	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.PersonneService#list()
	 */
	@SuppressWarnings("unchecked")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Personne> list() {
		return manager.createQuery("select p from Personne as p").getResultList();
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

	@POST
	@Path("/add/")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces(MediaType.TEXT_PLAIN)
	//@Produces({ MediaType.APPLICATION_JSON })
	//public PersonneItf add(Personne p) {
	public String add(Personne p) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		manager.persist(p);
		//EntityManagerSingleton.getInstance().merge(p);
		t.commit();
		int id = p.getIdPersonne();
		String ret = id+ "";
		return ret;
	}

	/*@POST
	@Path("/addVoiture/{idVoiture}/{idPersonne}")
	@Produces({ MediaType.APPLICATION_JSON })
	public PersonneItf addPersonneVoiture(@PathParam("idVoiture") String arg0, @PathParam("idPersonne") String arg1) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Voiture v = manager.find(Voiture.class, Integer.parseInt(arg0));
		Personne p = manager.find(Personne.class, Integer.parseInt(arg1));
		p.setVoiture(v);
		t.commit();
		return p;
	}*/

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey";
	}
}