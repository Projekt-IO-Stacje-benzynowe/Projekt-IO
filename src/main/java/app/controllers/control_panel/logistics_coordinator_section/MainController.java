package app.controllers.control_panel.logistics_coordinator_section;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.*;

import java.io.IOException;

public class MainController {
    @FXML
    private AnchorPane mainContainer;
    @FXML
    public HBox topBar;
    @FXML
    public StackPane contentArea;

    @FXML
    private VBox sidebarContainer;

    public void initialize() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/control_panel/sidebar.fxml"));
            sidebarContainer = loader.load(); // Ten VBox jest root z sidebar.fxml

            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setMainController(this);
            }
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            // Dodaj do mainContainer na indeksie 1 (pod top, nad contentArea)
            mainContainer.getChildren().add(1, sidebarContainer);
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08)); // lub dowolna proporcja
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
