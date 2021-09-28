package com.example.application.views;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "Fiber", shortName = "Fiber", enableInstallPrompt = false)
@Theme(themeFolder = "fiber")
@PageTitle("Main")
public class MainLayout extends AppLayout {

    public static class MenuItemInfo {

        private String text;
        private String iconClass;
        private Class<? extends Component> view;

        public MenuItemInfo(String text, String iconClass, Class<? extends Component> view) {
            this.text = text;
            this.iconClass = iconClass;
            this.view = view;
        }

        public String getText() {
            return text;
        }

        public String getIconClass() {
            return iconClass;
        }

        public Class<? extends Component> getView() {
            return view;
        }

    }

    public MainLayout() {
        setPrimarySection(Section.NAVBAR);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }

    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.getElement().setAttribute("aria-label", "Menu toggle");

        H1 title = new H1("Fiber");
        title.addClassNames("ms-xs", "my-0", "text-m");

        Header header = new Header(toggle, title);
        header.addClassNames("bg-base", "box-border", "flex", "h-l", "items-center",
                "w-full");
        header.getElement().getThemeList().add("dark");
        return header;
    }

    private Component createDrawerContent() {
        H2 appName = new H2("Components");
        appName.addClassNames("flex", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation());
        section.addClassNames("flex", "flex-col", "items-stretch", "max-h-full", "min-h-full");
        return section;
    }

    private Nav createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("border-b", "border-contrast-10", "flex-grow", "overflow-auto");
        nav.getElement().setAttribute("aria-labelledby", "views");

        H3 views = new H3("Views");
        views.addClassNames("flex", "h-m", "items-center", "mx-m", "my-0", "text-s", "text-tertiary");
        views.setId("views");

        for (RouterLink link : createLinks()) {
            nav.add(link);
        }
        return nav;
    }

    private List<RouterLink> createLinks() {
        MenuItemInfo[] menuItems = new MenuItemInfo[]{ //
                new MenuItemInfo("Accordion", "la la-angle-down", AccordionView.class),
                new MenuItemInfo("Button", "la la-plus", ButtonView.class),
                new MenuItemInfo("Checkbox", "la la-check", CheckboxView.class),
                new MenuItemInfo("Data table", "la la-table", DataTableView.class),
                new MenuItemInfo("Date picker", "la la-calendar", DatePickerView.class),
                new MenuItemInfo("Search", "la la-search", SearchView.class),
                new MenuItemInfo("Select", "la la-tasks", SelectView.class),
                new MenuItemInfo("Text input", "la la-terminal", TextInputView.class),
                new MenuItemInfo("Tag", "la la-tag", TagView.class),
        };
        List<RouterLink> links = new ArrayList<>();
        for (MenuItemInfo menuItemInfo : menuItems) {
            links.add(createLink(menuItemInfo));

        }
        return links;
    }

    private static RouterLink createLink(MenuItemInfo menuItemInfo) {
        RouterLink link = new RouterLink();
        link.addClassNames("flex", "h-s", "items-center", "px-m", "relative", "text-secondary");
        link.setRoute(menuItemInfo.getView());

        Span icon = new Span();
        icon.addClassNames("me-l", "text-l");
        if (!menuItemInfo.getIconClass().isEmpty()) {
            icon.addClassNames(menuItemInfo.getIconClass());
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("text-s");

        link.add(icon, text);
        return link;
    }
}
