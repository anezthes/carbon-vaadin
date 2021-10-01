package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Modal")
@Route(value = "modal", layout = MainLayout.class)
public class ModalView extends Main {

    private String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse cursus fermentum risus, sit amet fringilla nunc pellentesque quis. Duis quis odio ultrices, cursus lacus ac, posuere felis. Donec dignissim libero in augue mattis, a molestie metus vestibulum. Aliquam placerat felis ultrices lorem condimentum, nec ullamcorper felis porttitor.";

    public ModalView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Modal"));
        createModals();
    }

    private void createModals() {
        Button button = new Button("Launch modal");
        button.addClassName("self-start");
        button.addClickListener(event -> createDialog(false).open());
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(button);

        button = new Button("Launch modal with scrolling content");
        button.addClassName("self-start");
        button.addClickListener(event -> createDialog(true).open());
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        add(button);
    }

    private Dialog createDialog(boolean scrollable) {
        Dialog dialog = new Dialog();
        dialog.getElement().getThemeList().add("no-padding");

        /* Header */
        H2 label = new H2("Label");
        label.addClassNames("font-normal", "m-0", "text-secondary", "text-xs");

        H3 heading = new H3("Modal heading");
        heading.addClassNames("font-normal", "m-0", "text-xl");

        Button close = new Button(VaadinIcon.CLOSE_SMALL.create());
        close.addClickListener(e -> dialog.close());
        close.addThemeVariants(ButtonVariant.LUMO_LARGE);
        close.addClassNames("absolute", "m-0", "text-body");
        close.getStyle().set("right", "0");
        close.getStyle().set("top", "0");

        Div header = new Div(label, heading, close);
        header.addClassNames("flex", "flex-col", "gap-xs", "pb-xs", "pt-m", "px-m", "relative");

        /* Content */
        Div content = new Div(createParagraph());
        content.addClassNames("flex-grow", "mb-xl", "pt-xs", "px-m");
        if (scrollable) {
            content.addClassNames("overflow-auto");
            for (int i = 0; i < 10; i++) {
                content.add(createParagraph());
            }
        }

        /* Buttons */
        Button cancel = new Button("Cancel");
        cancel.addClickListener(e -> dialog.close());
        cancel.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        for (Button button : new Button[] {cancel, save}) {
            button.addClassNames("leading-none", "flex-grow", "h-auto", "justify-start", "m-0", "pb-xl", "pe-xl", "pt-m");
            button.setMaxWidth("12.25rem");
        }

        Div buttons = new Div(cancel, save);
        buttons.addClassNames("flex", "justify-end");

        /* Wrapper: needed for scrolling */
        Div wrapper = new Div(header, content, buttons);
        wrapper.addClassNames("flex", "flex-col");
        wrapper.setHeightFull();

        dialog.add(wrapper);
        return dialog;
    }

    private Paragraph createParagraph() {
        Paragraph paragraph = new Paragraph(LOREM_IPSUM);
        paragraph.addClassNames("m-0", "text-s");
        return paragraph;
    }
}
