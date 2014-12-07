package fr.istic.gla.client;

import com.google.gwt.core.client.GWT;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import fr.istic.gla.shared.VoitureFactory;
import fr.istic.gla.shared.VoitureItf;


public class VoitureJsonConverter {

	private VoitureJsonConverter() {
	}

	private static VoitureJsonConverter instance = new VoitureJsonConverter();


	// Instantiate the factory
	VoitureFactory factory = GWT.create(VoitureFactory.class);
	// In non-GWT code, use AutoBeanFactorySource.create(MyFactory.class);

	public VoitureItf makeVoiture() {
		// Construct the AutoBean
		AutoBean<VoitureItf> voiture = factory.voiture();

		// Return the Book interface shim
		return voiture.as();
	}

	String serializeToJson(VoitureItf voiture) {
		// Retrieve the AutoBean controller
		AutoBean<VoitureItf> bean = AutoBeanUtils.getAutoBean(voiture);

		return AutoBeanCodex.encode(bean).getPayload();
	}

	VoitureItf deserializeFromJson(String json) {
		AutoBean<VoitureItf> bean = AutoBeanCodex.decode(factory,VoitureItf.class, json);
		return bean.as();
	}

	public static VoitureJsonConverter getInstance() {
		return instance;
	}
}