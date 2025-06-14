package app.controllers.business_panel;

import app.service.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import app.controllers.shared.MainController;
import app.controllers.shared.DynamicContentController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class buttonPageController implements DynamicContentController{
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
    }

    public void goToProductPage(ActionEvent actionEvent) {
        SceneManager.addScene("ProductPage");
        mainController.showDynamicContent("ProductPage");
    }

    public void goToRewardPage(ActionEvent actionEvent) {
        SceneManager.addScene("rewardsBusiness");
        mainController.showDynamicContent("rewardsBusiness");

    }
}