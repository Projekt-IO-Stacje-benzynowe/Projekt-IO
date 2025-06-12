package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.RequestModel;
import app.service.SceneManager;
import app.service.control_panel.logistics_coordinator_section.ModifyDeliveryService;
import app.service.control_panel.logistics_coordinator_section.ViewRequestsService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class ViewRequestsController implements DynamicContentController {
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

    private TableView<RequestModel> requestsTableView;

    public void initialize() {
        requestsTableView = ViewRequestsService.getRequests();
        tablePane.getChildren().add(requestsTableView);
    }

    public void goToApproveRequest(ActionEvent event) {
    }

    public void deleteRequest(ActionEvent event) {
    }

    
}
