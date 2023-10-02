package org.vaadin.example.UI.Views;

import org.vaadin.example.UI.Views.SmplrSpace.MainSmplr;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;


@Theme(value = Lumo.class, variant = Lumo.DARK)

public class MainLayout extends AppLayout {
	private static final long serialVersionUID = 6899752449650527426L;

	public MainLayout() {
		createHeader();
		createDrawer();
	}

	private void createHeader() {
		H1 logo = new H1("Floor Plan V 1.0.0");

		HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

		header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
		header.setWidthFull();

		addToNavbar(header);

	}

	private void createDrawer() {

		addToDrawer(new VerticalLayout(new RouterLink("Index View", MainSmplr.class)));


	}
}