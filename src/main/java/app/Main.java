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

        //SceneManager.addScene("Analysis", "/view/business_panel/Main.fxml"); // scena Main z Centrali po zalogowaniu
        // trzeba dodać inne panele
        SceneManager.showScene("login");


    }
    public static void main(String[] args) {
        launch(args);
    }
    // @Override
    // public void start(Stage primaryStage) throws Exception {
    
    //     // MySQLConnection.makeConnection();
    //     // Parent root = FXMLLoader.load(getClass().getResource("/view/branch_panel/main_menu.fxml"));
    //     // Scene scene = new Scene(root, 800, 600);  // rozmiar okna

    //     // primaryStage.setTitle("Moja aplikacja JavaFX");
    //     // primaryStage.setScene(scene);
    //     // primaryStage.show();  // <--- uruchamia okno
    // }

    // public static void main(String[] args) {
    //     launch(args);
    // }    
}