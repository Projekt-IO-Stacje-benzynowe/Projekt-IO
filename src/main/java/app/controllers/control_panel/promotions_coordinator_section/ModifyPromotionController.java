package app.controllers.control_panel.promotions_coordinator_section;

import java.time.LocalDate;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.ProductModel;
import app.model.PromotionModel;
import app.model.RewardModel;
import app.service.SceneManager;
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

/**
 *  Controller for modifying an existing promotion in the promotions coordinator section of the control panel.
 */
public class ModifyPromotionController implements DynamicContentController {
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
    private TextField quantityField;

    public void initialize() {
        try {
            PromotionModel current_promotion = ModifyPromotionService.getPromotion();
            promotionText.setText(current_promotion.getName());
            descriptionText.setText(current_promotion.getDesc());


            if (current_promotion.getStartDate().isBefore(LocalDate.now().plusDays(1))) {
                // Disable changing the start date if the promotion has already started
                startDatePicker.setDayCellFactory(_ -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || !date.isEqual(current_promotion.getStartDate()));
                    }
                });
            } else {
                startDatePicker.setDayCellFactory(_ -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.compareTo(LocalDate.now().plusDays(1)) < 0 );
                    }
                });
            }
            startDatePicker.setValue(current_promotion.getStartDate());

            if (current_promotion.getEndDate().isBefore(LocalDate.now().plusDays(1))) {
                // Disable changing the end date if the promotion has already ended
                endDatePicker.setDayCellFactory(_ -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || !date.isEqual(current_promotion.getEndDate()));
                    }
                });
            } else {
                endDatePicker.setDayCellFactory(_ -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        setDisable(empty || date.compareTo(LocalDate.now().plusDays(1)) < 0 );
                    }
                });
            }
            endDatePicker.setValue(current_promotion.getEndDate());

            ObservableList<RewardModel> rewards = ModifyPromotionService.getRewards();
            rewardComboBox.setItems(rewards);
            Integer rewardID = ModifyPromotionService.getRewardID(current_promotion.getID());
            rewardComboBox.getSelectionModel().select(rewards.stream().filter(reward -> reward.getRewardProductID() == rewardID).findFirst().get());

            ObservableList<ProductModel> products = ModifyPromotionService.getProducts();
            productComboBox.setItems(products);
            Integer productID = current_promotion.getProductID();
            productComboBox.getSelectionModel().select(products.stream().filter(product -> product.getID() == productID).findFirst().get());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifyPromotion(ActionEvent event) {
        int result = ModifyPromotionService.modifyPromotion(promotionText.getText(), descriptionText.getText(), startDatePicker.getValue(), endDatePicker.getValue(), rewardComboBox.getSelectionModel().getSelectedItem(), productComboBox.getSelectionModel().getSelectedItem(), quantityField.getText());
        if (result == 0) {
            ModifyPromotionService.clearNonUserData();
            SceneManager.clearScene("modify_promotion");
            mainController.showDynamicContent("promotion_main");
        }
    }
}
