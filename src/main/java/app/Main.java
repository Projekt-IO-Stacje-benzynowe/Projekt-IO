package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import app.service.SceneManager;

import app.db.connection.MySQLConnection;






// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.text.Font;
// import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage){
        MySQLConnection.makeConnection();
        SceneManager.setStage(primaryStage);
        // resources w java, nie dziala dla mavena
        // SceneManager.addScene("branch", "/resources/view/branch_panel/main_menu.fxml");
        // SceneManager.addScene("login", "/resources/view/login.fxml");
        // SceneManager.addScene("discount", "/app/view/branch_panel/discount_panel.fxml")
        // SceneManager.addScene("branch", "/view/branch_panel/main_menu.fxml");
        SceneManager.addScene("login", "/view/login.fxml");
        // SceneManager.addScene("login", "/view/login_screen.fxml");

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
    
    

    // @Override
    // public void start(Stage primaryStage) {
    //     // Logo (jeśli masz obrazek)
    //     ImageView logo = new ImageView(new Image("file:/resources/icon_top_center_on_blue.png")); // <- podmień na swoją ścieżkę
    //     logo.setFitWidth(100);
    //     logo.setPreserveRatio(true);

    //     Label title = new Label("Retail Management");
    //     title.setFont(new Font("Arial", 24));

    //     TextField emailField = new TextField();
    //     emailField.setPromptText("Email address");

    //     PasswordField passwordField = new PasswordField();
    //     passwordField.setPromptText("Password");

    //     Button loginButton = new Button("Log in");
    //     loginButton.setMaxWidth(Double.MAX_VALUE);

    //     Hyperlink forgotPassword = new Hyperlink("Forgot password?");

    //     VBox vbox = new VBox(10, logo, title, emailField, passwordField, loginButton, forgotPassword);
    //     vbox.setPadding(new Insets(40));
    //     vbox.setAlignment(Pos.CENTER);
    //     vbox.setPrefWidth(300);

    //     Scene scene = new Scene(vbox, 400, 500);
    //     primaryStage.setScene(scene);
    //     primaryStage.setTitle("Login");
    //     primaryStage.show();
    // }

    // public static void main(String[] args) {
    //     launch(args);
    // }
}