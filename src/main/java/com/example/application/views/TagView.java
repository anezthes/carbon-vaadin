package com.example.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;

@PageTitle("Tag")
@Route(value = "tag", layout = MainLayout.class)
public class TagView extends Main {

    public TagView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Tag"));
        createTags();
    }

    public enum Tag {
        RED("error"), MAGENTA("magenta"), PURPLE("purple"),
        BLUE(""), CYAN("cyan"), TEAL("teal"), GREEN("success"),
        GRAY("contrast"), COOL_GRAY("cool-gray"), WARM_GRAY("warm-gray");

        private String themeName;

        Tag(String themeName) {
            this.themeName = themeName;
        }

        public String getThemeName() {
            return themeName;
        }
    }

    private void createTags() {
        Div wrapper = new Div();
        wrapper.addClassNames("flex", "flex-col", "gap-m");

        // Default
        Div tags = new Div();
        tags.addClassNames("flex", "flex-wrap", "gap-m");
        for (Tag value : Tag.values()) {
            Span tag = createTag(StringUtils.capitalize(value.getThemeName().replace("-", " ")));
            tag.getElement().getThemeList().add(value.getThemeName());
            tags.add(tag);
        }
        wrapper.add(tags);

        // Filters
        tags = new Div();
        tags.addClassNames("flex", "flex-wrap", "gap-m");
        for (Tag value : Tag.values()) {
            Span tag = createTag(StringUtils.capitalize(value.getThemeName().replace("-", " ")));
            tag.getElement().getThemeList().add(value.getThemeName());
            Button button = new Button(VaadinIcon.CLOSE_SMALL.create());
            button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
            tag.add(button);
            tags.add(tag);
        }
        wrapper.add(tags);

        // "Disabled" tags
        tags = new Div();
        tags.addClassNames("flex", "flex-wrap", "gap-m");
        for (Tag value : Tag.values()) {
            Span tag = createTag(StringUtils.capitalize(value.getThemeName().replace("-", " ")));
            tag.getElement().getThemeList().add(value.getThemeName());
            tag.addClassNames("disabled");
            tags.add(tag);
        }
        wrapper.add(tags);

        add(wrapper);
    }

    private Span createTag(String text) {
        Span span = new Span(text.isEmpty() ? "Blue" : text);
        span.getElement().getThemeList().add("badge");
        return span;
    }
}
