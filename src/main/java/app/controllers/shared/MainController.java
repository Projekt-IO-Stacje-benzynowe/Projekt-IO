package app.controllers.shared;

import app.service.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;

public class MainController implements Controller {

    @FXML
    public AnchorPane mainContainer;
    @FXML
    private BorderPane background;
    @FXML
    private HBox topBar;
    @FXML
    private VBox sidebar;
    @FXML
    private BorderPane mainContent;
    @FXML
    private VBox sidebarContainer;

    public void setPanel(String panelName) {
        SceneManager.setPanel(panelName); //ustawiamy panel w dynamicznej zawartosci
    }

    public void initialize() {
        try {
            SceneManager.setMainController(this); // ustawiamy sceneManagerowi mainController sceny jako ten główny
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/shared/sidebar.fxml"));
            sidebarContainer = loader.load();

            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setMainController(this);
            }
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            // Dodaj do mainContainer na indeksie 1 (pod top, nad contentArea)
            mainContainer.getChildren().add(sidebarContainer);
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08)); // lub dowolna proporcja
            background.prefWidthProperty().bind(mainContainer.widthProperty());
            background.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebar.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            mainContent.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.7));
            SceneManager.setMainRoot(mainContent);
            System.out.println(mainContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showDynamicContent(String name) {
        Parent content = SceneManager.setSubPanel(this, name);
        mainContent.setCenter(content);
    }
}
