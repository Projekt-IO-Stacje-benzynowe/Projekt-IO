package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.ChooseDeliveryService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

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
    @FXML
    private Text messageText;

    private TableView<DeliveryModel> deliveriesTableView;

    public void initialize() {
        deliveriesTableView = ChooseDeliveryService.getDeliveriesForOutletView();
        deliveriesTableView.setOnMouseClicked(e -> clickTable(e));
        tablePane.getChildren().add(deliveriesTableView);
    }

    public void clickTable(MouseEvent event) {
        String text = deliveriesTableView.getSelectionModel().getSelectedItem().getRewardName();
        messageText.setText(text);
    }

    public void goBack(ActionEvent event) {
        Session.setOutletNull();
        mainController.showDynamicContent("logistics_main");
        SceneManager.clearScene("choose_delivery");
    }

    public void goToModifyDelivery(ActionEvent event) {
        if (deliveriesTableView.getSelectionModel().getSelectedItem() != null) {
            ChooseDeliveryService.setSessionDelivery(deliveriesTableView.getSelectionModel().getSelectedItem());
            SceneManager.showScene("modify_delivery");
        } else {
            messageText.setText("Please select a delivery to modify.");
        }
    }

    public void deleteDelivery(ActionEvent event) {
        DeliveryModel delivery = deliveriesTableView.getSelectionModel().getSelectedItem();
        if (delivery != null) {
            ChooseDeliveryService.deleteDelivery(delivery);
        } else {
            messageText.setText("Please select a delivery to modify.");
        }
    }
}
