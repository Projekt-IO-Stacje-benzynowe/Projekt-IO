package app.controllers.branch_panel;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
public class MainMenuController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    public void goToDeliverySectionButton(ActionEvent event){
        mainController.showDynamicContent("delivery_panel");
    }
    @FXML
    public void goToShowPromotionsButton(ActionEvent event){
        mainController.showDynamicContent("promotions_table");
    }      
    @FXML
    public void goToRewardsButton(ActionEvent event){
        mainController.showDynamicContent("rewards_table");
    }  
    @FXML
    public void goToReportIssues(ActionEvent event){
        mainController.showDynamicContent("report_product");
    }


}    