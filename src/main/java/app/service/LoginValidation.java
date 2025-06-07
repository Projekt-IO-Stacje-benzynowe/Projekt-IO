package app.service;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import app.db.connection.MySQLConnection;
import app.db.repo.RepositorySQL;
import app.model.UserModel;
import app.service.branch_panel.ClientSimulation.Launcher;
import app.db.connection.MySQLConnection;;

public class LoginValidation{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private void loginScene(ActionEvent event) {
        MySQLConnection.makeConnection();

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
            if (user.getPanel().equals("branch")){
                user.setNameBranch(RepositorySQL.GetBranchNameForUser(user.getID()));
            
                Launcher launcher = new Launcher();
        

                launcher.runServerTask();   
                try {
                    Thread.sleep(500);
                } catch(Exception e){
                    System.out.println("Error while freezing thread" + e);
                };

                launcher.runClientTask();            
            }
            Session.User = user;
        };
    }
}
