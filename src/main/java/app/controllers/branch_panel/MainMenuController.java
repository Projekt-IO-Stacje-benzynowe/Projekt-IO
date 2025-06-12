package app.controllers.branch_panel;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import app.service.SceneManager;
public class MainMenuController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }

    @FXML
    public void goToDeliverySectionButton(ActionEvent event){
        SceneManager.addScene("delivery_panel");
        mainController.showDynamicContent(PanelList.getFXMLFile("delivery_panel"));
    }
    @FXML
    public void goToShowPromotionsButton(ActionEvent event){
        SceneManager.addScene("promotions_table_scene");
        mainController.showDynamicContent(PanelList.getFXMLFile("promotions_table_scene"));
    }      
    @FXML
    public void goToRewardsButton(ActionEvent event){
        SceneManager.addScene("rewards_table");
        mainController.showDynamicContent(PanelList.getFXMLFile("rewards_table"));
    }  
    @FXML
    public void goToReportIssues(ActionEvent event){
        SceneManager.addScene("report_product");
        mainController.showDynamicContent(PanelList.getFXMLFile("report_product"));
    }


}    