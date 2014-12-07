package fr.istic.gla.shared;


import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;

public interface Factory extends AutoBeanFactory {
	AutoBean<BookItf> book();
	AutoBean<PersonneItf> personne();
	AutoBean<EvenementItf> evenement();
	AutoBean<VoitureItf> voiture();
}
