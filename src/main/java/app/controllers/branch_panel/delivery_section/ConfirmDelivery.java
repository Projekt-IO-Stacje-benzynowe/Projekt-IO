package app.controllers.branch_panel.delivery_section;

import app.controllers.branch_panel.MainController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import app.controllers.branch_panel.DynamicContentController;
import app.service.branch_panel.delivery_section.Delivery;
import app.service.SceneManager;


public class ConfirmDelivery implements DynamicContentController {
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
    private TextField deliveryID;
    @FXML
    public void confirmDelivery(ActionEvent event){
        Delivery Delivery = new Delivery();
        Delivery.Confirm(deliveryID.getText());
        deliveryID.clear();
    }   
    @FXML
    public void goBackButton(ActionEvent event){
        goToAnotherView("delivery_panel");
    }
}