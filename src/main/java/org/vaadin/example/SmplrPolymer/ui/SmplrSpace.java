package org.vaadin.example.SmplrPolymer.ui;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.google.gson.Gson;

@Route("Space")

@Tag("smplrspace-container")

@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.9.0")

@JsModule("./src/js/Smplr/SmplrSpaceContainer.js")

public class SmplrSpace extends PolymerTemplate<TemplateModel> {

	public static final String SPACE_ID = "b6e6acf9-1524-4f2b-a257-c6edd64832e0";
	public static final String CLIENT_TOKEN = "pub_fc4b7f5e33bd49cf98912c56c404de8c";
	public static final String CONTAINER_ID = "container";

	public SmplrSpace() {
		Gson gson = new Gson();

		String jsonSpace = gson.toJson(SPACE_ID);
		String jsonClientToken = gson.toJson(CLIENT_TOKEN);
		String jsonContainerID = gson.toJson(CONTAINER_ID);

		getElement().setProperty("spaceId", jsonSpace);
		getElement().setProperty("clientToken", jsonClientToken);
		getElement().setProperty("containerId", jsonContainerID);

//		getElement().callJsFunction("startView");

		getElement().callJsFunction("startViewSpace", SPACE_ID, CLIENT_TOKEN, CONTAINER_ID);
//		System.out.print("Starting View");

	}

}
