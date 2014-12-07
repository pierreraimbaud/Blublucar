package fr.istic.gla.client;

import java.util.ArrayList;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import fr.istic.gla.shared.EvenementItf;
import fr.istic.gla.shared.Personne;
import fr.istic.gla.shared.PersonneItf;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class glagerseygwt implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	static AbsolutePanel header  =new AbsolutePanel();
	static AbsolutePanel p  =new AbsolutePanel();
	static AbsolutePanel p2  =new AbsolutePanel();
	static AbsolutePanel p3  =new AbsolutePanel();

	static ArrayList<EvenementItf> le = new ArrayList<EvenementItf>();
	static int decal = 0;

	public void onModuleLoad() {

		header.setWidth("1240px");
		header.setHeight("30px");
		header.getElement().getStyle().setBackgroundColor("#DFA009");
		RootPanel.get().add(header);

		p.getElement().getStyle().setBackgroundColor("#FFFF00");
		p.setWidth("400px");
		p.setHeight("600px");
		RootPanel.get().add(p,0,30);

		p2.getElement().getStyle().setBackgroundColor("#FF0000");
		p2.setWidth("400px");
		p2.setHeight("600px");
		RootPanel.get().add(p2, 420,30);

		p3.getElement().getStyle().setBackgroundColor("#0D8412");
		p3.setWidth("400px");
		p3.setHeight("600px");
		RootPanel.get().add(p3,840,30);

		Label l = new Label();
		l.setText("Blablucar");
		header.add(l,0,0);


		Button b = new Button();
		b.setText("Voir liste");
		p.add(b, 200,200);
		RootPanel.get().add(p);

		Label prenom = new Label();
		prenom.setText("Prenom");

		Label nom = new Label();
		nom.setText("Nom");

		Label voit = new Label();
		voit.setText("Je possède une voiture");

		final Label nbpl = new Label();
		nbpl.setText("Nombre de places :");

		final TextBox prenomVal = new TextBox();
		final TextBox nomVal = new TextBox();
		final CheckBox voitVal = new CheckBox();
		final TextBox nbPlaVal = new TextBox();
		nbPlaVal.setValue("0");
		nbPlaVal.setVisible(false);
		nbpl.setVisible(false);
		p.add(nbpl,10,525);
		p.add(nbPlaVal,10,550);

		voitVal.addClickHandler(new ClickHandler(){

			public void onClick(ClickEvent event) {
				if (voitVal.getValue() == true){
					nbpl.setVisible(true);
					nbPlaVal.setVisible(true);

				}
				else{
					nbPlaVal.setVisible(false);
					nbpl.setVisible(false);
				}
			}

		});

		p.add(prenom,10,400);
		p.add(prenomVal,10,425);

		p.add(nom,10,450);
		p.add(nomVal,10,475);

		p.add(voit,10,500);
		p.add(voitVal,150,500);

		Button aj = new Button();
		aj.setText("Valider");
		p.add(aj,10,600);

		aj.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				String fir = prenomVal.getText(); 
				String nam =  nomVal.getText();
				int nb = Integer.parseInt(nbPlaVal.getText());
				
				prenomVal.setText("");
				nomVal.setText("");
				voitVal.setValue(false);
				nbPlaVal.setValue("0");
				nbPlaVal.setVisible(false);

				//PersonneItf p = 
						//new Personne(prenomVal.getText(), nomVal.getText(),Integer.parseInt(nbPlaVal.getText()));

				String serializedPersonne = 
						"{\"name\": \""+nam+"\",\"firstname\": \""+fir+"\",\"nb_places_dispos\": \""+nb+"\"}";
					//	"{\"name\": \"bougri\",\"firstname\": \"rat\",\"nb_places_dispos\": \"2\"}";

				RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
						GWT.getHostPageBaseURL() + "rest/personnes/add");

				builder.setHeader("Content-Type", "application/json");
				builder.setRequestData(serializedPersonne);
				builder.setCallback(new RequestCallback() {
					public void onResponseReceived(Request request, Response response) {  
						if (200 == response.getStatusCode()) { 
							Window.alert("Vous avez bien réussi à vous ajouter. Votre id (à retenir) est le "+response.getText());

						}  
					}  
					public void onError(Request request, Throwable exception) {  
					}  
				}); 
				try {
					builder.send();
				} catch (RequestException e) {
					e.printStackTrace();
				}
			}
		});























		b.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {



				RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT
						.getHostPageBaseURL() + "rest/evenements/search/" + 4);
				rb.setCallback(new RequestCallback() {

					public void onError(Request request, Throwable exception) {
						Window.alert(exception.getMessage());
					}

					public void onResponseReceived(Request request,
							Response response) {
						if (200 == response.getStatusCode()) {

							final EvenementItf v = EvenementJsonConverter.getInstance()
									.deserializeFromJson(response.getText());
							Label l = new Label();
							l.setText("Liste"+ v.getEtat_places());
							p.add(l,100,500);

						}
					}

				});	
				try {
					rb.send();
				} catch (RequestException e) {
					e.printStackTrace();
				}
			}
		});






		for (int i =0; i<5; i++){
			RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT
					.getHostPageBaseURL() + "rest/evenements/search/" + i);
			rb.setCallback(new RequestCallback() {

				public void onError(Request request, Throwable exception) {
					Window.alert(exception.getMessage());
				}

				public void onResponseReceived(Request request,
						Response response) {
					if (200 == response.getStatusCode()) {

						final EvenementItf v = EvenementJsonConverter.getInstance()
								.deserializeFromJson(response.getText());
						Label l = new Label();
						l.setText("Evenement :" + v.getIdEvenement());
						p.add(l,35,80+decal);


						Button b = new Button();
						b.setText("ok");
						p.add(b, 130,77+decal);
						RootPanel.get().add(p);

						decal += 50;

						b.addClickHandler(new ClickHandler() {

							public void onClick(ClickEvent arg0) {
								p2.clear();
								p3.clear();
								Label l = new Label();
								l.setText("wesh papi"+v.getIdEvenement()+ " le "
										+ v.getDate() + " à "+ v.getLieu() + " Heure :"+ v.getHeure());

								Button aj = new Button();
								aj.setText("Je m'ajoute");
								p2.add(aj);
								p2.add(l,0,50);
								aj.addClickHandler(new ClickHandler() {

									public void onClick(ClickEvent event) {
										p3.clear();
										Label prenom = new Label();
										prenom.setText("Prenom");

										Label nom = new Label();
										nom.setText("Nom");

										Label voit = new Label();
										voit.setText("Je viens avec ma voiture");

										final Label nbpl = new Label();
										nbpl.setText("Nombre de places :");

										final TextBox p = new TextBox();
										final TextBox n = new TextBox();
										final CheckBox c = new CheckBox();
										final TextBox nb = new TextBox();

										c.addClickHandler(new ClickHandler(){

											public void onClick(ClickEvent event) {
												if (c.getValue() == true){
													p3.add(nbpl);
													p3.add(nb);
												}
												else{
													p3.remove(nbpl);
													p3.remove(nb);
												}
											}

										});

										p3.add(prenom);
										p3.add(p);

										p3.add(nom);
										p3.add(n);

										p3.add(voit);
										p3.add(c);

										Button aj = new Button();
										aj.setText("Valider");
										p3.add(aj,0,200);
										aj.addClickHandler(new ClickHandler() {

											public void onClick(ClickEvent event) {
												//	String data = "hola";
												//data += p.getText()+ " ";
												//data += n.getText()+ " ";
												//data += nb.getText()+ " places ";
												//data += c.getValue();
												//Personne p = new Personne();
												//p.setName(n.getText());
												PersonneItf personne = new Personne();
												String name = "Sylvain";
												String firstname = "Le Core";
												personne.setName(name);
												personne.setFirstname(firstname);

												String test = PersonneJsonConverter.getInstance().serializeToJson(personne);
												String serializedPersonne = 
														"{\"name\": \"bougri\",\"firstname\": \"rat\",\"nb_places_dispos\": \"2\"}";

												RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
														GWT.getHostPageBaseURL() + "rest/personnes/add");

												builder.setHeader("Content-Type", "application/json");
												builder.setRequestData(serializedPersonne);
												builder.setCallback(new RequestCallback() {
													public void onResponseReceived(Request request, Response response) {  
														if (200 == response.getStatusCode()) { 
														}  
													}  
													public void onError(Request request, Throwable exception) {  
													}  
												}); 
												try {
													builder.send();
												} catch (RequestException e) {
													e.printStackTrace();
												}
												Window.alert(test);
												p3.clear();
											}
										});

										Button test = new Button();
										test.setText("Test");
										p3.add(test,0,250);
										test.addClickHandler(new ClickHandler() {

											public void onClick(ClickEvent event) {
												RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
														GWT.getHostPageBaseURL() + "rest/evenements/addPersonneToEvenement/1/4");

												builder.setCallback(new RequestCallback() {
													public void onResponseReceived(Request request, Response response) {  
														if (200 == response.getStatusCode()) { 
														}  
													}  
													public void onError(Request request, Throwable exception) {  
													}  
												}); 
												try {
													builder.send();
												} catch (RequestException e) {
													e.printStackTrace();
												}
											}
										});
									}
								});
							}
						});
					}
				}
			});
			try {
				rb.send();
			} catch (RequestException e) {
				e.printStackTrace();
			}
		}
	}
}