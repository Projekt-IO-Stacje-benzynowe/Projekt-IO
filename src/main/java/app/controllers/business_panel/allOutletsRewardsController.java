package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.OutletModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.business_panel.outletsMainService;
import app.service.business_panel.raportChoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;


public class allOutletsRewardsController implements DynamicContentController {
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
        outletsTableView = outletsMainService.getAllOutletsView();
        tablePane.getChildren().add(outletsTableView);
    }

    public void goToChooseOutlet(ActionEvent actionEvent) {
        int result = outletsMainService.setOutlet(outletsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            SceneManager.clear();
            raportChoice.generate(2, Session.getOutlet().getID(), 0);
            Session.clearNonUserData();
            mainController.showDynamicContent("Analysis");
        }

    }
}
