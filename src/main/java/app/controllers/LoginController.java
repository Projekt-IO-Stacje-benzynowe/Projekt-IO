package app.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import app.db.repo.RepositorySQL;
import app.model.UserModel;
import app.service.Session;

public class LoginController{
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
        
        if (user != null & password.equals(user.getPassword())){
            SceneManager.showScene(user.getPanel());
            Session.User = user;
        };
    }
}
