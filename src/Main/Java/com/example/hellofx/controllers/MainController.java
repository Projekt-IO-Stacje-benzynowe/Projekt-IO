package com.example.hellofx.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

public class MainController {

    @FXML
    private BorderPane mainContainer; // Odpowiada głównemu BorderPane z main.fxml

    @FXML
    private VBox sidebarContainer; // Kontener dla sidebar

    @FXML
    public void initialize() {
        loadSidebar();
    }

    private void loadSidebar() {
        try {
            FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("/com/example/hellofx/sidebar.fxml"));
            VBox sidebar = sidebarLoader.load();

            SidebarController sidebarController = sidebarLoader.getController();
            sidebarController.setMainController(this);

            sidebarContainer.getChildren().setAll(sidebar);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Metoda do zmiany zawartości centralnej
    public void changeContent(String fxmlPath) {
        try {
            // Tutaj dodaj kod do zmiany zawartości w centralnym obszarze
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}