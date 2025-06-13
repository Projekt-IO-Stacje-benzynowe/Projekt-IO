package app.controllers.branch_panel.delivery_section;

import java.util.Optional;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import app.service.branch_panel.delivery_section.DeliveryService;


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
    
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);

        if(Delivery.Confirm(deliveryID.getText())){
            alert.setContentText("Zmieniono status dostawy na 'completed'. Czy chcesz zgłosić wadliwość jakiś produktów?");

            ButtonType okButton = new ButtonType("OK");
            ButtonType goToReportIssues = new ButtonType("Chcesz zgłosić błędy?");
            alert.getButtonTypes().setAll(okButton, goToReportIssues);

            Optional<ButtonType> resultButton = alert.showAndWait();

            // when we confirm our operation, an alert appears with a button that lets us go to the report panel.
            if (resultButton.isPresent() && resultButton.get() == goToReportIssues) {
                goToReportIssues();
            }
        }else{
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Nie znaleziono dostawy o podanym ID.");
            alert.showAndWait();
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