package app.controllers.branch_panel;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import app.service.Session;
import app.service.SceneManager;
public class MainMenuController implements Controller, DynamicContentController  {
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
    public void goToDeliverySectionButton(ActionEvent event){
        SceneManager.addScene("delivery_panel");
        goToAnotherView("delivery_panel");
    }
    @FXML
    public void goToShowPromotionsButton(ActionEvent event){
        SceneManager.addScene("promotions_table_scene");
        goToAnotherView("discount_panel_table_promotions");
    }      
    @FXML
    public void goToRewardsButton(ActionEvent event){
        SceneManager.addScene("rewards_table");
        goToAnotherView("tableRewards");
    }  
    @FXML
    public void goToReportIssues(ActionEvent event){
        SceneManager.addScene("report_product");
        goToAnotherView("reportPanel");
    }


}    