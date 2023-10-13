package org.vaadin.example.SmplrPolymer.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.vaadin.example.SmplrPolymer.Data.Point;
import org.vaadin.example.SmplrPolymer.Data.Position;

import com.google.gson.Gson;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Route("Space")

@Tag("smplrspace-container")

@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.10.0")

@JsModule("./src/js/Smplr/SmplrSpaceContainer.js")

public class SmplrSpace extends PolymerTemplate<TemplateModel> {
	private static final long serialVersionUID = 1882208222260143747L;

	List<Point> points = new ArrayList<>();

	private static final String SPACE_ID = "b6e6acf9-1524-4f2b-a257-c6edd64832e0";
	private static final String CLIENT_TOKEN = "pub_fc4b7f5e33bd49cf98912c56c404de8c";
	private static final String CONTAINER_ID = "container";
	private Gson gson = new Gson();

	private Map<Integer, Point> idToPoint = new HashMap<>();
	private int nextPointId = 0;

	public SmplrSpace() {

		String jsonSpace = gson.toJson(SPACE_ID);
		String jsonClientToken = gson.toJson(CLIENT_TOKEN);
		String jsonContainerID = gson.toJson(CONTAINER_ID);

		getElement().setProperty("spaceId", jsonSpace);
		getElement().setProperty("clientToken", jsonClientToken);
		getElement().setProperty("containerId", jsonContainerID);

		bindData();
//		getElement().callJsFunction("startView");
//		getElement().callJsFunction("startViewSpace", SPACE_ID, CLIENT_TOKEN, CONTAINER_ID);
//		System.out.print("Starting View");

	}

	public void bindData() {

		// float elevation, float x, float z , int levelIndex
		Position pos = new Position(2.4597055500462695, -7.325816322330535, -12.077383563026133, 1);
		Point p = new Point("P",pos);
		points.add(p);

		String jsonPoint = gson.toJson(points);

		getElement().setProperty("Point", jsonPoint);
		System.out.println(jsonPoint);

		System.out.println(p);

	}

	public void startView() {
		getElement().callJsFunction("startSpaceView");
	}

//	public void addPoint(List<Point> pt) {

	public void addPoint() {
		getElement().callJsFunction("addPoint");
	}
	
	public void drawPoints() {
		getElement().callJsFunction("addPointDataLayer");
	}

}
