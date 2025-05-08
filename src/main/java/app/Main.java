package app;

import javafx.application.Application;
import javafx.stage.Stage;
import app.controllers.SceneManager;
import app.db.connection.MSSQLConnection;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        String url = "jdbc:sqlserver://localhost:1433;databaseName=test;encrypt=true;trustServerCertificate=true;\n";
        String user = "SA";
        String password = "TwojeHaslo123!";
        
        MSSQLConnection.MakeConnection(url, user, password);

        SceneManager.setStage(primaryStage);
        SceneManager.addScene("branch", "/resources/view/branch_panel/main_menu.fxml");
        SceneManager.addScene("login", "/resources/view/login.fxml");
        // SceneManager.addScene("discount", "/app/view/branch_panel/discount_panel.fxml");

        // trzeba dodać inne panele         
        // SceneManager.addScene("screen1", "/view/Screen1.fxml");
        // SceneManager.addScene("screen2", "/view/Screen2.fxml");
        SceneManager.showScene("login");
    }
    public static void main(String[] args) {
        launch(args);
    }
}