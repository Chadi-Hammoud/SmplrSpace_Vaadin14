package org.vaadin.example.SmplrPolymer.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("SmplrView")
//Download the Leaflet JS files using NPM
@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.10.0")

public class SmplrView extends VerticalLayout {

	public SmplrView() {
//		Div div = new Div();
//		div.setId("container");
//		div.getElement().getStyle().set("height", "400px");
//		div.getElement().getStyle().set("width", "800px");
		SmplrSpace space = new SmplrSpace();

		Button btn = new Button("Add Point");
		btn.addClickListener(event -> {
			// This code will be executed when the button is clicked
			space.addPoint();
			Notification.show("Button clicked! the data will be binded");
		});

		add(space, btn);

//		add(div, space);
	}

}