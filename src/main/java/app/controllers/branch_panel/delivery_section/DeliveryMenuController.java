package app.controllers.branch_panel.delivery_section;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import app.controllers.SceneManager;

public class DeliveryMenuController {
    @FXML
    private TextField deliveryID;

    @FXML
    public void confirmDelivery(ActionEvent event){
        SceneManager.addScene("confirm_delivery", "/resources/view/branch_panel/confirm_delivery.fxml");
        SceneManager.showScene("confirm_delivery");
    }
    @FXML
    public void requestForAdditionalDelivery(ActionEvent event){
        // SceneManager.addScene("request_additional_delivery", "/app/view/branch_panel/request_additional_delivery.fxml");
        // SceneManager.showScene("request_additional_delivery");
    }
}
// Przykład: komunikat informacyjny