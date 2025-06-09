package app.controllers.control_panel.logistics_coordinator_section;

import app.model.RewardModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.logistics_coordinator_section.PlanDeliveryService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlanDeliveryController implements Controller {
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private BorderPane background;
    @FXML
    private HBox topBar;
    @FXML
    private VBox sidebar;
    @FXML
    private BorderPane mainContent;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/control_panel/sidebar.fxml"));
            sidebarContainer = loader.load();

            SidebarController sidebarController = loader.getController();
            if (sidebarController != null) {
                sidebarController.setController(this);
            }
            sidebarContainer.prefHeightProperty().bind(mainContainer.heightProperty());
            sidebarContainer.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.2));
            // Dodaj do mainContainer na indeksie 1 (pod top, nad contentArea)
            mainContainer.getChildren().add(sidebarContainer);
            background.prefWidthProperty().bind(mainContainer.widthProperty());
            background.prefHeightProperty().bind(mainContainer.heightProperty());
            mainContent.prefWidthProperty().bind(mainContainer.widthProperty().multiply(0.8));
            mainContent.prefHeightProperty().bind(mainContainer.heightProperty());
            deliveryGrid.setPrefWidth(mainContent.getWidth() * 0.8);
            deliveryGrid.setPrefHeight(mainContent.getHeight() * 0.8);
            outletText.setText(Session.getOutlet().getName());

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
        SceneManager.showScene("Main");
        SceneManager.clearScene("plan_delivery");
    }
}