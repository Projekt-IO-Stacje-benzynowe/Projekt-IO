package app.controllers.business_panel;

import app.service.SceneManager;
import app.controllers.shared.MainController;
import app.controllers.shared.DynamicContentController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 *  Controller for the business panel button page.
 */
public class buttonPageController implements DynamicContentController{
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
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
        SceneManager.addScene("choose_product_business");
        mainController.showDynamicContent("choose_product_business");

    }
}