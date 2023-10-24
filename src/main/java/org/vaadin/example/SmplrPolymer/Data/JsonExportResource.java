package org.vaadin.example.SmplrPolymer.Data;

import java.io.ByteArrayInputStream;

import com.vaadin.flow.server.StreamResource;

public class JsonExportResource extends StreamResource {

	public JsonExportResource(String fileName, String jsonContent) {
		super(fileName, () -> {
			return new ByteArrayInputStream(jsonContent.getBytes());
		});
		setCacheTime(0); // Disables caching
	}
}
