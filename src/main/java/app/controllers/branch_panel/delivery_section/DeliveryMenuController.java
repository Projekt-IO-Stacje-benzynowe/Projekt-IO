package app.controllers.branch_panel.delivery_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import app.service.SceneManager;

public class DeliveryMenuController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }
    @FXML
    public void confirmDelivery(ActionEvent event){
        SceneManager.addScene("confirm_delivery");
        mainController.showDynamicContent("confirm_delivery");;
    }
    @FXML
    public void requestForAdditionalDelivery(ActionEvent event){
        SceneManager.addScene("additional_delivery");
        mainController.showDynamicContent("additional_delivery");
    }
    @FXML
    public void goBackButton(){
        SceneManager.addScene("menu_branch");
        mainController.showDynamicContent("branch");
    }
}