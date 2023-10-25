package org.vaadin.example.SmplrPolymer.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;

@Route("test")
public class test extends VerticalLayout {

	public test() {
		
		MemoryBuffer memoryBuffer = new MemoryBuffer();
		Upload upload = new Upload(memoryBuffer);
		upload.setMaxFiles(1);
		upload.setAcceptedFileTypes("image/jpeg", "image/png");
		upload.addSucceededListener(event -> {
		    String fileName = event.getFileName();
		    InputStream inputStream = memoryBuffer.getInputStream();
		    // Do something with the file data
		    // processFile(inputStream, fileName);
		    
		    System.out.println(fileName);
		    System.out.println(inputStream);
		});
		add(upload);

	        
	        
	}
}
