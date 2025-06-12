package app.controllers.branch_panel.delivery_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import app.service.branch_panel.delivery_section.Delivery;


public class ConfirmDelivery implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }
    @FXML
    private TextField deliveryID;
    @FXML
    public void confirmDelivery(ActionEvent event){
        Delivery Delivery = new Delivery();
        Delivery.Confirm(deliveryID.getText());
        deliveryID.clear();
    }   
    @FXML
    public void goBackButton(ActionEvent event){
        mainController.showDynamicContent(PanelList.getFXMLFile("delivery_panel"));
    }
}