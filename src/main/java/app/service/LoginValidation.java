package app.service;

import app.controllers.shared.MainController;
import app.db.connection.MySQLConnection;
import app.db.repo.RepositorySQL;
import app.model.UserModel;
import app.service.branch_panel.ClientSimulation.Launcher;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        System.out.println(user.getPanel());
        
        if (user == null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Informacja");
            alert.setHeaderText(null);
            alert.setContentText("Nie znaleziono użytkownika o podanym adresie email");
            alert.showAndWait();
            return;
        }
        
        if (password.equals(user.getPassword())){
            String panel = user.getPanel();
            Stage stage = (Stage) emailField.getScene().getWindow(); // <-- służy zamknięciu Panela logowania
            stage.close();

            // Załaduj główną scenę
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/shared/Main.fxml"));
            Parent root = loader.load();

            // Pokaż główne okno
            Stage mainStage = new Stage();
            mainStage.setScene(new Scene(root));
            mainStage.show();

            MainController mainController = loader.getController(); // <--przypisujemy kontroler do sceny
            mainController.setPanel(user.getPanel()); //<--- ładujemy zawartość w poprawne miejsce
            if (panel.equals("branch")){
                user.setNameBranch(RepositorySQL.getBranchNameForUser(user.getID()));
            
                Launcher launcher = new Launcher();
        

                launcher.runServerTask();   
                try {
                    Thread.sleep(500);
                } catch(Exception e){
                    System.out.println("Error while freezing thread" + e);
                };

                launcher.runClientTask();            
            }
            Session.user = user;
        };
    }
}
