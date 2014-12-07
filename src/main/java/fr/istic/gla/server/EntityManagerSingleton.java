package fr.istic.gla.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import fr.istic.gla.shared.Evenement;
import fr.istic.gla.shared.EvenementItf;
import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;
import fr.istic.gla.shared.Voiture;
import fr.istic.gla.shared.VoitureItf;

public class EntityManagerSingleton {

	private static EntityManagerSingleton instance = new EntityManagerSingleton();

	public static EntityManagerSingleton getInstance() {
		return instance;
	}

	private EntityManager manager;

	public EntityManager getManager() {
		return manager;
	}

	public EntityManagerSingleton() {

		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("dev");
		manager = factory.createEntityManager();
		EntityTransaction t = manager.getTransaction();
		t.begin();
		for (int i = 0; i < 5; i++) {
			VoitureItf v= new Voiture();
			manager.persist(v);
		}
		for (int i = 0; i < 5; i++) {
			EvenementItf e = new Evenement();
			manager.persist(e);
		}
		for (int i = 0; i < 5; i++) {
			PersonneItf p = new Personne();
			manager.persist(p);
		}
		t.commit();		
	}	
/*	public <T> T merge(T entity) {
		return manager.merge(entity);
	}*/
}