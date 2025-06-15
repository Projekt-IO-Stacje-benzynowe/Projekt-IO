package app.controllers.shared;

import app.service.SceneManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.*;

/**
 *  MainController is responsible for managing the main layout of the application,
 *  including the sidebar and dynamic content area.
 *  It initializes the main components and allows for dynamic content switching.
 *  This controller is used to set up the main application window and handle interactions
 *  with the sidebar and main content area.
 */
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

    /**
     * Sets the panel to be displayed in the dynamic content area.
     * @param panelName
     */
    public void setPanel(String panelName) {
        SceneManager.setPanel(panelName);
    }

    public void initialize() {
        try {
            // Initialize the main controller and sidebar
            SceneManager.setMainController(this);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/shared/sidebar.fxml"));
            sidebarContainer = loader.load();

            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setMainController(this);
            }
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            // Add the sidebar to the mainContainer and set the layout
            mainContainer.getChildren().add(sidebarContainer);
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08));
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

    /**
     * Displays dynamic content in the main content area based on the provided name.
     * This method uses SceneManager to set the sub-panel and updates the center of the mainContent.
     * @param name The name of the content to be displayed.
     */
    public void showDynamicContent(String name) {
        Parent content = SceneManager.setSubPanel(this, name);
        mainContent.setCenter(content);
    }
}
