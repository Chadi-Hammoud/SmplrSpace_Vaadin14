package org.vaadin.example.SmplrPolymer.Data;

import java.util.List;


public class SpaceService {

	private List<Point> points;
	
	public SpaceService() {
		Point p = new Point();
		
        this.points.add(p);
    }

	public SpaceService(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

	
	

}
