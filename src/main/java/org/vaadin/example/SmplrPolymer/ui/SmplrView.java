package org.vaadin.example.SmplrPolymer.ui;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("SmplrView")
//Download the Leaflet JS files using NPM
@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.10.0")

public class SmplrView extends VerticalLayout {

	public SmplrView() {
		Div div = new Div();
		div.setId("container");
		div.getElement().getStyle().set("height", "400px");
		div.getElement().getStyle().set("width", "800px");
		SmplrSpace space = new SmplrSpace();

		add(div, space);
	}

}