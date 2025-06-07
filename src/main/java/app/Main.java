package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import app.service.SceneManager;

import app.db.connection.MySQLConnection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        SceneManager.setStage(primaryStage);

        // resources w java, nie dziala dla mavena
        // SceneManager.addScene("branch", "/resources/view/branch_panel/main_menu.fxml");
        // SceneManager.addScene("login", "/resources/view/login.fxml");
        // SceneManager.addScene("discount", "/app/view/branch_panel/discount_panel.fxml")
        SceneManager.addScene("branch", "/view/branch_panel/main_branch.fxml");
        SceneManager.addScene("login", "/view/login.fxml");
        // SceneManager.addScene("Main", "/view/control_panel/Main.fxml"); // scena Main z Centrali po zalogowaniu
        // SceneManager.showScene("Main");
        //SceneManager.addScene("Analysis", "/view/business_panel/Main.fxml"); // scena Main z Centrali po zalogowaniu
        // trzeba dodać inne panele
        // SceneManager.addScene("screen1", "/view/Screen1.fxml");
        // SceneManager.addScene("screen2", "/view/Screen2.fxml");
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