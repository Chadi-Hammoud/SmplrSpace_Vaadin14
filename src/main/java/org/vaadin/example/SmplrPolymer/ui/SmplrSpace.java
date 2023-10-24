package org.vaadin.example.SmplrPolymer.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.vaadin.example.SmplrPolymer.Data.FileReceiver;
import org.vaadin.example.SmplrPolymer.Data.JsonExportResource;
import org.vaadin.example.SmplrPolymer.Data.Point;
import org.vaadin.example.SmplrPolymer.Data.Position;
import org.vaadin.example.SmplrPolymer.Data.SpaceService;
import org.vaadin.olli.FileDownloadWrapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.vaadin.flow.component.ClientCallable;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
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

	String tempClickedPoint;
	Position tempPosition;

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

		Position pos = new Position(elevation, x, z, levelIndex);
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

	@ClientCallable
	public void getClickedPoint(String _id) {
		tempClickedPoint = _id;
		System.err.println("Clicked Point is: " + _id);
	}

	public boolean removePointByID() {

		if (tempClickedPoint == null) {

			return false;

		} else {

			List<Point> newPoints = points.stream().filter(point -> !point._getId().equals(tempClickedPoint))
					.collect(Collectors.toList());

			// Update the points list
			points = newPoints;

			// Send Data to PolytmerElement class
			bindDataToPolymerComponent();

			// Dispatch the update
			dispatchPoint("updateView", null, null);

			// Reset the temporary point
			tempPosition = null;

			return true;
		}

	}

	/////////////////////////////////////////////////////////
	public void drawPoint() {

		drawPoint(tempPosition);
	}

	public void drawPoint(Position pos) {

		dispatchPoint("add", null, pos);
	}

	public void removePoint() {
		dispatchPoint("remove", tempClickedPoint, null);
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
		case "remove":
			removePointByID();
			break;
		case "updateView":
			updateView();
			break;

		case "importView":
			importView();
			break;
		default:
			System.out.println("Unknown action type " + type);
		}
	}

	///////////////////////////////////////////////////////
	// export to local storage
	public void exportPointData() {
		String json = gson.toJson(points);

		try (FileWriter file = new FileWriter(
				"C:\\Chadi Hammoud\\SmplrSpace-Vaadin\\src\\main\\resources\\TestingData\\Exported\\Data001.json")) {
			file.write(json);
			System.out.println("Successfully Copied JSON Array to File...");
			System.out.println("\nJSON Array: " + json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(points.size());
	}

	/////////////////////////////////////////////////////
	// Exporting 001
	public void JsonExportView(Button downloadButton) {
		String jsonContent = gson.toJson(points);
		// Create a StreamResource with the dynamically generated JSON content
		StreamResource resource = new StreamResource("data.json", () -> {
			byte[] jsonBytes = jsonContent.getBytes();
			return new ByteArrayInputStream(jsonBytes);
		});

		// Set the Content-Disposition header to suggest a filename
		resource.setCacheTime(0); // Disables caching
		resource.setContentType("application/json");

		// Create a download link
		Anchor downloadLink = new Anchor(resource, "Download JSON");
		downloadLink.getElement().setAttribute("download", true);

		downloadLink.getStyle().set("display", "none"); // Hide the link
		getElement().appendChild(downloadLink.getElement());

		// Trigger a click on the hidden download link
		downloadLink.getElement().callJsFunction("click");

	}

	/////////////////////////////////////////////////////
	// Upload Data from local Storage
	public void importPointData() {

		String filePath = "C:\\Chadi Hammoud\\SmplrSpace-Vaadin\\src\\\\main\\resources\\TestingData\\Exported\\Data001.json";

		try {
			String jsonData = new String(Files.readAllBytes(Paths.get(filePath)));
			ObjectMapper objectMapper = new ObjectMapper();
			points = objectMapper.readValue(jsonData, new TypeReference<List<Point>>() {
			});
			// You can now work with the parsed data
			Notification.show("Parsed " + points.size() + " points.");

			bindDataToPolymerComponent();
			importView();
		} catch (IOException e) {
			e.printStackTrace();
			Notification.show("Failed to read and parse the JSON file.");
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

	public void importView() {
		getElement().callJsFunction("importView");
	}

	public void startView() {
		getElement().callJsFunction("startSpaceView");
	}

	public void addPoint() {
		getElement().callJsFunction("addPoint");
	}

	public void drawPoints() {
		getElement().callJsFunction("addPointDataLayer");
	}

	public void removePointClientSide() {
		getElement().callJsFunction("removePoint");
	}

	public void disablePick() {
		getElement().callJsFunction("disablePick");
	}

	public void updateDataLayers() {
		getElement().callJsFunction("updateDataLayers");
	}

	public void importData() {
		try {
			InputStream uploadedFile = new FileReceiver().getUploadedFileInputStream();
			if (uploadedFile != null) {
				ObjectMapper objectMapper = new ObjectMapper();
				points = objectMapper.readValue(uploadedFile, new TypeReference<List<Point>>() {
				});
				Notification.show("Parsed " + points.size() + " points.");

				bindDataToPolymerComponent();
				importView();
			} else {
				Notification.show("Uploaded file is null.");
			}
		} catch (IOException e) {
			e.printStackTrace();
			Notification.show("Failed to read and parse the uploaded JSON file.");
		}
	}

}
