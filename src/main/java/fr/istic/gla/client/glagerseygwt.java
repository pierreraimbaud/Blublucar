package fr.istic.gla.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import com.google.gwt.user.datepicker.client.DateBox;

import fr.istic.gla.shared.EvenementItf;
import fr.istic.gla.shared.EvenementListItf;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class glagerseygwt implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	static AbsolutePanel header  =new AbsolutePanel();
	static AbsolutePanel p0  =new AbsolutePanel();
	static AbsolutePanel p1  =new AbsolutePanel();
	static AbsolutePanel p2a  =new AbsolutePanel();
	static AbsolutePanel p2  =new AbsolutePanel();
	static AbsolutePanel p3  =new AbsolutePanel();

	static ArrayList<EvenementItf> le = new ArrayList<EvenementItf>();
	static int decal = 0;

	public void onModuleLoad() {

		header.setWidth("1240px");
		header.setHeight("30px");
		header.getElement().getStyle().setBackgroundColor("#DFA009");
		RootPanel.get().add(header);

		p0.getElement().getStyle().setBackgroundColor("#FFFF00");
		p0.setWidth("400px");
		p0.setHeight("300px");
		RootPanel.get().add(p0,0,30);

		p1.getElement().getStyle().setBackgroundColor("#0140FF");
		p1.setWidth("400px");
		p1.setHeight("300px");
		RootPanel.get().add(p1,0,330);


		p2a.getElement().getStyle().setBackgroundColor("#DAB973");
		p2a.setWidth("400px");
		p2a.setHeight("100px");
		RootPanel.get().add(p2a, 420,30);

		p2.getElement().getStyle().setBackgroundColor("#FF0000");
		p2.setWidth("400px");
		p2.setHeight("600px");
		RootPanel.get().add(p2, 420,130);

		p3.getElement().getStyle().setBackgroundColor("#0D8412");
		p3.setWidth("400px");
		p3.setHeight("600px");
		RootPanel.get().add(p3,840,30);

		Label l = new Label();
		l.setText("Blablucar");
		header.add(l,0,0);

		Label inscription = new Label();
		inscription.setText("Nouveau sur le site ? Inscrivez-vous ici !");
		p0.add(inscription,0,30);

		int pre = 70;

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

		p0.add(nbpl,10,pre+125);
		p0.add(nbPlaVal,10,pre+150);

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

		p0.add(prenom,10,pre);
		p0.add(prenomVal,10,pre+25);

		p0.add(nom,10,pre+50);
		p0.add(nomVal,10,pre+75);

		p0.add(voit,10,pre+100);
		p0.add(voitVal,150,pre+100);

		Button aj = new Button();
		aj.setText("Valider");
		p0.add(aj,10,pre+200);

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

		Label ajoutE = new Label();
		ajoutE.setText("Vous voulez créer un nouvel évènement ? C'est ici que ça se passe !");
		p1.add(ajoutE,0,20);

		int eve = 50;

		Label date = new Label();
		date.setText("Date");

		Label lieu = new Label();
		lieu.setText("Lieu");

		Label heure = new Label();
		heure.setText("Heure");

		final DateBox dateVal = new DateBox();
		final TextBox lieuVal = new TextBox();
		final TextBox heureVal = new TextBox();

		p1.add(date,10,eve);
		p1.add(dateVal,10,eve+25);

		p1.add(lieu,10,eve+50);
		p1.add(lieuVal,10,eve+75);

		p1.add(heure,10,eve+100);
		p1.add(heureVal,10,eve+125);

		Button valider = new Button();
		valider.setText("Valider l'évènement");
		p1.add(valider,10,eve+175);

		valider.addClickHandler(new ClickHandler() {

			@SuppressWarnings("deprecation")
			public void onClick(ClickEvent event) {
				Date dat = dateVal.getValue(); 
				int annee = dat.getYear()+1900; //1900 date de depart
				int mois = dat.getMonth()+1; //0 est janvier
				String da = annee+"-"+mois+"-"+dat.getDate();
				String lie = lieuVal.getValue();
				String heu = heureVal.getValue();
				//EvenementItf ee = new Evenement(dat,lie,Integer.parseInt(heu));
				//String serializedEvenement = EvenementJsonConverter.getInstance().serializeToJson(ee);

				String serializedEvenement = 
						"{\"date\": \""+da+"\",\"lieu\": \""+lie+"\",\"heure\": \""+heu+"\"}";

				dateVal.setValue(null);
				lieuVal.setText("");
				heureVal.setText("");

				RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
						GWT.getHostPageBaseURL() + "rest/evenements/add");

				builder.setHeader("Content-Type", "application/json");
				builder.setRequestData(serializedEvenement);
				builder.setCallback(new RequestCallback() {
					public void onResponseReceived(Request request, Response response) {  
						if (200 == response.getStatusCode()) { 
							Window.alert("Vous avez bien réussi à vous ajouter un evenement");
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

		Label prev = new Label();
		prev.setText("N'oubliez que vous pouvez toujours vous inscrire"
				+ ", même s'il manque actuellement de places. "
				+ "Quelqu'un va peut-être s'inscrire après vous !");

		p2a.add(prev,10,30);


		Button listE = new Button();
		listE.setText("Voir la liste des évènements actuels :");
		p2a.add(listE,10,5);

		listE.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				RequestBuilder test = new RequestBuilder(RequestBuilder.GET, GWT
						.getHostPageBaseURL() + "rest/evenements/list/");
				test.setCallback(new RequestCallback() {

					public void onError(Request request, Throwable exception) {
						Window.alert(exception.getMessage());
					}

					public void onResponseReceived(Request request,
							Response response) {
						if (200 == response.getStatusCode()) {
							EvenementListItf lv = EvenementListJsonConverter.getInstance()
									.deserializeFromJson(response.getText());

							List<EvenementItf> lvv = lv.getEvenements();

							for (int i = 1; i<lvv.size(); i++){
								final EvenementItf v = lvv.get(i);

								Label l = new Label();
								l.setText("Evenement :" + v.getLieu());
								p2.add(l,10,80+decal);


								Button b = new Button();
								b.setText("Voir");
								p2.add(b,140,77+decal);

								Label nb_pla = new Label();
								int nbVal = v.getEtat_places();
								if (nbVal<0){
									nb_pla.setText("Actuellement, il manque : "+nbVal+ " places");
								}
								else{
									nb_pla.setText("Actuellement, il reste : "+nbVal+ " places");

								}
								p2.add(nb_pla,185,80+decal);

								decal += 50;

								b.addClickHandler(new ClickHandler() {

									@SuppressWarnings("deprecation")
									public void onClick(ClickEvent arg0) {
										p3.clear();

										final String idEvenement = v.getIdEvenement()+"";

										Label date = new Label();
										Label lieu = new Label();
										Label heure = new Label();
										int d = v.getDate().getDate();
										int m = v.getDate().getMonth()+1 ;
										int y = v.getDate().getYear()+1900;
										date.setText("Date : "+d+"/"+m+"/"+y);


										lieu.setText("Lieu : "+ v.getLieu());
										heure.setText("Heure : "+ v.getHeure()+ " h");

										int start = 0;

										p3.add(lieu,0,start+10);
										p3.add(date,0,start+30);
										p3.add(heure,0,start+50);

										Button aj = new Button();
										aj.setText("Je m'ajoute");
										p3.add(aj,0,start+100);
										aj.addClickHandler(new ClickHandler() {

											public void onClick(ClickEvent event) {
												Label id = new Label();
												id.setText("Veuillez entrer votre ID (reçu lors de votre inscription)");

												final TextBox idVal = new TextBox();

												int onCli = 150;
												p3.add(id,0,onCli);
												p3.add(idVal,0,onCli+20);

												Label attention = new Label();
												attention.setText("Ne cliquez que si vous avez renseigné un véhicule pour ce site");
												p3.add(attention,0,onCli+75);

												Button chauff = new Button();
												chauff.setText("Chauffeur cette fois !");
												p3.add(chauff,0,onCli+100);

												chauff.addClickHandler(new ClickHandler() {

													public void onClick(ClickEvent event) {
														String idPersonnee = idVal.getText();
														RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, GWT
																.getHostPageBaseURL() + "rest/personnes/Chauff/" + idPersonnee);
														rb.setCallback(new RequestCallback() {

															public void onError(Request request, Throwable exception) {
																Window.alert(exception.getMessage());
															}

															public void onResponseReceived(Request request,
																	Response response) {
																if (200 == response.getStatusCode()) {
																	Window.alert("Vous allez bien utiliser votre véhicule cette fois-ci");
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

												Button pasChauff = new Button();
												pasChauff.setText("Pas chauffeur cette fois !");
												p3.add(pasChauff,200,onCli+100);

												pasChauff.addClickHandler(new ClickHandler() {

													public void onClick(ClickEvent event) {
														String idPersonnee = idVal.getText();
														RequestBuilder rb = new RequestBuilder(RequestBuilder.POST, GWT
																.getHostPageBaseURL() + "rest/personnes/noChauff/" + idPersonnee);
														rb.setCallback(new RequestCallback() {

															public void onError(Request request, Throwable exception) {
																Window.alert(exception.getMessage());
															}

															public void onResponseReceived(Request request,
																	Response response) {
																if (200 == response.getStatusCode()) {
																	Window.alert("Vous ne serez pas chauffeur cette fois-ci");
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

												Button aj = new Button();
												aj.setText("Valider");
												p3.add(aj,0,onCli+160);

												aj.addClickHandler(new ClickHandler() {

													public void onClick(ClickEvent event) {

														String idPersonne = idVal.getText();

														RequestBuilder builder = new RequestBuilder(RequestBuilder.POST,
																GWT.getHostPageBaseURL() + "rest/evenements/addPersonneToEvenement/"+idPersonne+"/"+idEvenement);

														builder.setCallback(new RequestCallback() {
															public void onResponseReceived(Request request, Response response) {  
																if (200 == response.getStatusCode()) { 
																	p3.clear();
																	Window.alert("Vous avez été ajouté à cet évènement. Merci!");
																	p2.clear();
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
							decal = 0;
						}
					}
				});

				try {
					test.send();
				} catch (RequestException e) {
					e.printStackTrace();
				}
			}
		});
	}
}