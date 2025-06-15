package app.service.control_panel.promotions_coordinator_section;

import java.time.LocalDate;

import app.db.repo.RepositorySQL;
import app.model.ProductModel;
import app.model.PromotionModel;
import app.model.RewardModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TypeValidation;
import javafx.collections.ObservableList;


public class ModifyPromotionService {
    public static PromotionModel getPromotion() {
        return Session.getPromotion();
    }

    public static ObservableList<RewardModel> getRewards() {
        return RepositorySQL.getAllRewards();
    }

    public static ObservableList<ProductModel> getProducts() {
        return RepositorySQL.getAllProducts();
    }

    public static int getRewardID(int promotionID) {
        return RepositorySQL.getRewardIDForPromotion(promotionID);
    }

    public static int modifyPromotion(String promotionName, String promotionDescription, LocalDate startDate, LocalDate endDate, RewardModel reward, ProductModel product, String quantityText) {
        PromotionModel promotion = Session.getPromotion();
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

        Integer quantity = TypeValidation.intValidation(quantityText);
        if (quantity == null || quantity <= 0) {
            Alerts.warnInvalidInput("quantity");
            return -1; // Error: Invalid quantity
        }

        boolean result = RepositorySQL.modifyPromotion(promotion.getID(), promotionName, promotionDescription, startDate, endDate, reward.getRewardProductID(), product.getID(), quantity);
        if (result) {
            Alerts.confirmModify("Promotion");
            return 0; // Success: Promotion modified
        } else {
            Alerts.warnFailedToModify("promotion");
            return -3; // Error: Failed to modify promotion
        }
        
    }

    public static void clearNonUserData() {
        Session.clearNonUserData();
    }
}