package fr.istic.gla.server;

import java.util.Collection;

import fr.istic.gla.shared.Book;
import fr.istic.gla.shared.BookItf;

public interface MyService {

	public abstract Collection<Book> list();

	public abstract BookItf findById(String arg0);

	public abstract BookItf deleteById(String arg0);

}