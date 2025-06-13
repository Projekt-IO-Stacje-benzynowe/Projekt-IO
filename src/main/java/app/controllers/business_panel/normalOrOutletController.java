package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.service.SceneManager;
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

    public void printGeneralRaport(ActionEvent actionEvent) {

    }

    public void goToOutletPage(ActionEvent actionEvent) {

    }
}