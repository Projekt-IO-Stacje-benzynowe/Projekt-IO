package app.controllers.branch_panel;

import javafx.fxml.FXML;
import app.service.SceneManager;
import javafx.event.ActionEvent;

public class MainMenuController {
    @FXML
    public void goToDiscountsButton(ActionEvent event){       
        SceneManager.addScene("discount_menu", "/view/branch_panel/discount_panel.fxml");
        SceneManager.showScene("discount_menu");
    }    
    @FXML
    public void goToDeliveryButton(ActionEvent event){
        SceneManager.addScene("delivery_menu", "/view/branch_panel/delivery_panel.fxml");
        SceneManager.showScene("delivery_menu");
    }   
    @FXML
    public void goToQuestionnaireButton(ActionEvent event){
        SceneManager.addScene("questionnaire", "/view/questionnaire.fxml");
        // SceneManager.addScene("questionnaire", "/view/branch_panel/questionnaire.fxml");
        SceneManager.showScene("questionnaire");
    }      
}       
        