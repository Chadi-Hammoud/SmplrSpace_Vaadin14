package org.vaadin.example.SmplrPolymer.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.vaadin.example.SmplrPolymer.ui.SmplrSpace;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;

@Route("import-point-data")
public class FileReceiver implements Receiver {
	private byte[] fileBytes;

	@Override
	public OutputStream receiveUpload(String fileName, String mimeType) {
	    fileBytes = null; // Initialize the array to null
	    return new ByteArrayOutputStream() {
	        @Override
	        public void close() throws IOException {
	            super.close();
	            fileBytes = toByteArray(); // Set the array to the actual data
	        }
	    };
	}

	public InputStream getUploadedFileInputStream() {
		if (fileBytes != null) {
			return new ByteArrayInputStream(fileBytes);
		} else {
			return null; // Handle the case where fileBytes is null
		}
	}
}
