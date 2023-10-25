package org.vaadin.example.SmplrPolymer.Data;

public class Icon {

	private String url;
	private int width;
	private int height;

	public Icon(String uRL, int width, int height) {
		super();
		this.url = uRL;
		this.width = width;
		this.height = height;
	}

	public Icon() {
	}

	public String getURL() {
		return url;
	}

	public void setURL(String uRL) {
		this.url = uRL;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
