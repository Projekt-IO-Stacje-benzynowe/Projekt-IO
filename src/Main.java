
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SceneManager.setStage(primaryStage);
        SceneManager.addScene("login", "/view/login.fxml");
        // SceneManager.addScene("screen1", "/view/Screen1.fxml");
        // SceneManager.addScene("screen2", "/view/Screen2.fxml");

        SceneManager.showScene("login");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
