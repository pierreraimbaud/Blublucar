package fr.istic.gla.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import fr.istic.gla.shared.EvenementFactory;
import fr.istic.gla.shared.EvenementItf;


public class EvenementJsonConverter {

	private EvenementJsonConverter() {
	}
	
	private static EvenementJsonConverter instance = new EvenementJsonConverter();
	
	
	  // Instantiate the factory
	  EvenementFactory factory = GWT.create(EvenementFactory.class);
	  // In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

	  public EvenementItf makeEvenement() {
	    // Construct the AutoBean
	    AutoBean<EvenementItf> evenement = factory.evenement();

	    // Return the Book interface shim
	    return evenement.as();
	  }

	  String serializeToJson(EvenementItf evenement) {
	    // Retrieve the AutoBean controller
	    AutoBean<EvenementItf> bean = AutoBeanUtils.getAutoBean(evenement);

	    return AutoBeanCodex.encode(bean).getPayload();
	  }

	  EvenementItf deserializeFromJson(String json) {
	    AutoBean<EvenementItf> bean = AutoBeanCodex.decode(factory, EvenementItf.class, json);
	    return bean.as();
	  }

	public static EvenementJsonConverter getInstance() {
		return instance;
	}
}
