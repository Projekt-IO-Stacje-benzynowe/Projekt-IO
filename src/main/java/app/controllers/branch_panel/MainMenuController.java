package app.controllers.branch_panel;

import javafx.fxml.FXML;
import app.controllers.SceneManager;
import javafx.event.ActionEvent;

public class MainMenuController {
    @FXML
    public void goToDiscountsButton(ActionEvent event){       
        // SceneManager.addScene("discount_menu", "/resources/view/branch_panel/discount_panel.fxml");
        SceneManager.addScene("discount_menu", "/view/branch_panel/discount_panel.fxml");
        SceneManager.showScene("discount_menu");
    }    
    @FXML
    public void goToDeliveryButton(ActionEvent event){
        SceneManager.addScene("delivery_menu", "/view/branch_panel/delivery_panel.fxml");
        // SceneManager.addScene("delivery_menu", "/resources/view/branch_panel/delivery_panel.fxml");
        SceneManager.showScene("delivery_menu");
    }
}
