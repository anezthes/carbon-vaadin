package com.example.application.views;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Date picker")
@Route(value = "date-picker", layout = MainLayout.class)
public class DatePickerView extends Main {

    public DatePickerView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Date picker"));
        createDatePickers();
    }

    private void createDatePickers() {
        Div fields = new Div();
        fields.addClassNames("flex", "flex-wrap", "gap-m");

        DatePicker datePicker = new DatePicker("Date picker label");
        datePicker.setPlaceholder("mm/dd/yyyy");
        fields.add(datePicker);

        datePicker = new DatePicker("Date picker label");
        datePicker.setPlaceholder("mm/dd/yyyy");
        datePicker.setEnabled(false);
        fields.add(datePicker);

        datePicker = new DatePicker("Date picker label");
        datePicker.setPlaceholder("mm/dd/yyyy");
        datePicker.getElement().getThemeList().add("small");
        fields.add(datePicker);

        datePicker = new DatePicker("Date picker label");
        datePicker.setPlaceholder("mm/dd/yyyy");
        datePicker.getElement().getThemeList().add("large");
        fields.add(datePicker);

        datePicker = new DatePicker("Date picker label");
        datePicker.setPlaceholder("mm/dd/yyyy");
        datePicker.setInvalid(true);
        datePicker.setErrorMessage("A valid value is required");
        fields.add(datePicker);

        add(fields);

        /*
        <vaadin-date-picker label="Start date"></vaadin-date-picker>
              <vaadin-date-picker label="Start date" value="2022-03-26" clear-button-visible></vaadin-date-picker>
              <vaadin-date-picker label="Start date"  placeholder="DD/MM/YYYY"  helper-text="Format: DD/MM/YYYY"></vaadin-date-picker>
          </div>
          <div>
              <vaadin-time-picker label="Start time"></vaadin-time-picker>
              <vaadin-time-picker label="Start time" value="12:30" clear-button-visible></vaadin-time-picker>
              <vaadin-time-picker label="Start time"  placeholder="HH:MM"  helper-text="Format: HH:MM"></vaadin-time-picker>
          </div>
          <div>
              <vaadin-date-time-picker label="Start date and time"></vaadin-date-time-picker>
              <vaadin-date-time-picker label="Start date and time" value="2022-03-26T12:30" clear-button-visible></vaadin-date-time-picker>
              <vaadin-date-time-picker label="Start date and time" date-placeholder="DD/MM/YYYY" time-placeholder="HH:MM" helper-text="Format: DD/MM/YYYY and HH:MM"></vaadin-date-time-picker>
         */
    }
}
