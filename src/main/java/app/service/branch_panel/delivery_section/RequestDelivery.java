package app.service.branch_panel.delivery_section;

import app.service.TypeValidation;
import app.service.Session;
import app.db.repo.RepositorySQL;

import javafx.scene.control.TextField;

public class RequestDelivery {
    public static void sendRequest(TextField productID, TextField quantity, TextField deliveryID){
        int prodID = TypeValidation.intValidation(productID.getText());
        int quan = TypeValidation.intValidation(quantity.getText());
        int delID = TypeValidation.intValidation(deliveryID.getText());
        RepositorySQL.sendRequestForDelivery(Session.getUser().getID(), delID, prodID, quan);
        
    }
}
