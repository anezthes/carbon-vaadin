package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Radio button")
@Route(value = "radio-button", layout = MainLayout.class)
public class RadioButtonView extends Main {

	public RadioButtonView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Radio button"));
		createRadioButtonGroups();
	}

	private void createRadioButtonGroups() {
		Div radioButtonGroups = new Div();
		radioButtonGroups.addClassNames("flex", "flex-col");

		RadioButtonGroup radioButtonGroup = createRadioButtonGroup();
		radioButtonGroups.add(radioButtonGroup);

		radioButtonGroup = createRadioButtonGroup();
		radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
		radioButtonGroups.add(radioButtonGroup);

		radioButtonGroup = createRadioButtonGroup();
		radioButtonGroup.setEnabled(false);
		radioButtonGroups.add(radioButtonGroup);

		radioButtonGroup = createRadioButtonGroup();
		radioButtonGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
		radioButtonGroup.setEnabled(false);
		radioButtonGroups.add(radioButtonGroup);

		add(radioButtonGroups);
	}

	private RadioButtonGroup createRadioButtonGroup() {
		RadioButtonGroup radioButtonGroup = new RadioButtonGroup();
		radioButtonGroup.setItems("Radio button label 1", "Radio button label 2");
		radioButtonGroup.setLabel("Radio button heading");
		return radioButtonGroup;
	}
}
