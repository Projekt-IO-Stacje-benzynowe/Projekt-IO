package app.controllers.branch_panel;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import app.service.SceneManager;
import app.service.branch_panel.ReportProduct;

public class ReportIssuesProduct {
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
        SceneManager.showScene("branch");
    }
}