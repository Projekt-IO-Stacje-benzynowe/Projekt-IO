package app.service.branch_panel.delivery_section;

import app.service.TypeValidation;
import app.service.Session;
import app.db.repo.RepositorySQL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

// class used for validation data and add additional delivery

public class RequestDeliveryService {
    public static void sendRequest(TextField productID, TextField quantity){
        int prodID = TypeValidation.intValidation(productID.getText());
        int quan = TypeValidation.intValidation(quantity.getText());

        Alert alert = new Alert(AlertType.CONFIRMATION);        
        if(prodID == -1 || quan == -1){
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Wpisano błędne dane.");
            alert.showAndWait();

        }

        // check if exist RewardIDProd in table
        if(!RepositorySQL.doesRewardProductExist(prodID)){    
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Nie znaleziono takiego RewardProductID!");
            alert.showAndWait();
            return;
        }

        int newDelID = RepositorySQL.getMaxDeliveryId() + 1;
        RepositorySQL.sendRequestForDelivery(Session.user.getID(), newDelID, prodID, quan);

        alert.setTitle("Informacja");
        alert.setHeaderText(null);
        alert.setContentText("Zgłoszono dostawę!");
        alert.showAndWait();
    }
}
