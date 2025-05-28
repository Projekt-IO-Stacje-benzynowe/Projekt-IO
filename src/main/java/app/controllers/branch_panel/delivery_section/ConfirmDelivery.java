package app.controllers.branch_panel.delivery_section;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import app.service.branch_panel.delivery_section.Delivery;

public class ConfirmDelivery{
    @FXML
    private TextField deliveryID;
    @FXML
    public void confirmDelivery(ActionEvent event){
        Delivery.Confirm(deliveryID.getText());
        
    }   
}