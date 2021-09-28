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

        add(new H2("Checkboxes"));
        createCheckboxes();
    }

    private void createCheckboxes() {
        Div checkboxGroups = new Div();
        checkboxGroups.addClassNames("flex", "flex-col");

        CheckboxGroup checkboxGroup = new CheckboxGroup();
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        checkboxGroup.setItems("Checkbox label 1", "Checkbox label 2");
        checkboxGroup.setLabel("Checkbox heading");
        checkboxGroups.add(checkboxGroup);

        checkboxGroup = new CheckboxGroup();
        checkboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        checkboxGroup.setItems("Checkbox label 1", "Checkbox label 2");
        checkboxGroup.setLabel("Checkbox heading");
        checkboxGroup.setEnabled(false);
        checkboxGroups.add(checkboxGroup);

        add(checkboxGroups);
    }
}
