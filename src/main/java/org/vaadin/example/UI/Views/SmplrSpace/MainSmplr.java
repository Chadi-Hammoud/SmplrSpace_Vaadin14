package org.vaadin.example.UI.Views.SmplrSpace;

//import org.vaadin.example.UI.Views.MainLayout;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ClickNotifier;
import com.vaadin.flow.component.notification.Notification;

import com.vaadin.flow.router.Route;

//@Route(value = "", layout = MainLayout.class)
public class MainSmplr extends VerticalLayout {

	public MainSmplr() {
		DivView spaceView = new DivView();
		spaceView.getStyle().set("width", "100%");
		spaceView.getStyle().set("height", "100%");
		
		
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
		addPoint.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		addPoint.addClickListener(event -> {
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
		
		Button selectPoint = new Button("Select Point");
		selectPoint.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		selectPoint.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("clickPoint();");
		});
		
		Button stopViewr = new Button("Stop View");
		stopViewr.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		stopViewr.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("stopViewr();");
		});
	
		Button runView = new Button("Run View");
		runView.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
		runView.addClickListener(event -> {
			Page page = UI.getCurrent().getPage();
			page.executeJs("runView();");
		});

		HorizontalLayout horizontalView = new HorizontalLayout();
		horizontalView.add(remove, draw, addPoint, disablePickMode, showPoints, selectPoint,stopViewr,runView);
		add(spaceView, horizontalView);

	}
}