package app.controllers.control_panel.logistics_coordinator_section;

import app.model.OutletModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.MainService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class MainController implements Controller {
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private BorderPane background;
    @FXML
    private HBox topBar;
    @FXML
    private VBox sidebar;
    @FXML
    private BorderPane mainContent;
    @FXML
    private StackPane tablePane;
    @FXML
    private Text messageText;
    @FXML
    private VBox sidebarContainer;
    private TableView<OutletModel> outletsTableView;
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/control_panel/sidebar.fxml"));
            sidebarContainer = loader.load();

            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setController(this);
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

            outletsTableView = MainService.getAllOutletsView();
            outletsTableView.setOnMouseClicked(e -> clickTable(e));
            tablePane.getChildren().add(outletsTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickTable(MouseEvent event) {
        String text = outletsTableView.getSelectionModel().getSelectedItem().getName();
        messageText.setText(text);
    }

    public void logOut(ActionEvent event) {
        Session.endSession();
        SceneManager.clear();
        SceneManager.addScene("login");
        SceneManager.showScene("login");
    }

    public void goToViewRequests(ActionEvent event) {
        messageText.setText("view requests?");
    }

    public void goToPlanDelivery(ActionEvent event) {

        if (outletsTableView.getSelectionModel().getSelectedItem() != null) {
            Session.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
            SceneManager.addScene("plan_delivery");
            SceneManager.showScene("plan_delivery");
        } else {
            messageText.setText("Please select an outlet to add a delivery to.");
        }
    }

    public void goToChooseDelivery(ActionEvent event) {
        if (outletsTableView.getSelectionModel().getSelectedItem() != null) {
            Session.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
            SceneManager.addScene("choose_delivery");
            SceneManager.showScene("choose_delivery");
        } else {
            messageText.setText("Please select an outlet to modify deliveries for.");
        }
    }
}
