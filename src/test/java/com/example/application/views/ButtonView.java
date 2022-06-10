package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Button")
@Route(value = "button", layout = MainLayout.class)
public class ButtonView extends Main {

	public ButtonView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Button"));
		createButtons();
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

	enum ButtonType {
		REGULAR, ICON, ICON_ONLY, DISABLED
	}
}
