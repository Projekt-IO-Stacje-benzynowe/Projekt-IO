package app.controllers.control_panel.logistics_coordinator_section;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane; // Zmiana z VBox na BorderPane
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    @FXML
    private StackPane mainContainer;
    @FXML
    public StackPane top;
    @FXML
    public StackPane contentArea;

    private VBox sidebarContainer; // <-- nie oznaczaj @FXML!

    public void initialize() {
        try {
            // Ładuj sidebar.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/control_panel/sidebar.fxml"));
            sidebarContainer = loader.load(); // Ten VBox jest root z sidebar.fxml
            // Przekaż referencję do MainController jeśli chcesz:
            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setMainController(this);
            }

            // Ustaw alignment i bindowanie rozmiaru
            StackPane.setAlignment(sidebarContainer, javafx.geometry.Pos.TOP_LEFT);
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(
                    Bindings.min(220, mainContainer.widthProperty().multiply(0.2))
            );
            // Dodaj do mainContainer na indeksie 1 (pod top, nad contentArea)
            mainContainer.getChildren().add(1, sidebarContainer);

            // Przykład: skalowanie top
            top.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.20)); // lub dowolna proporcja
            System.out.println("Sidebar minWidth: " + sidebarContainer.getMinWidth());
            System.out.println("Sidebar maxWidth: " + sidebarContainer.getMaxWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
