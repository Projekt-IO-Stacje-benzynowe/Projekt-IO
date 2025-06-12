package app.controllers.branch_panel.delivery_section;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;

import app.service.branch_panel.delivery_section.RequestDelivery;

public class AdditionalDelivery implements DynamicContentController {
    @FXML
    TextField productIDField;
    @FXML
    TextField quantityField;
    @FXML
    TextField deliveryIDField;
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }
    @FXML
    public void sendRequest(){
        RequestDelivery.sendRequest(productIDField, quantityField, deliveryIDField);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);
        alert.setContentText("Zgłoszono dostawę!");
        alert.showAndWait();        
    }
    @FXML
    public void goBackButton(){
        mainController.showDynamicContent(PanelList.getFXMLFile("delivery_panel"));
    }


}