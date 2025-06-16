package app.service;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

/**
 * Dialogs class provides methods to create and display dialog boxes.
 * It includes a method to ask the user if they want to repeat an action.
 */
public class Dialogs {
    public static boolean askIfRepeat(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Repeat Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.getButtonTypes().setAll(
                new ButtonType("Yes", ButtonData.YES),
                new ButtonType("No", ButtonData.NO)
        );
        return alert.showAndWait().orElse(ButtonType.NO).getButtonData() == ButtonData.YES;
    }
}
