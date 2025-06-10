package app.controllers.branch_panel.delivery_section;

import app.controllers.branch_panel.MainController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import app.controllers.branch_panel.DynamicContentController;
import app.service.SceneManager;

public class DeliveryMenuController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void goToAnotherView(String fxml) {
        if (mainController != null) {
            mainController.loadContent(fxml);
        }
    }
    @FXML
    public void confirmDelivery(ActionEvent event){
        SceneManager.addScene("confirm_delivery");
        goToAnotherView("confirm_delivery");
    }
    @FXML
    public void requestForAdditionalDelivery(ActionEvent event){
        SceneManager.addScene("additional_delivery");
       goToAnotherView("additional_delivery");
    }
    @FXML
    public void goBackButton(){
        goToAnotherView("menu_branch");
    }
}