package app.service.branch_panel.delivery_section;

import javafx.scene.control.TextField;

import app.service.TypeValidation;
import app.service.branch_panel.ClientSimulation.Parser;
import app.db.repo.RepositorySQL;

import app.service.Session;

import java.util.Map;


public class RequestDelivery {
    public static void sendRequest(TextField productID, TextField quantity, TextField deliveryID){
        int prodID = TypeValidation.intValidation(productID.getText());
        int quan = TypeValidation.intValidation(quantity.getText());
        int delID = TypeValidation.intValidation(deliveryID.getText());
        RepositorySQL.sendRequestForDelivery(Session.user.getID(), delID, prodID, quan);
        
    }
}
