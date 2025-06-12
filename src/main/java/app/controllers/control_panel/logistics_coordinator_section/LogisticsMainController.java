package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.OutletModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.LogisticsMainService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.io.IOException;


public class LogisticsMainController implements DynamicContentController {
    private MainController mainController;

    @Override
    public void setMainController(MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }

    @FXML
    private StackPane tablePane;
    @FXML
    private BorderPane contentPane;
    @FXML
    private Text messageText;
    private TableView<OutletModel> outletsTableView;

    public void initialize() {
        outletsTableView = LogisticsMainService.getAllOutletsView();
        outletsTableView.setOnMouseClicked(e -> clickTable(e));
        tablePane.getChildren().add(outletsTableView);
    }

    public void clickTable(MouseEvent event) {
        String text = outletsTableView.getSelectionModel().getSelectedItem().getName();
        messageText.setText(text);
    }

    public void goToViewRequests(ActionEvent event) {
        if (outletsTableView.getSelectionModel().getSelectedItem() != null) {
            Session.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
            SceneManager.addScene("view_requests");
            mainController.showDynamicContent("view_requests");

        } else {
            messageText.setText("Please select an outlet to view requests from.");
        }
    }

    public void goToPlanDelivery(ActionEvent event) throws IOException {

        if (outletsTableView.getSelectionModel().getSelectedItem() != null) {
            Session.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
            SceneManager.addScene("plan_delivery");
            mainController.showDynamicContent("plan_delivery");

        } else {
            messageText.setText("Please select an outlet to add a delivery to.");
        }
    }

    public void goToChooseDelivery(ActionEvent event) {
        if (outletsTableView.getSelectionModel().getSelectedItem() != null) {
            Session.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
            SceneManager.addScene("choose_delivery");
            mainController.showDynamicContent("choose_delivery");
        } else {
            messageText.setText("Please select an outlet to modify deliveries for.");
        }
    }

}
