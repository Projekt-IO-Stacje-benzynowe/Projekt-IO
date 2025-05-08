package app.controllers.branch_panel.delivery_section;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import app.db.repo.RepositorySQL;

public class ConfirmDelivery{
    @FXML
    private TextField deliveryID;
    @FXML
    public void confirmDelivery(ActionEvent event){
        int result = RepositorySQL.confirmDelivery(deliveryID.getText());
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);
        
        if(result >= 1){
            alert.setContentText("Zmieniono status dostawy na 'completed'");
        } else {
            alert.setContentText("Nie znaleziono dostawy o podanym ID");
        }
        alert.showAndWait();
    }   
}