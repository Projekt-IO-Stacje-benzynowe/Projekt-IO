package app.controllers.branch_panel;
import app.controllers.branch_panel.DynamicContentController;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import app.service.SceneManager;
import app.service.branch_panel.ReportProduct;

public class ReportIssuesProduct implements DynamicContentController {
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
    @FXML
    private TextField idProductField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField descField;
    @FXML
    private TextField dateField;

    @FXML
    void sendReportButton(){
        ReportProduct.report(idProductField, quantityField, descField, dateField);
        idProductField.clear();
        quantityField.clear();
        descField.clear();
        dateField.clear();
    }

    @FXML
    void goBackButton(ActionEvent event){
        goToAnotherView("menu_branch");
    }
}