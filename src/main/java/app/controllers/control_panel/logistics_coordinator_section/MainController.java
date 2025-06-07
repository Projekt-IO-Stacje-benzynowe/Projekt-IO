package app.controllers.control_panel.logistics_coordinator_section;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.IOException;

public class MainController {
    public BorderPane background;
    public VBox sidebar;
    public StackPane imagePane;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    public HBox topBar;
    @FXML
    public BorderPane mainContent;
    @FXML
    private VBox sidebarContainer;
    @FXML
    private ImageView szczyp;
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
            mainContainer.getChildren().add(sidebarContainer);
            imagePane.prefWidthProperty().bind(mainContent.widthProperty());
            imagePane.prefHeightProperty().bind(mainContent.heightProperty());
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08)); // lub dowolna proporcja
            background.prefWidthProperty().bind(mainContainer.widthProperty());
            background.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebar.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            mainContent.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.7));
            szczyp.fitWidthProperty().bind(imagePane.widthProperty().multiply(0.95));
            szczyp.fitHeightProperty().bind(imagePane.heightProperty().multiply(0.95));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
