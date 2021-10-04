package com.example.application.views;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Accordion")
@Route(value = "", layout = MainLayout.class)
public class AccordionView extends Main {

	private String ACCORDION_PARAGRAPH = "The accordion component delivers large amounts of content in a small space through progressive disclosure. The user gets key details about the underlying content and can choose to expand that content within the constraints of the accordion. Accordions work especially well on mobile interfaces or whenever vertical space is at a premium.";

	public AccordionView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Accordion"));
		createAccordion();
	}

	private void createAccordion() {
		Accordion accordion = new Accordion();
		for (int i = 0; i < 3; i++) {
			Paragraph paragraph = new Paragraph(ACCORDION_PARAGRAPH);
			paragraph.addClassNames("m-0", "text-s");
			AccordionPanel panel = accordion.add("Title " + (i + 1), paragraph);
			if (i == 1) {
				panel.addThemeVariants(DetailsVariant.REVERSE);
			}
			if (i == 2) {
				panel.setEnabled(false);
			}
		}
		add(accordion);
	}

	private Div createButtons(ButtonType buttonType) {
		Div buttons = new Div();
		buttons.addClassNames("flex", "flex-wrap", "gap-m");

		for (ButtonVariant variant : ButtonVariant.values()) {
			Button button = null;

			if (!buttonType.equals(ButtonType.ICON_ONLY)) {
				button = new Button(StringUtils.capitalize(variant.getVariantName()));
			}
			if (buttonType.equals(ButtonType.ICON)) {
				button.setIconAfterText(true);
				button.setIcon(VaadinIcon.PLUS.create());
			}
			if (buttonType.equals(ButtonType.ICON_ONLY)) {
				button = new Button(VaadinIcon.PLUS.create());
			}
			if (buttonType.equals(ButtonType.DISABLED)) {
				button.setEnabled(false);
			}
			if (variant.equals(ButtonVariant.MATERIAL_CONTAINED)
					|| variant.equals(ButtonVariant.MATERIAL_OUTLINED)
					|| variant.equals(ButtonVariant.LUMO_ICON)
					|| variant.equals(ButtonVariant.LUMO_SUCCESS)
					|| variant.equals(ButtonVariant.LUMO_TERTIARY_INLINE)) {
				// Skip
			} else {
				button.addThemeVariants(variant);
				buttons.add(button);
			}
		}
		return buttons;
	}

	private void createButtons() {
		Div buttons = new Div();
		buttons.addClassNames("flex", "flex-col");
		buttons.add(
				createButtons(ButtonType.REGULAR),
				createButtons(ButtonType.ICON),
				createButtons(ButtonType.ICON_ONLY),
				createButtons(ButtonType.DISABLED)
		);
		add(buttons);
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

	enum ButtonType {
		REGULAR, ICON, ICON_ONLY, DISABLED
	}
}
