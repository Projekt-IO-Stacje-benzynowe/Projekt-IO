package app.controllers.branch_panel.questionnaire;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.event.ActionEvent;

import java.util.HashMap;

public class Questionnaire {    
    @FXML                       
    private ChoiceBox<String> questField;
    private HashMap<Integer, Object> questionMap = new HashMap<>();
    
    @FXML                            
    public void initialize(){
        System.out.println("Questionnaire controller initialized");
        questField.getItems().addAll("1", "2", "3", "4", "5");
        questField.setValue("Choose an option");
        questField.show();
    }

    @FXML                      
    public void displayPollButton(ActionEvent event){
        

        System.out.println("Display poll button clicked");  
    }

}