package org.vaadin.example.SmplrPolymer.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.vaadin.flow.component.upload.Receiver;

public class FileUploader implements Receiver {
	private File file;
	private String filename;

	public OutputStream receiveUpload(String filename, String mimeType) {
		FileOutputStream fos = null;
		try {
			this.filename = "C:\\vaadinImages" + filename;
			file = new File(this.filename);
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fos;
	}

	public String getFilename() {
		return filename;
	}
}
