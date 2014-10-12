package fr.istic.gla.client;

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
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;

import fr.istic.gla.shared.BookItf;
import fr.istic.gla.shared.PersonneItf;
import fr.istic.gla.shared.VoitureItf;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class glagerseygwt implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	
	public void onModuleLoad() {

		/*AbsolutePanel p  =new AbsolutePanel();
		p.setWidth("600px");
		p.setHeight("600px");

		Label l = new Label();
		l.setText("nom:");
		p.add(l, 10, 10);
		final TextBox t = new TextBox();
		p.add(t, 100, 10);
		Button b = new Button();
		b.setText("ok");
		p.add(b, 100, 30);
		RootPanel.get().add(p);

		b.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent arg0) {
				Window.alert(t.getText());
			}
		});*/
		
		// Create a text
		final TextBox area = new TextBox();
		area.setValue("2");
		RootPanel.get().add(area);

		// Create a button
		com.google.gwt.user.client.ui.Button b = new Button();
		b.setText("test call json restful service");

		RootPanel.get().add(b);

		b.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				RequestBuilder rb = new RequestBuilder(RequestBuilder.GET, GWT
						.getHostPageBaseURL() + "rest/voitures/search/" + area.getText());
				rb.setCallback(new RequestCallback() {

					public void onError(Request request, Throwable exception) {
						Window.alert(exception.getMessage());
					}

					public void onResponseReceived(Request request,
							Response response) {
						if (200 == response.getStatusCode()) {
							/*BookItf b = BookJsonConverter.getInstance()
									.deserializeFromJson(response.getText());
							Window.alert("get the author :" + b.getIsbn());*/
							VoitureItf v = VoitureJsonConverter.getInstance()
									.deserializeFromJson(response.getText());
							Window.alert("get the voiture :" + v.getIdVoiture());
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

	}
}