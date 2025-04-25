package com.example.hellofx.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SidebarController {
    private MainController mainController;

    @FXML
    private VBox Box1;

    @FXML
    private Button homeButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button logoutButton;

    @FXML
    private Label homeLabel;
    @FXML
    private Label settingsLabel;
    @FXML
    private Label logoutLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        System.out.println("SidebarController initialized!");

        // Sprawdzenie czy elementy są poprawnie wstrzyknięte
        if (Box1 == null) System.out.println("Sidebar VBox is null");
        if (homeButton == null) System.out.println("homeButton is null");
        if (settingsButton == null) System.out.println("settingsButton is null");
        if (logoutButton == null) System.out.println("logoutButton is null");

        // Inicjalizacja tylko jeśli elementy istnieją
        if (homeButton != null && homeLabel != null) {
            setupButtonHoverEffects(homeButton, homeLabel);
            homeButton.setOnAction(event -> handleHomeClick());
        }

        if (settingsButton != null && settingsLabel != null) {
            setupButtonHoverEffects(settingsButton, settingsLabel);
            settingsButton.setOnAction(e -> handleSettingsClick());
        }

        if (logoutButton != null && logoutLabel != null) {
            setupButtonHoverEffects(logoutButton, logoutLabel);
            logoutButton.setOnAction(e -> handleLogoutClick());
        }
    }

    private void setupButtonHoverEffects(Button button, Label label) {
        button.setOnMouseEntered(e -> label.setVisible(true));
        button.setOnMouseExited(e -> label.setVisible(false));
    }

    private void handleHomeClick() {
        System.out.println("Home button clicked");
        if (mainController != null) {
            mainController.changeContent("/com/example/hellofx/home.fxml");
        }
    }

    private void handleSettingsClick() {
        System.out.println("Settings button clicked");
        if (mainController != null) {
            mainController.changeContent("/com/example/hellofx/settings.fxml");
        }
    }

    private void handleLogoutClick() {
        System.out.println("Logout button clicked");
    }
}