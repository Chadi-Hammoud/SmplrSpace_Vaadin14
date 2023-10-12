package org.vaadin.example.SmplrPolymer.Data;

import java.util.concurrent.atomic.AtomicInteger;

public class Point {

	private static final AtomicInteger atomicInt = new AtomicInteger(0);
	private String name;
	private String id;

	private Position position;

	public Point() {

	}
	
	public Point(String id, Position position) {
		this.id = id;
		this.name = "Point";

		this.position = position;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
