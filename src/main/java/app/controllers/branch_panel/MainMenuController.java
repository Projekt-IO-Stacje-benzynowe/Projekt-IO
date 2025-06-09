package app.controllers.branch_panel;

import javafx.fxml.FXML;

import javafx.event.ActionEvent;
import app.service.Session;
import app.service.SceneManager;

public class MainMenuController {    
    // @FXML
    // private void initialize() {
    //     Launcher launcher = new Launcher();
        
        
    //     launcher.runServerTask();   
    //     try {
    //         Thread.sleep(500);
    //     } catch(Exception e){
    //         System.out.println("Error while freezing thread" + e);
    //     };

    //     launcher.runClientTask();
    // }
    
    @FXML
    public void goToDeliverySectionButton(ActionEvent event){
        SceneManager.addScene("delivery_menu");
        SceneManager.showScene("delivery_menu");
    }
    @FXML
    public void goToShowPromotionsButton(ActionEvent event){
        SceneManager.addScene("promotions_table_scene");
        SceneManager.showScene("promotions_table_scene");
    }      
    @FXML
    public void goToRewardsButton(ActionEvent event){
        SceneManager.addScene("rewards_table");
        SceneManager.showScene("rewards_table");
    }  
    @FXML
    public void goToReportIssues(ActionEvent event){
        SceneManager.addScene("reportProduct");
        SceneManager.showScene("reportProduct");
    }
    @FXML
    public void goToLogOutButton(ActionEvent event){
        Session.endSession();
        SceneManager.clear();
        SceneManager.addScene("login");
        SceneManager.showScene("login");
        SceneManager.addScene("branch");
    }   

}    