package org.vaadin.example.SmplrPolymer.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.vaadin.example.SmplrPolymer.Data.Point;
import org.vaadin.example.SmplrPolymer.Data.PointEvent;
import org.vaadin.example.SmplrPolymer.Data.Position;
import org.vaadin.example.SmplrPolymer.Data.SpaceService;
import org.vaadin.example.SmplrPolymer.Data.UpdatesPointPosition;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.dom.PropertyChangeEvent;
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
	
	Position tempPosition;

//	private Map<Integer, Point> idToPoint = new HashMap<>();
//
//	private int nextPointId = 0;

	public SmplrSpace() {

		String jsonSpace = gson.toJson(SPACE_ID);
		String jsonClientToken = gson.toJson(CLIENT_TOKEN);
		String jsonContainerID = gson.toJson(CONTAINER_ID);

		getElement().setProperty("spaceId", jsonSpace);
		getElement().setProperty("clientToken", jsonClientToken);
		getElement().setProperty("containerId", jsonContainerID);

	}

	public SpaceService getData() {

		SpaceService data = new SpaceService(points);
		return data;
	}

	public void bindListOfPointsToPolymerComponent(SpaceService data) {

		String PointsJson = gson.toJson(data.getPoints());
		System.out.println(PointsJson);
		getElement().setProperty("pointList", PointsJson);
	}

	public void bindDataToPolymerComponent() {
		SpaceService data = getData(); // Retrieve data from Java classes
		bindListOfPointsToPolymerComponent(data);
	}

	@ClientCallable
	public void setClientData(double x, double z, int levelIndex, double elevation) {
		pointX = x;
		pointZ = z;
		pointLevelIndex = levelIndex;
		pointelEvation = elevation;
		
		Position pos = new Position(elevation, x,z,levelIndex);
		tempPosition = pos;
		
		drawPoint(pos);

		System.out.println("Point is: (" + pointX + ";" + pointZ + ";" + pointLevelIndex + ";" + pointelEvation + ")");
		
	}

	@ClientCallable
	public void setClientUpdatedData(String _recievedData) {
		System.out.println("Recieved Data:" + _recievedData);

		JsonObject data = gson.fromJson(_recievedData, JsonObject.class);
		String id = data.get("id").getAsString();
		JsonObject updates = data.get("updates").getAsJsonObject();

		Position _updatedPosition = new Position();

		double _newPosX = updates.get("x").getAsDouble();
		double _newPosZ = updates.get("z").getAsDouble();
		int _newPosLevelIndex = updates.get("levelIndex").getAsInt();
		double _newPosElevation = updates.get("elevation").getAsDouble();

		_updatedPosition.setX(_newPosX);
		_updatedPosition.setZ(_newPosZ);
		_updatedPosition.setLevelIndex(_newPosLevelIndex);
		_updatedPosition.setElevation(_newPosElevation);


		bindDataToPolymerComponent();
		
		dispatchPoint("update", id, _updatedPosition);
		
		

		System.err.println("ID: " + id);
		System.err.println("Updates: " + updates);
	}

	// Method to update the position of a point in the list
	public void updatePointPosition(String pointId, Position pos) {

		for (Point point : points) {
			if (point._getId().equals(pointId)) {

				point.getPosition().setX(pos.getX());
				point.getPosition().setZ(pos.getZ());
				point.getPosition().setElevation(pos.getElevation());
				point.getPosition().setElevation(pos.getElevation());

				break;
			}
		}
		bindDataToPolymerComponent();

		Iterator<Point> iterator = points.iterator();

		// Iterate through the list using the iterator
		while (iterator.hasNext()) {
			Point point = iterator.next();
			System.out.println(" New Point: (" + point.getPosition().getX() + ")");
		}
		
	}
	
	public void drawPoint() {

		drawPoint(tempPosition);
	}

	public void drawPoint(Position pos) {

		dispatchPoint("add", null, pos);
	}

	public void addPointData(Position pos) {
		Point pt = new Point(pos);
		try {
			// Check if a point with the same ID already exists
			if (!pointExists(pt._getId())) {
				points.add(pt);
				System.out.println("Point " + pt.getName() + " was successfully added to Points");

				bindDataToPolymerComponent();
				addPointDataJava();
			} else {
				System.out.println("Point with ID " + pt._getId() + " already exists and won't be added.");
			}

		} catch (Exception e) {
			// Handle other exceptions
			System.out.println("An unexpected error occurred." + e.getMessage());
		}
	}

	// Helper method to check if a point with the same ID exists
	private boolean pointExists(String id) {
		for (Point existingPoint : points) {
			if (existingPoint._getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	/////////////////////////////////////////////////////////

	public void dispatchPoint(String type, String _id, Position newPosition) {
		switch (type) {
		case "add":
			addPointData(newPosition);
			break;
		case "update":
			updatePointPosition(_id, newPosition);
			break;
//	        case "remove":
//	            removePoint(action.getPoint().getId());
//	            break;
		default:
			System.out.println("Unknown action type " + type);
		}
	}

	/////////////////////////////////////////////////////

	public void addPointDataJava() {
		getElement().callJsFunction("addPointDataJava");
	}
	
	public void enablePickingMode() {
		getElement().callJsFunction("enablePickingMode");
	}

	public void updateView() {
		getElement().callJsFunction("updateView");
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
