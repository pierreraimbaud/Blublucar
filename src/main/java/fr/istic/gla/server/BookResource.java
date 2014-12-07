package fr.istic.gla.server;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.istic.gla.shared.Book;
import fr.istic.gla.shared.BookItf;

/*
 * This class is an example of services class
 */

@Path("/books")
public class BookResource implements MyService {

	//private List<Book> books = new ArrayList<Book>();

	EntityManager manager;

	public BookResource() {
		manager = EntityManagerSingleton.getInstance().getManager();	

	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#list()
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Book> list() {
		@SuppressWarnings("unchecked")
		List<Book> res =  manager.createQuery("select b from Book as b").getResultList();
		System.err.println(res.size());
		return res;
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#findById(java.lang.String)
	 */
	@GET
	@Path("search/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public BookItf findById(@PathParam("id") String arg0) {
		return manager.find(Book.class, Integer.parseInt(arg0));
	}

	/* (non-Javadoc)
	 * @see fr.istic.gla.server.MyService#deleteById(java.lang.String)
	 */
	@DELETE
	@Path("delete/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public BookItf deleteById(@PathParam("id") String arg0) {
		EntityTransaction t = manager.getTransaction();
		t.begin();
		Book b = manager.find(Book.class, Integer.parseInt(arg0));
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