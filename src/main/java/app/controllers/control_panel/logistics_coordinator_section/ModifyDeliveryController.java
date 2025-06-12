package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.RewardModel;
import app.service.SceneManager;
import app.service.control_panel.logistics_coordinator_section.ModifyDeliveryService;
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

public class ModifyDeliveryController extends MainController implements DynamicContentController {
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
            outletText.setText(ModifyDeliveryService.getOutletName());

            // Initialize the reward combo box with available rewards

            ObservableList<RewardModel> rewards = PlanDeliveryService.getRewards();
            rewardComboBox.setItems(rewards);
            Integer rewardID = ModifyDeliveryService.getRewardID();
            rewardComboBox.getSelectionModel().select(rewards.stream().filter(reward -> reward.getRewardProductID() == rewardID).findFirst().get());

            quantityField.setText(ModifyDeliveryService.getQuantity());

            // Ensures that only future dates can be selected
            deliveryDatePicker.setDayCellFactory(_ -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.compareTo(LocalDate.now().plusDays(1)) < 0 );
                }
            });
            deliveryDatePicker.setValue(ModifyDeliveryService.getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyDelivery(ActionEvent event) {
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
        ModifyDeliveryService.modifyDelivery(selectedReward, quantity, deliveryDate);
        errorText.setText("Successfully modified the delivery");
    }

    public void goBack(ActionEvent event) {
        ModifyDeliveryService.setSessionDelivery(null);
        mainController.showDynamicContent("choose_delivery");
        SceneManager.clearScene("modify_delivery");
    }
}
