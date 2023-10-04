package org.vaadin.example.SmplrPolymer.ui;

import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Route("Space")

@Tag("smplrspace-container")

@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.9.0")

@JsModule("./src/js/Smplr/SmplrSpaceContainer.js")

public class SmplrSpace extends PolymerTemplate<TemplateModel> implements HasSize{


	private static final String SPACE_ID = "b6e6acf9-1524-4f2b-a257-c6edd64832e0";
	private static final String CLIENT_TOKEN = "pub_fc4b7f5e33bd49cf98912c56c404de8c";
	private static final String CONTAINER_ID = "container";
	
	public SmplrSpace() {
		// TODO Auto-generated constructor stub
//		getElement().callJsFunction("ready();");
	}
	
	

}
