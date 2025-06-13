package app.service.branch_panel;

import app.db.repo.RepositorySQL;
import app.service.Session;

import app.service.TypeValidation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.sql.Timestamp;

public class ReportProduct {
    public static void report(TextField prodID, TextField quan, TextField desc, TextField date){
        int quantity = TypeValidation.intValidation(quan.getText());
        int productID = TypeValidation.intValidation(prodID.getText());
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);
        // it means that inserted data was not correct
        if(quantity == -1 || productID == -1){
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Wprowadzono niepoprawne dane");
            alert.showAndWait();
            return;
        }

        Timestamp validDate;
        try{
            validDate = Timestamp.valueOf(date.getText());
        } catch (Exception e){
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Wprowadzono niepoprawną datę");
            alert.showAndWait();
            return;
        }   

        RepositorySQL.sendReport(Session.user.getID(), productID, quantity, desc.getText(), validDate);
        
        if (quantity >= 1){
            alert.setContentText("Zgłoszono usterkę produktów");
        }else{
            alert.setContentText("Zgłoszono usterkę produktu");
        }

        alert.showAndWait();
    }
}
