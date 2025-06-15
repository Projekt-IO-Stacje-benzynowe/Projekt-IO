package app.service;

import app.controllers.shared.MainController;
import app.db.connection.MySQLConnection;
import app.db.repo.RepositorySQL;
import app.model.UserModel;
import app.service.branch_panel.ClientSimulation.LauncherService;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginValidation{
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;

    @FXML
    private void loginScene(ActionEvent event) throws IOException {
        MySQLConnection.makeConnection();

        String email = emailField.getText();
        String password = passwordField.getText();
        UserModel user = RepositorySQL.findUser(email);
        
        if (user == null){
            Alerts.warnInvalidInput("login details");
            emailField.clear();
            passwordField.clear();
            return;
        }
        
        if (password.equals(user.getPassword())){
            String panel = user.getPanel();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.close();

            // load main scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/shared/Main.fxml"));
            
            Parent root = loader.load();

            // show app window
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(root));
            mainStage.show();

            MainController mainController = loader.getController(); // assing controller to the scene
            mainController.setPanel(user.getPanel()); //set correct panel
            
            if (panel.equals("branch")){
                user.setNameBranch(RepositorySQL.getBranchNameForUser(user.getID()));
            
                LauncherService launcher = new LauncherService();
        

                launcher.runServerTask();   
                try {
                    Thread.sleep(500);
                } catch(Exception e){
                    System.out.println("Error while freezing thread" + e);
                };

                launcher.runClientTask();            
            }
            Session.setUser(user);
        };
    }
}
