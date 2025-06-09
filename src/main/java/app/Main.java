// package app;

// import javafx.application.Application;
// import javafx.stage.Stage;
// import app.service.SceneManager;

// public class Main extends Application {
//     @Override
//     public void start(Stage primaryStage){
//         SceneManager.setStage(primaryStage);
//         SceneManager.addScene("branch");
//         SceneManager.addScene("login"); 

//         SceneManager.addScene("Main");

//         // SceneManager.showScene("Main");
//         // SceneManager.addScene("Main");
//         // SceneManager.addScene("Analysis", "/view/business_panel/Main.fxml"); // scena Main z Centrali po zalogowaniu
//         // SceneManager.showScene("login");
//     }
//     public static void main(String[] args) {
//         launch(args);
//     }

// }


package app;

import javafx.application.Application;
import javafx.stage.Stage;
import app.service.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        SceneManager.setStage(primaryStage);
        SceneManager.addScene("branch");
        SceneManager.addScene("login");
        // SceneManager.addScene("Main");
        // SceneManager.showScene("Main");

        SceneManager.showScene("login");

        //SceneManager.addScene("Analysis", "/view/business_panel/Main.fxml"); // scena Main z Centrali po zalogowaniu
        // trzeba dodać inne panele
       // SceneManager.showScene("login");
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