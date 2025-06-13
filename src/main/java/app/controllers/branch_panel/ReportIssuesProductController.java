package app.controllers.branch_panel;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;

import app.service.branch_panel.ReportProduct;

public class ReportIssuesProductController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
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
        mainController.showDynamicContent("branch");
    }
}