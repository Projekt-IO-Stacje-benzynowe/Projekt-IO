package app.controllers.control_panel.logistics_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.RewardModel;
import app.service.SceneManager;
import app.service.control_panel.logistics_coordinator_section.ModifyDeliveryService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

    public void initialize() {
        try {
            outletText.setText(ModifyDeliveryService.getOutletName());

            // Initialize the reward combo box with available rewards

            ObservableList<RewardModel> rewards = ModifyDeliveryService.getRewards();
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
            LocalDate date = ModifyDeliveryService.getDate();
            if (date != null) {
                deliveryDatePicker.setValue(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyDelivery(ActionEvent event) {
        RewardModel selectedReward = rewardComboBox.getValue();
        String quantityText = quantityField.getText();
        LocalDate deliveryDate = deliveryDatePicker.getValue();
        int result = ModifyDeliveryService.modifyDelivery(selectedReward, quantityText, deliveryDate);
        if (result == 0) {
            ModifyDeliveryService.clearNonUserData();
            SceneManager.clearScene("modify_delivery");
            mainController.showDynamicContent("logistics_main");
        }
    }
}
