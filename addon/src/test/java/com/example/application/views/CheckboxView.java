package com.example.application.views;

import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Checkbox")
@Route(value = "checkbox", layout = MainLayout.class)
public class CheckboxView extends Main {

	public CheckboxView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Checkbox"));
		createCheckboxGroups();
	}

	private void createCheckboxGroups() {
		Div checkboxGroups = new Div();
		checkboxGroups.addClassNames("flex", "flex-col", "gap-l");

		CheckboxGroup checkboxGroup = createCheckboxGroup();
		checkboxGroups.add(checkboxGroup);

		checkboxGroup = createCheckboxGroup();
		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		checkboxGroups.add(checkboxGroup);

		checkboxGroup = createCheckboxGroup();
		checkboxGroup.setEnabled(false);
		checkboxGroups.add(checkboxGroup);

		checkboxGroup = createCheckboxGroup();
		checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
		checkboxGroup.setEnabled(false);
		checkboxGroups.add(checkboxGroup);

		add(checkboxGroups);
	}

	private CheckboxGroup createCheckboxGroup() {
		CheckboxGroup checkboxGroup = new CheckboxGroup();
		checkboxGroup.setItems("Checkbox label 1", "Checkbox label 2");
		checkboxGroup.setLabel("Checkbox heading");
		return checkboxGroup;
	}
}
