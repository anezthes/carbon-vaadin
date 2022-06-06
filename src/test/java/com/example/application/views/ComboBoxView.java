package com.example.application.views;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Combo box")
@Route(value = "combo-box", layout = MainLayout.class)
public class ComboBoxView extends Main {

	public ComboBoxView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Combo box"));
		createComboBoxes();
	}

	private void createComboBoxes() {
		Div fields = new Div();
		fields.addClassNames("flex", "flex-wrap", "gap-m");

		ComboBox comboBox = createComboBox();
		fields.add(comboBox);

		comboBox = createComboBox();
		comboBox.setHelperText("");
		comboBox.setInvalid(true);
		comboBox.setErrorMessage("A valid value is required");
		fields.add(comboBox);

		comboBox = createComboBox();
		comboBox.setEnabled(false);
		fields.add(comboBox);

		comboBox = createComboBox();
		comboBox.getElement().getThemeList().add("small");
		fields.add(comboBox);

		comboBox = createComboBox();
		comboBox.getElement().getThemeList().add("large");
		fields.add(comboBox);

		add(fields);
	}

	private ComboBox createComboBox() {
		ComboBox comboBox = new ComboBox("Combo box title");
		comboBox.setItems("Option 1", "Option 2", "Option 3", "Option 4");
		comboBox.setHelperText("Optional helper text");
		return comboBox;
	}
}
