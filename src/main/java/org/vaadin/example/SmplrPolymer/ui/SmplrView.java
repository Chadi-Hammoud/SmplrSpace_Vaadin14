package org.vaadin.example.SmplrPolymer.ui;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("SmplrView")

public class SmplrView extends VerticalLayout {

	public SmplrView() {
		Div div = new Div();
		div.setId("container");
		SmplrSpace space = new SmplrSpace();

		add(div, space);
	}

}
