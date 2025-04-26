import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;


public class LoginController{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private void loginScene(ActionEvent event) {
        SceneManager.showScene("login");

        if (loginValidation(emailField.getText(), passwordField.getText())){
            SceneManager.addScene("screen1", "/view/Screen1.fxml");
            SceneManager.showScene("screen1");;
        };
    }   
    private static boolean loginValidation(String email, String pass){
        return email.equals("admin@gmail.com") && pass.equals("12345");
    }
}
