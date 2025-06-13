package app.controllers.branch_panel.delivery_section;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import app.service.branch_panel.delivery_section.RequestDeliveryService;

public class AdditionalDeliveryController implements DynamicContentController {
    @FXML
    TextField productIDField;
    @FXML
    TextField quantityField;
    
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void sendRequest(){
        RequestDeliveryService.sendRequest(productIDField, quantityField);        
    }
    @FXML
    public void goBackButton(){
        mainController.showDynamicContent("delivery_panel");
    }

}