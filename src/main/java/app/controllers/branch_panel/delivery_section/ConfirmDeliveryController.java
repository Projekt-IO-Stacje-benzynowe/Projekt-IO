package app.controllers.branch_panel.delivery_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.service.branch_panel.delivery_section.DeliveryService;
import app.service.Dialogs;
import app.service.Alerts;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


/**
 *  Controller for confirming delivery in the branch panel.
 */
public class ConfirmDeliveryController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }
    @FXML
    private TextField deliveryID;
    @FXML
    public void confirmDelivery(ActionEvent event){
        DeliveryService Delivery = new DeliveryService();
        Delivery.Confirm(deliveryID.getText());
    
        if(Delivery.Confirm(deliveryID.getText())){
            if (Dialogs.askIfRepeat("Delivery status has been changed to 'completed'. Would you like to report any defective products?")) {
                goToReportIssues();
            }
        }else{
            Alerts.warnItemNotFound("delivery");;
        }
        deliveryID.clear();
    }   
    @FXML
    public void goBackButton(ActionEvent event){
        mainController.showDynamicContent("delivery_panel");
    }

    private void goToReportIssues(){
        mainController.showDynamicContent("report_product");
    }

    


}