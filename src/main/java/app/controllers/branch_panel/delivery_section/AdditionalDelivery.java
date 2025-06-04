package app.controllers.branch_panel.delivery_section;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import app.service.SceneManager;
import javafx.fxml.FXML;

import app.service.branch_panel.delivery_section.RequestDelivery;

public class AdditionalDelivery {
    @FXML
    TextField productIDField;
    @FXML
    TextField quantityField;
    @FXML
    TextField deliveryIDField;
    
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
        SceneManager.showScene("delivery_menu");
    }


}