package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.*;
import app.model.RewardModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.PlanDeliveryService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class PlanDeliveryController extends MainController implements DynamicContentController {
    private MainController mainController;

    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
            this.mainController = mainController;
    }
    @FXML
    private GridPane deliveryGrid;
    @FXML
    private Text outletText;
    @FXML
    private ComboBox<RewardModel> rewardComboBox;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private Text errorText;
    @FXML
    private VBox sidebarContainer;

    public void initialize() {
        try {
            outletText.setText(PlanDeliveryService.getOutletName());

            // Initialize the reward combo box with available rewards

            ObservableList<RewardModel> rewards = PlanDeliveryService.getRewards();
            rewardComboBox.setItems(rewards);
            rewardComboBox.setPromptText("Select a reward");

            // Ensures that only future dates can be selected
            deliveryDatePicker.setDayCellFactory(_ -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(LocalDate.now().plusDays(1)) < 0 );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void planDelivery(ActionEvent event) {
        RewardModel selectedReward = rewardComboBox.getValue();
        if (selectedReward == null) {
            errorText.setText("Please select a reward.");
            return;
        }
        int quantity;
        String quantityText = quantityField.getText();
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                errorText.setText("Quantity must be a positive number.");
                return;
            }
        } catch (NumberFormatException e) {
            errorText.setText("Invalid quantity format.");
            return;
        }
        LocalDate deliveryDate = deliveryDatePicker.getValue();
        if (deliveryDate == null) {
            errorText.setText("Please select a delivery date.");
            return;
        }

        boolean result = PlanDeliveryService.addDelivery(selectedReward, quantity, deliveryDate);
        if (result == true) {
            errorText.setText("Successfully added a delivery");
        }
        else {
            errorText.setText("Failed to add a delivery");
        }
        
    }

    public void goBack(ActionEvent event) {
        PlanDeliveryService.setSessionOutlet(null);
        mainController.showDynamicContent("logistics_main");
        SceneManager.clearScene("plan_delivery");
    }


}