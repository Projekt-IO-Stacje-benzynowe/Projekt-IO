package app.service.branch_panel;

import app.db.repo.RepositorySQL;
import app.service.Session;

import app.service.TypeValidation;

import javafx.scene.control.TextField;

import java.sql.Timestamp;

import app.service.Alerts;

public class ReportProduct {
    public static void report(TextField prodID, TextField quan, TextField desc, TextField date){
        int quantity = TypeValidation.intValidation(quan.getText());
        int productID = TypeValidation.intValidation(prodID.getText());

        // it means that inserted data was not correct
        if(quantity == -1){
            Alerts.warnInvalidInput("quantity");
            quan.clear();
            return;
        }
        if(productID == -1){
            Alerts.warnInvalidInput("productID");
            prodID.clear();
            return;
        }

        if(!TypeValidation.isValidDateTime(date.getText())){
            Alerts.warnInvalidInput("date");
            date.clear();
            return;
        };

        Timestamp validDate;
        try{
            validDate = Timestamp.valueOf(date.getText());
        } catch (Exception e){
            Alerts.warnInvalidInput(" date");
            date.clear();
            return;
        }   

        RepositorySQL.sendReport(Session.getUser().getID(), productID, quantity, desc.getText(), validDate);        
        if (quantity >= 1){
            Alerts.infoAlert("Information", "Product defects have been reported successfully.");
        }else{
            Alerts.infoAlert("Information", "Products defects have been reported successfully.");
        }
    }
}
