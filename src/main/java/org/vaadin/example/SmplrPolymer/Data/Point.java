package org.vaadin.example.SmplrPolymer.Data;

import java.util.UUID;

public class Point {

	private String name;
	private UUID id = UUID.randomUUID();;

	private Position position;

	public Point() {

	}
	
	public Point(Position position) {
		this.id = id;
		this.name = "Point";

		this.position = position;
	}
	

	public UUID getId() {
		return id;
	}

//	public void setId(UUID id) {
//		this.id = id;
//	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
