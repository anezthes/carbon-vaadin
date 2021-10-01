package com.example.application.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Tabs")
@Route(value = "tabs", layout = MainLayout.class)
public class TabsView extends Main {

    private String OUTLINE = "outline";

    public TabsView() {
        addClassNames("flex", "flex-col", "pb-l", "px-l");

        add(new H2("Tabs"));
        createTabs();
    }

    private void createTabs() {
        Div div = new Div();
        div.addClassNames("flex", "flex-col", "gap-m");

        Tabs tabs = new Tabs(
                new Tab("Tab label 1"),
                new Tab("Tab label 2"),
                new Tab("Tab label 3")
        );
        tabs.setWidth("320px");
        div.add(tabs);

        tabs = new Tabs(
                createTab("Tab label 1", OUTLINE),
                createTab("Tab label 2", OUTLINE),
                createTab("Tab label 3", OUTLINE)
        );
        tabs.addThemeName(OUTLINE);
        tabs.setWidth("240px");
        div.add(tabs);

        add(div);
    }

    private Tab createTab(String text, String... themeNames) {
        Tab tab = new Tab(text);
        tab.addThemeNames(themeNames);
        return tab;
    }
}
