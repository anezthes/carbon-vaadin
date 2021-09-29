package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Search")
@Route(value = "search", layout = MainLayout.class)
public class SearchView extends Main {

    public SearchView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Search"));
        createSearchFields();
    }

    private void createSearchFields() {
        /* Prefix */
        Div fields = new Div();
        fields.addClassNames("flex", "flex-wrap", "gap-m");

        TextField searchField = createSearchField(Slot.PREFIX);
        fields.add(searchField);

        searchField = createSearchField(Slot.PREFIX);
        searchField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        fields.add(searchField);

        searchField = createSearchField(Slot.PREFIX);
        searchField.addThemeName("large");
        fields.add(searchField);

        add(fields);

        /* Suffix */
        fields = new Div();
        fields.addClassNames("flex", "flex-wrap", "gap-m");

        searchField = createSearchField(Slot.SUFFIX);
        fields.add(searchField);

        searchField = createSearchField(Slot.SUFFIX);
        searchField.addThemeVariants(TextFieldVariant.LUMO_SMALL);
        fields.add(searchField);

        searchField = createSearchField(Slot.SUFFIX);
        searchField.addThemeName("large");
        fields.add(searchField);

        add(fields);
    }

    enum Slot {
        PREFIX, SUFFIX
    }

    private TextField createSearchField(Slot slot) {
        TextField textField = new TextField();
        if (slot.equals(Slot.PREFIX)) {
            textField.setPrefixComponent(VaadinIcon.SEARCH.create());
        } else {
            textField.setSuffixComponent(VaadinIcon.SEARCH.create());
        }
        textField.setPlaceholder("Search");
        textField.setClearButtonVisible(true);
        return textField;
    }
}
