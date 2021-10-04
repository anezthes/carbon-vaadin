package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Select")
@Route(value = "select", layout = MainLayout.class)
public class SelectView extends Main {

	public SelectView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Select"));
		createSelects();
	}

	private void createSelects() {
		Div fields = new Div();
		fields.addClassNames("flex", "flex-wrap", "gap-m");

		Select select = createSelect();
		fields.add(select);

		select = createSelect();
		select.setHelperText("");
		select.setInvalid(true);
		select.setErrorMessage("A valid value is required");
		fields.add(select);

		select = createSelect();
		select.setEnabled(false);
		fields.add(select);

		select = createSelect();
		select.getElement().getThemeList().add("small");
		fields.add(select);

		select = createSelect();
		select.getElement().getThemeList().add("large");
		fields.add(select);

		add(fields);
	}

	private Select createSelect() {
		Select select = new Select();
		select.setLabel("Select");
		select.setItems("Option 1", "Option 2", "Option 3", "Option 4");
		select.setHelperText("Optional helper text");
		return select;
	}
}
