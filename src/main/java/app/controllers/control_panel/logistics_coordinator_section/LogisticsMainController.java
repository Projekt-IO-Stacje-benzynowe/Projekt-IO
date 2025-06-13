package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.OutletModel;
import app.service.SceneManager;
import app.service.control_panel.logistics_coordinator_section.LogisticsMainService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

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
    private TableView<OutletModel> outletsTableView;

    public void initialize() {
        outletsTableView = LogisticsMainService.getAllOutletsView();
        tablePane.getChildren().add(outletsTableView);
    }

    public void goToViewRequests(ActionEvent event) {
        int result = LogisticsMainService.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            SceneManager.addScene("view_requests");
            mainController.showDynamicContent("view_requests");
        }
    }

    public void goToPlanDelivery(ActionEvent event) throws IOException {
        int result = LogisticsMainService.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            SceneManager.addScene("plan_delivery");
            mainController.showDynamicContent("plan_delivery");
        }
    }

    public void goToChooseDelivery(ActionEvent event) {
        int result = LogisticsMainService.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            SceneManager.addScene("choose_delivery");
            mainController.showDynamicContent("choose_delivery");
        }
    }

}
