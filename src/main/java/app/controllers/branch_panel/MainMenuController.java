package app.controllers.branch_panel;

import javafx.fxml.FXML;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.WindowEvent;

import app.service.Session;
import app.service.branch_panel.ClientSimulation.Launcher;
import app.service.SceneManager;

import java.lang.Process;


public class MainMenuController {    
    @FXML
    private void initialize() {
        Launcher launcher = new Launcher();
        
        launcher.runServerTask();   
        
        try {
            Thread.sleep(500);
        } catch(Exception e){
            System.out.println("Error while freezing thread" + e);
        };

        launcher.runClientTask();
    }


    @FXML
    public void goToDeliverySectionButton(ActionEvent event){
        SceneManager.addScene("delivery_menu", "/view/branch_panel/delivery_panel.fxml");
        SceneManager.showScene("delivery_menu");
    }
    @FXML
    public void goToShowPromotionsButton(ActionEvent event){
        SceneManager.addScene("promotions_table_scene", "/view/branch_panel/discount_panel_table_promotions.fxml");
        SceneManager.showScene("promotions_table_scene");
    }      
    @FXML
    public void goToRewardsButton(ActionEvent event){
        SceneManager.addScene("delivery_menu", "/view/branch_panel/delivery_panel.fxml");
        SceneManager.showScene("delivery_menu");
    }   
    @FXML
    public void goToReportIssues(ActionEvent event){
        SceneManager.addScene("reportProduct", "/view/branch_panel/reportPanel.fxml");
        SceneManager.showScene("reportProduct");
    }   
    @FXML
    public void goToLogOutButton(ActionEvent event){
        Session.EndSession();
        SceneManager.Clear();
        SceneManager.addScene("login", "/view/login.fxml");
        SceneManager.showScene("login");
    }

}       
        