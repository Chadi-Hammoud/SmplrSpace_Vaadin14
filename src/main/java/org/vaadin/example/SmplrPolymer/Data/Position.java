package org.vaadin.example.SmplrPolymer.Data;

public class Position {

	private double x;
	private double z;
	private double elevation;
	private int levelIndex;

	public Position() {

		this.x = 0.0;
		this.z = 0.0;
		this.elevation = 0.0;
		this.levelIndex = 0;
	}

	public Position( double elevation, double x,  double z , int levelIndex ) {
		this.x = x;
		this.z = z;
		this.elevation = elevation;
		this.levelIndex = levelIndex;
	}

	public double getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public double getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public double getElevation() {
		return elevation;
	}

	public void setElevation(float elevation) {
		this.elevation = elevation;
	}

	public int getLevelIndex() {
		return levelIndex;
	}

	public void setLevelIndex(int levelIndex) {
		this.levelIndex = levelIndex;
	}

}
