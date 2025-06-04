package app.service;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class TypeValidation {
    
    public static int intValidation(TextField num){
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
