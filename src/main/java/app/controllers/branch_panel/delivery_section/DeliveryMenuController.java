package app.controllers.branch_panel.delivery_section;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

import app.service.SceneManager;

public class DeliveryMenuController {
    @FXML
    public void confirmDelivery(ActionEvent event){
        SceneManager.addScene("confirm_delivery");
        SceneManager.showScene("confirm_delivery");
    }
    @FXML
    public void requestForAdditionalDelivery(ActionEvent event){
        SceneManager.addScene("additional_delivery");
        SceneManager.showScene("additional_delivery");
    }
    @FXML
    public void goBackButton(){
        SceneManager.showScene("branch");
    }
}