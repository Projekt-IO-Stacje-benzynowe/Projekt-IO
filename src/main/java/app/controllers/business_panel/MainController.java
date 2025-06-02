package app.controllers.business_panel;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    @FXML private BorderPane mainContainer; // Zmienione z VBox

    @FXML
    public void initialize() {
        try {
            loadSidebar();
            changeContent("/view/business_panel/home.fxml");
        } catch (IOException e) {
            System.err.println("Failed to load sidebar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadSidebar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/business_panel/sidebar.fxml"));
        VBox sidebar = loader.load(); // Zmienione z VBox na Parent

        SidebarController sidebarController = loader.getController();
        if (sidebarController != null) {
            sidebarController.setMainController(this);
        } else {
            System.err.println("SidebarController is null! Check FXML file configuration.");
        }
        Platform.runLater(()->{
        sidebar.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.15)); // skalowanie dopiero po załadowaniu main contentu
    });
        mainContainer.setLeft(sidebar);// Ustawienie sidebaru po lewej stronie BorderPane
    }
    public void changeContent(String fxmlPath) {
        try {
            Parent content = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainContainer.setCenter(content); // Ustawienie zawartości w centrum
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}