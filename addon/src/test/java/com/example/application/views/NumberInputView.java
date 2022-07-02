package com.example.application.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Number input")
@Route(value = "number-input", layout = MainLayout.class)
public class NumberInputView extends Main {

	public NumberInputView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Number input"));
		createNumberInput();
	}

	private void createNumberInput() {
		NumberField numberField = new NumberField("Number input label");
		numberField.setHasControls(true);
		numberField.setHelperText("Optional helper text");
		numberField.setMax(100);
		numberField.setMin(0);
		numberField.setStep(10);
		numberField.setValue(50.0);
		add(numberField);
	}
}
