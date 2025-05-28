package app.service.branch_panel.delivery_section;

import app.db.repo.RepositorySQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Delivery {
    
    public static void Confirm(String deliveryID){
        int result = RepositorySQL.confirmDelivery(deliveryID);
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
