package app.service.branch_panel.delivery_section;
import app.controllers.branch_panel.DynamicContentController;
import app.controllers.branch_panel.MainController;
import app.db.repo.RepositorySQL;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import app.service.SceneManager;

public class Delivery implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void goToAnotherView(String fxml) {
        if (mainController != null) {
            mainController.loadContent(fxml);
        }
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
        if(!SceneManager.isScene("report_product")){
            SceneManager.addScene("report_product");
        }
        goToAnotherView("reportPanel");
    }
}
