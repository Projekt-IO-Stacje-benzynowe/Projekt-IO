package app.controllers.control_panel.rewards_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.RewardModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.PlanDeliveryService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ModifyRewardController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala przypisać ten kontroler do głównego kontrolera
        this.mainController = mainController;
    }
    @FXML
    private ComboBox<RewardModel> rewardComboBox;
    @FXML
    private TextField quantityField;

    @FXML
    private Text errorText;

    public void initialize() {
        try {
            // Initialize the reward combo box with available rewards
            ObservableList<RewardModel> rewards = PlanDeliveryService.getRewards();
            rewardComboBox.setItems(rewards);
            rewardComboBox.setPromptText("Select a reward");

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
        errorText.setText(selectedReward.getName() + " " + quantityField.getText());
    }

    public void goBack(ActionEvent event) {
        Session.setOutletNull();
        SceneManager.showScene("main");
        SceneManager.clearScene("plan_delivery");
    }
}
