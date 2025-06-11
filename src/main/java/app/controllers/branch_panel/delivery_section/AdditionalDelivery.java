package app.controllers.branch_panel.delivery_section;
import app.controllers.branch_panel.DynamicContentController;
import app.controllers.branch_panel.MainController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import app.service.SceneManager;
import javafx.fxml.FXML;

import app.service.branch_panel.delivery_section.RequestDelivery;

public class AdditionalDelivery implements DynamicContentController {
    @FXML
    TextField productIDField;
    @FXML
    TextField quantityField;
    @FXML
    TextField deliveryIDField;
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
    public void sendRequest(){
        RequestDelivery.sendRequest(productIDField, quantityField, deliveryIDField);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);
        alert.setContentText("Zgłoszono dostawę!");
        alert.showAndWait();        
    }
    @FXML
    public void goBackButton(){
        goToAnotherView("delivery_panel");
    }


}