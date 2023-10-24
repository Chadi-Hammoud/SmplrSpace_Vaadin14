package org.vaadin.example.SmplrPolymer.ui;

import org.vaadin.example.SmplrPolymer.Data.FileReceiver;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.router.Route;

@Route("SmplrView")
@NpmPackage(value = "@smplrspace/smplr-loader", version = "2.10.0")

public class SmplrView extends VerticalLayout {
	private static final long serialVersionUID = -5575582976392893617L;

	public SmplrView() {

		SmplrSpace space = new SmplrSpace();

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

		Upload upload = new Upload(new FileReceiver());
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

		add(space, hrz, upload);
	}

}