package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Text input")
@Route(value = "text-input", layout = MainLayout.class)
public class TextInputView extends Main {

    public TextInputView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Text input"));
        createTextFields();
    }

    private void createTextFields() {
        Div fields = new Div();
        fields.addClassNames("flex", "flex-wrap", "gap-m");

        TextField textField = createTextField();
        textField.setClearButtonVisible(true);
        fields.add(textField);

        textField = createTextField();
        textField.setEnabled(false);
        fields.add(textField);

        textField = createTextField();
        textField.setHelperText("");
        textField.setInvalid(true);
        textField.setErrorMessage("A valid value is required");
        fields.add(textField);

        textField = createTextField();
        textField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        fields.add(textField);

        textField = createTextField();
        textField.addThemeName("large");
        fields.add(textField);

        PasswordField passwordField = new PasswordField("Text input label");
        passwordField.setPlaceholder("Placeholder text");
        passwordField.setHelperText("Optional helper text");
        fields.add(passwordField);

        TextArea textArea = new TextArea("Text input label");
        textArea.setPlaceholder("Placeholder text");
        textArea.setHelperText("Optional helper text");
        textArea.setClearButtonVisible(true);
        fields.add(textArea);

        add(fields);
    }

    private TextField createTextField() {
        TextField textField = new TextField("Text input label");
        textField.setPlaceholder("Placeholder text");
        textField.setHelperText("Optional helper text");
        textField.setClearButtonVisible(true);
        return textField;
    }
}
