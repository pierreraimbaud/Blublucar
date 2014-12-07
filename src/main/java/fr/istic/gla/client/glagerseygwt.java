package fr.istic.gla.client;

import java.util.ArrayList;
import java.util.Date;

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

		int pre = 50;

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

		p.add(nbpl,10,pre+125);
		p.add(nbPlaVal,10,pre+150);

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

		p.add(prenom,10,pre);
		p.add(prenomVal,10,pre+25);

		p.add(nom,10,pre+50);
		p.add(nomVal,10,pre+75);

		p.add(voit,10,pre+100);
		p.add(voitVal,150,pre+100);

		Button aj = new Button();
		aj.setText("Valider");
		p.add(aj,10,pre+200);

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

		int eve = 350;

		Label date = new Label();
		date.setText("Date");

		Label lieu = new Label();
		lieu.setText("Lieu");

		Label heure = new Label();
		heure.setText("Heure");


		final DateBox dateVal = new DateBox();
		final TextBox lieuVal = new TextBox();
		final TextBox heureVal = new TextBox();

		p.add(date,10,eve);
		p.add(dateVal,10,eve+25);

		p.add(lieu,10,eve+50);
		p.add(lieuVal,10,eve+75);

		p.add(heure,10,eve+100);
		p.add(heureVal,10,eve+125);

		Button valider = new Button();
		valider.setText("Valider l'évènement");
		p.add(valider,10,eve+175);

		valider.addClickHandler(new ClickHandler() {

			@SuppressWarnings("deprecation")
			public void onClick(ClickEvent event) {
				Date dat = dateVal.getValue(); 
				int annee = dat.getYear()+1900; //1900 date de depart
				int mois = dat.getMonth()+1; //0 est janvier
				String da = annee+"-"+mois+"-"+dat.getDay();
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

		for (int i =3; i<8; i++){
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
						l.setText("Evenement :" + v.getLieu());
						p2.add(l,10,80+decal);


						Button b = new Button();
						b.setText("ok");
						p2.add(b, 150,77+decal);

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
								int d = v.getDate().getDay();
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