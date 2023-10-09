package org.vaadin.example.PolymerExample.ui;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "poly003", layout = MainLayout.class)

@Tag("demo-element")

@JsModule("./src/js/PolymerExample/demo-element.js")
public class xCustom003 extends VerticalLayout {

	public xCustom003() {
		// TODO Auto-generated constructor stub

	}

}
