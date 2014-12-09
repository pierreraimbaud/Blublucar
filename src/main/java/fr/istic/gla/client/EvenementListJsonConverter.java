package fr.istic.gla.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;

import fr.istic.gla.shared.EvenementListFactory;
import fr.istic.gla.shared.EvenementListItf;


public class EvenementListJsonConverter {

	private EvenementListJsonConverter() {
	}

	private static EvenementListJsonConverter instance = new EvenementListJsonConverter();

	// Instantiate the factory
	EvenementListFactory factory = GWT.create(EvenementListFactory.class);
	// In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

	String serializeToJson(EvenementListItf rideList) {
		// Retrieve the AutoBean controller
		AutoBean<EvenementListItf> bean = AutoBeanUtils.getAutoBean(rideList);

		return AutoBeanCodex.encode(bean).getPayload();
	}

	EvenementListItf deserializeFromJson(String json) {
		AutoBean<EvenementListItf> bean = AutoBeanCodex.decode(factory, EvenementListItf.class, "{\"evenements\": " + json+ "}");
		return bean.as();
	}

	public static EvenementListJsonConverter getInstance() {
		return instance;
	}
}