package app.controllers.control_panel.promotions_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.OutletModel;
import app.model.ProductModel;
import app.model.RewardModel;
import app.service.SceneManager;
import app.service.control_panel.promotions_coordinator_section.CreatePromotionService;
import app.service.control_panel.promotions_coordinator_section.ModifyPromotionService;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

/**
 *  Controller class for creating a new promotion in the promotions coordinator section of the control panel.
 */
public class CreatePromotionController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private BorderPane contentPane;
    @FXML
    private GridPane deliveryGrid;
    @FXML
    private TextField promotionText;
    @FXML
    private TextField descriptionText;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<RewardModel> rewardComboBox;
    @FXML
    private ComboBox<ProductModel> productComboBox;
    @FXML
    private ComboBox<OutletModel> outletComboBox;
    @FXML
    private TextField quantityField;

     public void initialize() {
        try {
            // Initialize the text fields and date pickers
            // Make sure only future dates can be selected
            startDatePicker.setDayCellFactory(_ -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(LocalDate.now().plusDays(1)));
                }
            });

            endDatePicker.setDayCellFactory(_ -> new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    setDisable(empty || date.isBefore(LocalDate.now().plusDays(1)));
                }
            });

            // Initialize the reward and product combo boxes with available options
            // Set prompt texts for better user experience
            ObservableList<RewardModel> rewards = CreatePromotionService.getRewards();
            rewardComboBox.setItems(rewards);
            rewardComboBox.setPromptText("Select a reward");

            ObservableList<ProductModel> products = CreatePromotionService.getProducts();
            productComboBox.setItems(products);
            productComboBox.setPromptText("Select a product");

            ObservableList<OutletModel> outlets = CreatePromotionService.getOutlets();
            outletComboBox.setItems(outlets);
            outletComboBox.setPromptText("Select an outlet");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPromotion(ActionEvent event) {
        int result = CreatePromotionService.createPromotion(promotionText.getText(), descriptionText.getText(), startDatePicker.getValue(), endDatePicker.getValue(), rewardComboBox.getSelectionModel().getSelectedItem(), productComboBox.getSelectionModel().getSelectedItem(), quantityField.getText());
        if (result == 0) {
            ModifyPromotionService.clearNonUserData();
            SceneManager.clearScene("create_promotion");
            mainController.showDynamicContent("promotion_main");
        }
    }
}