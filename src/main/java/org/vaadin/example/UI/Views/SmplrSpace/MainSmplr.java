package org.vaadin.example.UI.Views.SmplrSpace;

import org.vaadin.example.UI.Views.MainLayout;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Tag("smplr-space")
@Route(value = "", layout = MainLayout.class)
@JsModule("./src/js/SmplrSpace/start-view.js")
public class MainSmplr extends VerticalLayout {

	public MainSmplr() {
		setId("container");
//		UI.getCurrent().getPage().executeJs("startView");
		UI.getCurrent().getPage().executeJs("sayHi()");

	}

}
