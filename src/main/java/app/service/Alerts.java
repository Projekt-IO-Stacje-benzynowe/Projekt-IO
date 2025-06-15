package app.service;

import javafx.scene.control.Alert;

public class Alerts {
    public static void warnSelectToDelete(String item) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText("Please select a " + item + " to delete.");
        alert.showAndWait();
    }

    public static void warnSelectToModify(String item) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText("Please select a " + item + " to modify.");
        alert.showAndWait();
    }

    public static void warnSelect(String item) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText("Please select a " + item + ".");
        alert.showAndWait();
    }

    public static void confirmDelete(String item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Deletion Successful");
        alert.setHeaderText(null);
        alert.setContentText(item + " deleted successfully.");
        alert.showAndWait();
    }

    public static void confirmAdd(String item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Addition Successful");
        alert.setHeaderText(null);
        alert.setContentText(item + " added successfully.");
        alert.showAndWait();
    }

    public static void confirmModify(String item) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Modification Successful");
        alert.setHeaderText(null);
        alert.setContentText(item + " modified successfully.");
        alert.showAndWait();
    }

    public static void warnFailedToDelete(String item) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Deletion Failed");
        alert.setHeaderText(null);
        alert.setContentText("Failed to delete " + item + ".");
        alert.showAndWait();
    }

    public static void warnFailedToAdd(String item) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText("Failed to delete " + item + ".");
        alert.showAndWait();
    }

    public static void warnFailedToModify(String item) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Modification Failed");
        alert.setHeaderText(null);
        alert.setContentText("Failed to modify " + item + ".");
        alert.showAndWait();
    }

    public static void warnSelectToAdd(String item) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Selection Required");
        alert.setHeaderText(null);
        alert.setContentText("Please select a " + item + " to add.");
        alert.showAndWait();
    }

    public static void warnInvalidInput(String item) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a valid " + item + ".");
        alert.showAndWait();
    }

    public static void warnCannotDeleteBegunPromotion() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Cannot Delete Promotion");
        alert.setHeaderText(null);
        alert.setContentText("You cannot delete a promotion that has already begun.");
        alert.showAndWait();
    }

    public static void warnInvalidDateRange() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Date Range");
        alert.setHeaderText(null);
        alert.setContentText("The start date cannot be after the end date.");
        alert.showAndWait();
    }
}
