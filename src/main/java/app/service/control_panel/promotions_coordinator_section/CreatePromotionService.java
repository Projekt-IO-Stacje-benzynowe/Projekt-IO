package app.service.control_panel.promotions_coordinator_section;

import java.time.LocalDate;

import app.db.repo.RepositorySQL;
import app.model.OutletModel;
import app.model.ProductModel;
import app.model.RewardModel;
import app.service.Alerts;
import app.service.TypeValidation;
import javafx.collections.ObservableList;

/**
 *  Service class for creating a new promotion in the promotions coordinator section of the control panel.
 */
public class CreatePromotionService {
    public static ObservableList<RewardModel> getRewards() {
        return RepositorySQL.getAllRewards();
    }

    public static ObservableList<ProductModel> getProducts() {
        return RepositorySQL.getAllProducts();
    }

    public static ObservableList<OutletModel> getOutlets() {
        return RepositorySQL.getAllOutlets();
    }

    public static int createPromotion(String promotionName, String promotionDescription, LocalDate startDate, LocalDate endDate, RewardModel reward, ProductModel product, OutletModel outlet, String quantityText) {
        if (promotionName == null || promotionName.isEmpty()) {
            Alerts.warnInvalidInput("promotion name");
            return -1; // Error: Promotion name is empty
        }
        if (promotionDescription == null || promotionDescription.isEmpty()) {
            Alerts.warnInvalidInput("promotion description");
            return -1; // Error: Promotion description is empty
        }

        if (startDate.isAfter(endDate)) {
            Alerts.warnInvalidDateRange();
            return -1; // Error: Start date is after end date
        }

        if (reward == null) {
            Alerts.warnSelect("reward");
            return -1; // Error: Reward is not selected
        }

        if (product == null) {
            Alerts.warnSelect("product");
            return -1; // Error: Product is not selected
        }

        if (outlet == null) {
            Alerts.warnSelect("outlet");
            return -1; // Error: Outlet is not selected
        }

        Integer quantity = TypeValidation.intValidation(quantityText);
        if (quantity == null || quantity <= 0) {
            Alerts.warnInvalidInput("quantity");
            return -1; // Error: Invalid quantity
        }

        boolean result = RepositorySQL.createPromotion(promotionName, promotionDescription, startDate, endDate, reward.getRewardProductID(), product.getID(), outlet.getID(), quantity);
        if (result) {
            Alerts.confirmAdd("Promotion");
            return 0; // Success: Promotion modified
        } else {
            Alerts.warnFailedToAdd("promotion");
            return -1; // Error: Failed to modify promotion
        }
    }
}