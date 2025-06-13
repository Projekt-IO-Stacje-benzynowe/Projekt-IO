package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.DeliveryModel;
import app.service.SceneManager;
import app.service.control_panel.logistics_coordinator_section.ChooseDeliveryService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ChooseDeliveryController implements DynamicContentController {
    private MainController mainController;

    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private StackPane tablePane;
    @FXML
    private BorderPane contentPane;

    private TableView<DeliveryModel> deliveriesTableView;

    public void initialize() {
        deliveriesTableView = ChooseDeliveryService.getDeliveriesForOutletView();
        tablePane.getChildren().add(deliveriesTableView);
    }

    public void goToModifyDelivery(ActionEvent event) {
        int result = ChooseDeliveryService.setSessionDelivery(deliveriesTableView.getSelectionModel().getSelectedItem());

        if (result == 0) {
            SceneManager.addScene("modify_delivery");
            mainController.showDynamicContent("modify_delivery");
        }
    }

    public void deleteDelivery(ActionEvent event) {
        DeliveryModel delivery = deliveriesTableView.getSelectionModel().getSelectedItem();
        int result = ChooseDeliveryService.deleteDelivery(delivery);
        if (result == 0) {
            deliveriesTableView.getItems().remove(delivery);
        }
    }
}
