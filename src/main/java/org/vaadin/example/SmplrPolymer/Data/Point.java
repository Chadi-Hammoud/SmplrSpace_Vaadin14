package org.vaadin.example.SmplrPolymer.Data;

import java.util.UUID;

public class Point {

	private String id;
	private UUID uuidString = UUID.randomUUID();
	
	private String _id = uuidString.toString();



	private Position position;

	public Point() {
		this.id = " Annonimus Point ";

	}
	
	public Point(Position position) {
		this.id = "point";
		this.position = position;
	}
	

	public String _getId() {
		return _id;
	}

	public String getName() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
