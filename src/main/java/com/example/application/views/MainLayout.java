package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view is a top-level placeholder for other views.
 */
@PWA(name = "Fiber", shortName = "Fiber", enableInstallPrompt = false)
@Theme(themeFolder = "fiber")
@PageTitle("Fiber")
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
        H2 title = new H2("Components");
        title.addClassNames("flex", "flex-shrink-0", "items-center", "h-xl", "m-0", "px-m", "text-m");

        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(title,
                createNavigation());
        section.addClassNames("flex", "flex-col", "flex-shrink-0", "items-stretch", "max-h-full", "min-h-full");
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
                new MenuItemInfo("Checkbox", "la la-check-square", CheckboxView.class),
                new MenuItemInfo("Combo box", "la la-caret-down", ComboBoxView.class),
                new MenuItemInfo("Data table", "la la-table", DataTableView.class),
                new MenuItemInfo("Date picker", "la la-calendar", DatePickerView.class),
                new MenuItemInfo("Modal", "la la-credit-card", ModalView.class),
                new MenuItemInfo("Radio button", "la la-dot-circle", RadioButtonView.class),
                new MenuItemInfo("Search", "la la-search", SearchView.class),
                new MenuItemInfo("Select", "la la-tasks", SelectView.class),
                new MenuItemInfo("Tabs", "la la-database", TabsView.class),
                new MenuItemInfo("Tag", "la la-tag", TagView.class),
                new MenuItemInfo("Text input", "la la-terminal", TextInputView.class),
                new MenuItemInfo("Upload", "la la-upload", UploadView.class),
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
        if (menuItemInfo.getText().equals("Tabs")) {
            icon.getStyle().set("transform", "rotate(90deg)");
        }

        Span text = new Span(menuItemInfo.getText());
        text.addClassNames("text-s");

        link.add(icon, text);
        return link;
    }
}
