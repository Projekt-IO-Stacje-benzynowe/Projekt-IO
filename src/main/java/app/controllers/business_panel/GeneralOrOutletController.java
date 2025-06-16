package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.service.SceneManager;
import app.service.Session;
import app.service.business_panel.raportChoice;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *  Controller for managing general reports or outlet-specific actions in the business panel.
 */
public class GeneralOrOutletController implements DynamicContentController{
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
    }

    public void printGeneralRaport(ActionEvent actionEvent) throws Exception {
        raportChoice.generate(2, 0, 0);
        Session.clearNonUserData();
        SceneManager.clear();
        mainController.showDynamicContent("Analysis");
    }

    public void goToOutletPage(ActionEvent actionEvent) {
        SceneManager.addScene("allOutletsRewards");
        mainController.showDynamicContent("allOutletsRewards");
    }
}