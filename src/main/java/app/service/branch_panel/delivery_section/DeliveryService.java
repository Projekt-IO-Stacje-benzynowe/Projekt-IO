package app.service.branch_panel.delivery_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.db.repo.RepositorySQL;

/**
 *  Service for handling delivery-related operations in the branch panel.
 */
public class DeliveryService implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    public boolean Confirm(String deliveryID){
        int result = RepositorySQL.confirmDelivery(deliveryID);

        if(result >= 1){
            return true;
        }
        return false;
    }
}
