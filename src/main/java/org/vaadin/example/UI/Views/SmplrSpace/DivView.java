package org.vaadin.example.UI.Views.SmplrSpace;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;

//@Route("")
@JsModule("./src/js/SmplrSpace/DivViewJS.js")
public class DivView extends Div {

	public DivView() {
		Div divElement = new Div();
		divElement.setId("container");
		add(divElement);
	}
}
