package org.vaadin.example.SmplrPolymer.Data;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class Point {

	private String name;
	private UUID uuidString = UUID.randomUUID();
	
	String id = uuidString.toString();

	private AtomicInteger pointCount = new AtomicInteger(0);

	private Position position;

	public Point() {
		this.id = id;
		this.name = " Annonimus Point "+pointCount;

	}
	
	public Point(Position position) {
		this.id = id;
		this.name = "Point "+pointCount;
		this.position = position;
	}
	

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
