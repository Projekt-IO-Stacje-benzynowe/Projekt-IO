package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.OutletModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.business_panel.OutletsMainService;
import app.service.business_panel.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 *  Controller for managing all outlets in the business panel.
 */
public class allOutletsController implements DynamicContentController {
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
        outletsTableView = OutletsMainService.getAllOutletsView();
        tablePane.getChildren().add(outletsTableView);
    }

    public void goToChooseOutlet(ActionEvent actionEvent) {
        int result = OutletsMainService.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            SceneManager.clear();
            raportChoice.generate(1, Session.getOutlet().getID(), Session.getProduct().getID());
            mainController.showDynamicContent("Analysis");
        }

    }
}
