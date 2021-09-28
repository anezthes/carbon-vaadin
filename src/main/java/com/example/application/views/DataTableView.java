package com.example.application.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Data table")
@Route(value = "data-table", layout = MainLayout.class)
public class DataTableView extends Main {

    public DataTableView() {
        addClassNames("flex", "flex-col", "px-l");

        add(new H2("Data table"));
        createDataTables();
    }

    private void createDataTables() {
        Grid<LoadBalancer> grid = createGrid();
        add(grid);

        grid = createGrid();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        add(new H3("Stripes"), grid);

        grid = createGrid();
        grid.getColumns().forEach(column -> column.setSortable(true));
        add(new H3("Sortable"), grid);

        grid = createGrid();
        grid.addThemeVariants(GridVariant.LUMO_COMPACT);
        add(new H3("Compact"), grid);

        grid = createGrid();
        grid.addThemeName("short");
        add(new H3("Short"), grid);

        grid = createGrid();
        grid.addThemeName("tall");
        add(new H3("Tall"), grid);

        grid = createGrid();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        add(new H3("Multi-select"), grid);

        grid = createGrid();
        grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        add(new H3("Multi-select with stripes"), grid);
    }

    private Grid<LoadBalancer> createGrid() {
        Grid<LoadBalancer> grid = new Grid();
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.setAllRowsVisible(true);
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        grid.addColumn(LoadBalancer::getName).setHeader("Name");
        grid.addColumn(LoadBalancer::getProtocol).setHeader("Protocol");
        grid.addColumn(LoadBalancer::getPort).setHeader("Port");
        grid.addColumn(LoadBalancer::getRule).setHeader("Rule");
        grid.addColumn(LoadBalancer::getAttachedGroups).setHeader("Attached Groups");
        grid.addColumn(LoadBalancer::getStatus).setHeader("Status");

        grid.setItemDetailsRenderer(new ComponentRenderer<>(loadBalancer -> new Span("Aux squad rules")));

        grid.setItems(
                new LoadBalancer("Load Balance 3", "HTTP", 3000, "Round robin", "Kevins VM Groups", "Disabled"),
                new LoadBalancer("Load Balance 1", "HTTP", 443, "Round robin", "Maureens VM Groups", "Starting"),
                new LoadBalancer("Load Balance 2", "HTTP", 80, "DNS delegation", "Andrews VM Groups", "Active"),
                new LoadBalancer("Load Balance 6", "HTTP", 3000, "Round robin", "Marcs VM Groups", "Disabled"),
                new LoadBalancer("Load Balance 4", "HTTP", 443, "Round robin", "Mels VM Groups", "Starting"),
                new LoadBalancer("Load Balance 5", "HTTP", 80, "DNS delegation", "Ronjas VM Groups", "Active")
        );

        return grid;
    }

    /* Dummy class for demo purposes */
    private class LoadBalancer {
        private String name;
        private String protocol;
        private int port;
        private String rule;
        private String attachedGroups;
        private String status;

        public LoadBalancer(String name, String protocol, int port, String rule, String attachedGroups, String status) {
            this.name = name;
            this.protocol = protocol;
            this.port = port;
            this.rule = rule;
            this.attachedGroups = attachedGroups;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProtocol() {
            return protocol;
        }

        public void setProtocol(String protocol) {
            this.protocol = protocol;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getRule() {
            return rule;
        }

        public void setRule(String rule) {
            this.rule = rule;
        }

        public String getAttachedGroups() {
            return attachedGroups;
        }

        public void setAttachedGroups(String attachedGroups) {
            this.attachedGroups = attachedGroups;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
