package app.service.branch_panel;

import app.db.repo.RepositorySQL;
import app.service.Session;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

import java.sql.Timestamp;

public class ReportProduct {
    public static void report(TextField prodID, TextField quan, TextField desc, TextField date){
        Integer quantity = intValidation(quan);
        Integer productID = intValidation(prodID);
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Informacja");
        alert.setHeaderText(null);

        Timestamp validDate;
        try{
            validDate = Timestamp.valueOf(date.getText());
        } catch (Exception e){
            alert.setAlertType(AlertType.WARNING);
            alert.setContentText("Nie znaleziono dostawy o podanym ID.");
            alert.showAndWait();
            return;
        }   

        if(quantity != -1 && productID != -1)
            RepositorySQL.sendReport(Session.getUser().getID(), productID, quantity, desc.getText(), validDate);
        
        if (quantity >= 1){
            alert.setContentText("Zgłoszono usterkę produktów");
        }else{
            alert.setContentText("Zgłoszono usterkę produktu");
        }

        alert.showAndWait();
    }
    private static int intValidation(TextField num){
        int result = -1;        
        try {
            result = Integer.parseInt(num.getText());
        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Niepoprawna liczba");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadź liczbę całkowitą");
            alert.showAndWait();
        }
        return result;
    }
}
