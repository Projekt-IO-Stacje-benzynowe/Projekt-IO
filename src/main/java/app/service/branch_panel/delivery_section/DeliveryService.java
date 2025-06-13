package app.service.branch_panel.delivery_section;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.db.repo.RepositorySQL;
import javafx.beans.binding.When;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import app.service.SceneManager;

public class DeliveryService implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    public void Confirm(String deliveryID){
        int result = RepositorySQL.confirmDelivery(deliveryID);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);

        if(result >= 1){
            alert.setContentText("Zmieniono status dostawy na 'completed'. Czy chcesz zgłosić wadliwość jakiś produktów?");
            
            ButtonType okButton = new ButtonType("OK");
            ButtonType goToReportIssues = new ButtonType("Chcesz zgłosić błędy?");
            alert.getButtonTypes().setAll(okButton, goToReportIssues);

            Optional<ButtonType> resultButton = alert.showAndWait();


            // when we confirm our operation, an alert appears with a button that lets us go to the report panel.
            if (resultButton.isPresent() && resultButton.get() == goToReportIssues) {
                goToReportIssues();
            }
        } else {
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Nie znaleziono dostawy o podanym ID.");
            alert.showAndWait();
        }
    }
    private void goToReportIssues(){
        // SceneManager.addScene("report_product");
        mainController.showDynamicContent("report_product");
    }
}
