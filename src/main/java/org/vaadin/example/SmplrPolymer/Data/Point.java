package org.vaadin.example.SmplrPolymer.Data;

import java.util.concurrent.atomic.AtomicInteger;

public class Point {

	private static final AtomicInteger atomicInt = new AtomicInteger(0);
	private String NAME;
	private int id;

	private Position position;

	public Point() {

	}

	public Point(Position position) {
		this.id = atomicInt.incrementAndGet();
		this.NAME = "Point";

		this.position = position;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
