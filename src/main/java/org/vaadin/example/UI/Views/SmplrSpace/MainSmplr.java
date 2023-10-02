package org.vaadin.example.UI.Views.SmplrSpace;

import org.vaadin.example.UI.Views.MainLayout;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.notification.Notification;

import com.vaadin.flow.router.Route;

@Tag("smplr-space")
@Route(value = "", layout = MainLayout.class)
@JsModule("./src/js/SmplrSpace/DivViewJS.js")
@JsModule("./src/js/SmplrSpace/alerty.js")

public class MainSmplr extends VerticalLayout {

	public MainSmplr() {
		DivView spaceView = new DivView();

		Button remove = new Button("Remove Dep Layer");
		remove.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		remove.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("remove();");
		});

		Button draw = new Button("Draw Dep Layer");
		draw.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		draw.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("drawDataLayer();");
		});
		
		Button addPoint = new Button("Add Point");
		draw.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		draw.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("addPoint();");
		});
		
		Button disablePickMode = new Button("Disable Pick Mode");
		disablePickMode.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		disablePickMode.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("disablePick();");
		});
		
		
		Button showPoints = new Button("showPoints");
		showPoints.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		showPoints.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("showPoints();");
		});
		



		add(spaceView, remove, draw, addPoint, disablePickMode, showPoints);

	}
}