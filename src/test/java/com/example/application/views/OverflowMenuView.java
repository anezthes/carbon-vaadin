package com.example.application.views;

import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Overflow menu")
@Route(value = "overflow-menu", layout = MainLayout.class)
public class OverflowMenuView extends Main {

	public OverflowMenuView() {
		addClassNames("flex", "flex-col", "pb-l", "px-l");

		add(new H2("Overflow menu"));
		createOverflowMenu();
	}

	private void createOverflowMenu() {
		MenuBar menuBar = new MenuBar();
		menuBar.addThemeName("overflow-menu");

		MenuItem menuItem = menuBar.addItem(VaadinIcon.ELLIPSIS_DOTS_V.create());
		menuItem.getSubMenu().addItem("Option 1");
		menuItem.getSubMenu().addItem("Option 2 is an example of a really long string and how we recommend handling this");
		menuItem.getSubMenu().addItem("Option 3");
		menuItem.getSubMenu().addItem("Option 4");

		add(menuBar);
	}
}
