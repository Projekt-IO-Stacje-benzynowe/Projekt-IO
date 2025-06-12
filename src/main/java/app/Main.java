package app;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;

import app.service.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        // Setting the default locale to English to comply with the requirement
        // that the application should be in English
        // but also the weeek starts on Monday
        Locale.setDefault(Locale.UK);
        SceneManager.setStage(primaryStage);
        SceneManager.addScene("branch");
        SceneManager.addScene("login");
        SceneManager.showScene("login");


    }
    public static void main(String[] args) {
        launch(args);
    }  
}