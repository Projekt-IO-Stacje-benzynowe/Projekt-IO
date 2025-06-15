package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.service.SceneManager;
import app.service.Session;
import app.service.business_panel.raportChoice;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class normalOrOutletController implements DynamicContentController{
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
    }

    public void printGeneralRaport(ActionEvent actionEvent) throws Exception {
        raportChoice.generate(1, 0, Session.getProduct().getID());
        Session.clearNonUserData();
        SceneManager.clear();
        mainController.showDynamicContent("Analysis");
    }

    public void goToOutletPage(ActionEvent actionEvent) {
        SceneManager.addScene("allOutlets");
        mainController.showDynamicContent("allOutlets");
    }
}