package org.vaadin.example.SmplrPolymer.Data;

public class UpdatesPointPosition {

	private String ptID;
	private Position pos;

	public UpdatesPointPosition(String ptID, Position pos) {
		this.ptID = ptID;
		this.pos = pos;
	}
	
	public String getPointID() {
		return ptID;
	}
	
	public Position getPosition() {
		return pos;
	}
	
	

}
