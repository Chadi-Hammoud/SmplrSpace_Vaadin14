package org.vaadin.example.SmplrPolymer.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.vaadin.example.SmplrPolymer.Data.Point;
import org.vaadin.example.SmplrPolymer.Data.Position;
import org.vaadin.example.SmplrPolymer.Data.SpaceService;

import com.google.gson.Gson;
import com.vaadin.flow.component.ClientCallable;
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

	List<Point> points = new ArrayList<Point>();

	private static final String SPACE_ID = "b6e6acf9-1524-4f2b-a257-c6edd64832e0";
	private static final String CLIENT_TOKEN = "pub_fc4b7f5e33bd49cf98912c56c404de8c";
	private static final String CONTAINER_ID = "container";

	private double pointX = 0.0;
	private double pointZ = 0.0;
	private int pointLevelIndex = 0;
	private double pointelEvation = 0.0;

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

//		bindData();
//		getElement().callJsFunction("startView");
//		getElement().callJsFunction("startViewSpace", SPACE_ID, CLIENT_TOKEN, CONTAINER_ID);
//		System.out.print("Starting View");

	}

//	public void bindData() {
//
//		Position pos = new Position(2.4597055500462695, -7.325816322330535, -12.077383563026133, 1);
//		Point p = new Point("P", pos);
//		points.add(p);
//
//		String jsonPoint = gson.toJson(points);
//
//		getElement().setProperty("Point", jsonPoint);
//		System.out.println(jsonPoint);
//
//		System.out.println(p);
//
//	}

	/////////////////////////////////////////////////////

	public SpaceService getData() {

		SpaceService data = new SpaceService(points);
		return data;
	}

	public void bindListOfPointsToPolymerComponent(SpaceService data) {

		String PointsJson = gson.toJson(data.getPoints());
		getElement().setProperty("pointList", PointsJson);
	}

	public void bindDataToPolymerComponent() {
		SpaceService data = getData(); // Retrieve data from Java classes
		bindListOfPointsToPolymerComponent(data);
	}

	public void addInitPoint(String shape) {
		UUID ptId = UUID.randomUUID();
		Position pos = new Position(2.4597055500462695, -7.325816322330535, -12.077383563026133, 1);
		Point p = new Point(ptId, pos);
		System.out.println("Point:" + p);
		points.add(p);
		System.out.println("Point:" + points);
		// Convert the new Point to JSON

		bindDataToPolymerComponent();

		getElement().callJsFunction("addPoint");

	}

	@ClientCallable
	public void setClientData(double x, double z, int levelIndex, double elevation) {
		pointX = x;
		pointZ = z;
		pointLevelIndex = levelIndex;
		pointelEvation = elevation;

		System.out.println("Point is: (" + pointX + ";" + pointZ + ";" + pointLevelIndex + ";" + pointelEvation + ")");
	}

	public void drawPoint() {
		UUID ptId = UUID.randomUUID();
		
		Position pos = new Position(pointelEvation, pointX, pointZ, pointLevelIndex);
		Point pt = new Point(ptId, pos);
		points.add(pt);
		System.err.print(points);
		bindDataToPolymerComponent();
		addPointDataJava();

	}
	
	public void addPointDataJava() {
		getElement().callJsFunction("addPointDataJava");
	}

	/////////////////////////////////////////////////////

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

	public void removePoint() {
		getElement().callJsFunction("removePoint");
	}

	public void disablePick() {
		getElement().callJsFunction("disablePick");
	}

	public void updateDataLayers() {
		getElement().callJsFunction("updateDataLayers");
	}

}
