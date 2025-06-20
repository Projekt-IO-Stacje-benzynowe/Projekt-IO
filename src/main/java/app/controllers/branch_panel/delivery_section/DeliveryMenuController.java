package app.controllers.branch_panel.delivery_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

/**
 *  Controller for the delivery menu in the branch panel.
 */
public class DeliveryMenuController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void confirmDelivery(ActionEvent event){
        mainController.showDynamicContent("confirm_delivery");;
    }
    @FXML
    public void requestForAdditionalDelivery(ActionEvent event){
        mainController.showDynamicContent("additional_delivery");
    }
    @FXML
    public void goBackButton(){
        mainController.showDynamicContent("branch");
    }
}