package org.vaadin.example.SmplrPolymer.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
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

		Button addBtn = new Button("Add Point");
		addBtn.addClickListener(event -> {
			// This code will be executed when the button is clicked
			space.addPoint();
			Notification.show("Button clicked! the data will be binded");
		});

		Button removeBtn = new Button("Remove Point");
		removeBtn.addClickListener(event -> {
			// This code will be executed when the button is clicked
			space.removePoint();
			Notification.show("Button clicked! Point will be deleted");
		});
		Button disablePickBtn = new Button("Disable pick");
		disablePickBtn.addClickListener(event -> {
			// This code will be executed when the button is clicked
			space.disablePick();
			Notification.show("Pick Mode will be disabled");
		});

		Button updateDataLayersBtn = new Button("update Data");
		updateDataLayersBtn.addClickListener(event -> {
			// This code will be executed when the button is clicked
			space.updateDataLayers();
//			Notification.show("Pick Mode will be disabled");
		});

		Button addPointDataJavaBtn = new Button("add Point Java");
		addPointDataJavaBtn.addClickListener(event -> {
			// This code will be executed when the button is clicked
			space.drawPoint();
//			Notification.show("Pick Mode will be disabled");
		});

		HorizontalLayout hrz = new HorizontalLayout();

		hrz.add(addBtn, removeBtn, disablePickBtn, updateDataLayersBtn, addPointDataJavaBtn);

		add(space, hrz);
	}

}