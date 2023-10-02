package org.vaadin.example.UI.Views.SmplrSpace;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


@Route("btn")
@JsModule("./src/js/SmplrSpace/alerty.js")
public class alerty extends VerticalLayout{
	
	public alerty() {
		Button myButton = new Button("Click Me");
		myButton.addClickListener(event -> {
		    // Call a JavaScript function
		    Page page = UI.getCurrent().getPage();
		    page.executeJs("myJavaScriptFunction();");
		});
		
		TextField textField = new TextField();
		add(textField);
		add(new Button("Alert current input text", click -> {
			UI.getCurrent().getPage().executeJs("alertString('"+textField.getValue()+"')");
		}));
		
		
		add(myButton);

	}

}
