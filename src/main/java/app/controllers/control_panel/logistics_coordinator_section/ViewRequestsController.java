package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.RequestModel;
import app.service.SceneManager;
import app.service.control_panel.logistics_coordinator_section.ViewRequestsService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Controller for viewing and managing requests in the logistics coordinator section of the control panel.
 * This controller allows the user to view, approve, and delete requests.
 */
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
        boolean result = ViewRequestsService.setSessionRequest(requestsTableView.getSelectionModel().getSelectedItem());
        if (result) {
            mainController.showDynamicContent("modify_delivery");
            SceneManager.clearScene("view_requests");
        }
    }

    /**
     * Deletes the selected request from the table and the database.
     * If the request is successfully deleted, it will be removed from the table view.
     * @param event
     */
    public void deleteRequest(ActionEvent event) {
        RequestModel request = requestsTableView.getSelectionModel().getSelectedItem();
        int result = ViewRequestsService.deleteRequest(request);
        if (result > 0) {
            requestsTableView.getItems().remove(request);
        }
        if (result == 0) {
            ViewRequestsService.clearNonUserData();
            mainController.showDynamicContent("logistics_main");
            SceneManager.clearScene("view_requests");
        }
    }

    
}
