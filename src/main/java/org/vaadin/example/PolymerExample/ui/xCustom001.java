package org.vaadin.example.PolymerExample.ui;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("demo-element")


@Route("Poly001")

//@JsModule("./src/js/PolymerExample/xCustom001.js")
@JsModule("./src/js/PolymerExample/demo-element.js")
//@JsModule("./src/js/PolymerExample/index.js")

public class xCustom001 extends PolymerTemplate<TemplateModel> {

	public xCustom001() {
		// TODO Auto-generated constructor stub
	}

}
