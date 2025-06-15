package app.service.branch_panel.delivery_section;

import app.service.TypeValidation;
import app.service.Session;
import app.db.repo.RepositorySQL;
import javafx.scene.control.TextField;

import app.service.Alerts;
// class used for validation data and add additional delivery

public class RequestDeliveryService  {
    public static void sendRequest(TextField productID, TextField quantity){
        int prodID = TypeValidation.intValidation(productID.getText());
        int quan = TypeValidation.intValidation(quantity.getText());

        if(prodID == -1){
            Alerts.warnInvalidInput("productID");
            productID.clear();
            return;
        }
        if(quan == -1){
            Alerts.warnInvalidInput("quantity");
            quantity.clear();
            return;
        }
        // check if exist RewardIDProd in table
        if(!RepositorySQL.doesRewardProductExist(prodID)){    
            Alerts.warnItemNotFound("ProductID");
            return;
        }
        int newDelID = RepositorySQL.getMaxDeliveryId() + 1;
        RepositorySQL.sendRequestForDelivery(Session.getUser().getID(), newDelID, prodID, quan);

        Alerts.infoAlert("Information", "Delivery has been reported!");
    }
}
