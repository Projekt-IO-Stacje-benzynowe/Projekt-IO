package app.service;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import app.db.repo.RepositorySQL;
import app.model.UserModel;

public class LoginValidation{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private void loginScene(ActionEvent event) {
        SceneManager.showScene("login");

        String email = emailField.getText();
        String password = passwordField.getText();
        UserModel user = RepositorySQL.FindUser(email);
        
        if (user == null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Nie znaleziono użytkownika o podanym adresie email");
            alert.showAndWait();
            return;
        }
        
        if (password.equals(user.getPassword())){
            SceneManager.showScene(user.getPanel());
            Session.User = user;
        };
    }
}
