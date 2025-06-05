package app;

import javafx.application.Application;

import javafx.stage.Stage;      
import app.service.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        SceneManager.setStage(primaryStage);
        SceneManager.addScene("login", "/view/login.fxml");
        SceneManager.addScene("branch", "/view/branch_panel/main_branch.fxml");

        SceneManager.showScene("login");
    }   
    public static void main(String[] args) {
        launch(args);
    }

}