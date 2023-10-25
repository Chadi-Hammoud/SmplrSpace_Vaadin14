package org.vaadin.example.SmplrPolymer.ui;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.vaadin.example.SmplrPolymer.Data.FileUploader;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;

@Route("SmplrView")
@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.10.0")

public class SmplrView extends VerticalLayout {
	private static final long serialVersionUID = -5575582976392893617L;

	private String imagePath;
	private String fileName;

	public SmplrView() {

		SmplrSpace space = new SmplrSpace();
		Button submitButton;

		Button removeBtn = new Button("Remove Point");
		removeBtn.addClickListener(event -> {
			space.removePoint();

			if (space.removePointByID()) {
				System.out.println("Point:' " + space.tempClickedPoint + "' was successfully deleted!");

				String script = "console.error('Point was removed Successuflly!');";
				UI.getCurrent().getPage().executeJs(script);
			} else {
				System.err.println("console.error('No Selected Point, Please select one!')");
				String script = "No Point selected Please select one!";
				UI.getCurrent().getPage().executeJs(script);
			}

		});
		Button disablePickBtn = new Button("Disable pick");
		disablePickBtn.addClickListener(event -> {
			space.disablePick();
			Notification.show("Pick Mode will be disabled");
		});

		Button updateDataLayersBtn = new Button("update Data");
		updateDataLayersBtn.addClickListener(event -> {
			space.updateDataLayers();
		});

		Button exportPointDataBtn = new Button("Export");
		exportPointDataBtn.addClickListener(event -> {
			Notification.show("Exporting Data...");
			space.exportPointData();
			Notification.show("Exporting Data is completed!");
		});

		Button importPointDataBtn = new Button("Import");
		importPointDataBtn.addClickListener(event -> {
			Notification.show("Importing Data...");
			space.importPointData();
			Notification.show("Importing Data is completed!");
		});

		Button enablePickingModeBtn = new Button("enable Picking Mode");
		enablePickingModeBtn.addClickListener(event -> {
			space.enablePickingMode();
		});

		HorizontalLayout hrz = new HorizontalLayout();

		Button JsonExportViewBtn = new Button("Json Export View");
		JsonExportViewBtn.addClickListener(event -> {
			Notification.show("Exporting Data...");
			space.JsonExportView(JsonExportViewBtn);
			Notification.show("Exporting Data is completed!");
		});

		Upload upload = new Upload(new FileUploader());
		upload.setAcceptedFileTypes("application/json");
		upload.setMaxFiles(1);

		upload.addSucceededListener(event -> {
			Notification.show("File uploaded successfully!");
			space.importData();
		});

		upload.addFailedListener(event -> {
			Notification.show("File upload failed: " + event.getReason());
		});

		hrz.add(enablePickingModeBtn, disablePickBtn, removeBtn, updateDataLayersBtn, exportPointDataBtn,
				importPointDataBtn, JsonExportViewBtn);

		// Upload an Icon
		MemoryBuffer buffer = new MemoryBuffer();

//		FileUploader buffer = new FileUploader();
		Upload uploadIcon = new Upload(buffer);
		uploadIcon.setMaxFiles(1);
		uploadIcon.setAcceptedFileTypes("image/jpeg", "image/jpg", "image/png");

//		uploadIcon.setReceiver((filename, mimeType) -> {
//			File file = new File(filename);
//			return file::setBytes;
//		});

		uploadIcon.addFailedListener(e -> {
			imagePath = null;

		});

		submitButton = new Button("Submit");

		Dialog dialog = new Dialog(new VerticalLayout(uploadIcon, submitButton));

		uploadIcon.addSucceededListener(event -> {
			InputStream inputStream = buffer.getInputStream();
			byte[] bytes;
			try {
				bytes = IOUtils.toByteArray(inputStream);

				// save the image file
				String fileName = event.getFileName();
				String directory = "C://vaadinImages"; // change this to your directory
				File outputFile = new File(directory, fileName);
				FileUtils.writeByteArrayToFile(outputFile, bytes);

				// read the saved image file and convert to base64 string
				byte[] fileContent = Files.readAllBytes(Paths.get(outputFile.getAbsolutePath()));
				String encodedString = Base64.getEncoder().encodeToString(fileContent);

				imagePath = "data:image/png;base64," + encodedString; // change "image/png" to the actual image format
				// if not png

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.err.println("image base 64: " + imagePath);
			
		});
		upload.addFailedListener(e -> {
			imagePath = null;
		});

		submitButton.addClickListener(event -> {
			dialog.close();

			System.out.println("File uploaded: " + fileName);
			Notification.show("File uploaded: " + fileName);
		});

		Button UploadIconbtn = new Button("enable Picking Mode");
		UploadIconbtn.addClickListener(event -> {
			dialog.open();
		});

		add(space, hrz, upload, UploadIconbtn);
	}

}