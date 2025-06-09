package app.controllers.control_panel.logistics_coordinator_section;

import app.model.DeliveryModel;
import app.model.OutletModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.ChooseDeliveryService;
import app.service.control_panel.logistics_coordinator_section.MainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ChooseDeliveryController implements Controller {
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
    private TableView<DeliveryModel> deliveriesTableView;

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
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            // Dodaj do mainContainer na indeksie 1 (pod top, nad contentArea)
            mainContainer.getChildren().add(sidebarContainer);
            topBar.prefHeightProperty().bind(mainContainer.heightProperty().multiply(0.08)); // lub dowolna proporcja
            background.prefWidthProperty().bind(mainContainer.widthProperty());
            background.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebar.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            mainContent.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.7));

            deliveriesTableView = ChooseDeliveryService.getDeliveriesForOutletView(Session.getOutlet().getID());
            deliveriesTableView.setOnMouseClicked(e -> clickTable(e));
            tablePane.getChildren().add(deliveriesTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickTable(MouseEvent event) {
        String text = deliveriesTableView.getSelectionModel().getSelectedItem().getRewardName();
        messageText.setText(text);
    }

    public void goBack(ActionEvent event) {
        Session.setOutletNull();
        SceneManager.showScene("Main");
        SceneManager.clearScene("choose_delivery");
    }

    public void goToModifyDelivery(ActionEvent event) {
        if (deliveriesTableView.getSelectionModel().getSelectedItem() != null) {
            Session.setDelivery(deliveriesTableView.getSelectionModel().getSelectedItem());
            SceneManager.showScene("modify_delivery");
        } else {
            messageText.setText("Please select a delivery to modify.");
        }
    }
}
