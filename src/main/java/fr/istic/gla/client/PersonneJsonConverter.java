package fr.istic.gla.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;

public class PersonneJsonConverter {

	private PersonneJsonConverter() {
	}
	
	private static PersonneJsonConverter instance = new PersonneJsonConverter();
	
	
	  // Instantiate the factory
	  fr.istic.gla.shared.PersonneFactory factory = GWT.create(fr.istic.gla.shared.PersonneFactory.class);
	  // In non-GWT code, use AutoBeanFactorySource.create(PersonneFactory.class);

	  public PersonneItf makePersonne() {
	    // Construct the AutoBean
	    AutoBean<PersonneItf> personne = factory.personne();

	    // Return the Personne interface shim
	    return personne.as();
	  }

	  String serializeToJson(Personne personne) {
	    // Retrieve the AutoBean controller
	    AutoBean<PersonneItf> bean = AutoBeanUtils.getAutoBean(personne);

	    return AutoBeanCodex.encode(bean).getPayload();
	  }

	  PersonneItf deserializeFromJson(String json) {
	    AutoBean<PersonneItf> bean = AutoBeanCodex.decode(factory, PersonneItf.class, json);
	    return bean.as();
	  }

	public static PersonneJsonConverter getInstance() {
		return instance;
	}
}
