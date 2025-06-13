package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.model.RewardModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TypeValidation;
import javafx.collections.ObservableList;

import java.time.LocalDate;

public class PlanDeliveryService {
    public static ObservableList<RewardModel> getRewards() {
        return RepositorySQL.getAllRewards();
    }

    public static int addDelivery(RewardModel selectedReward, String quantityText, LocalDate deliveryDate) {
        Integer quantity = TypeValidation.intValidation(quantityText);
        if (selectedReward == null) {
            Alerts.warnSelectToAdd("reward");
            return -1; // Invalid reward selection
        }
        if (deliveryDate == null) {
            Alerts.warnSelectToAdd("delivery date");
            return -1; // Invalid delivery date
        }
        if(quantity == null || quantity <= 0) {
            Alerts.warnInvalidInput("quantity");
            return -1; // Invalid quantity
        }
        DeliveryModel delivery = new DeliveryModel(null, Session.getOutlet().getID(), selectedReward.getPromotionID(), null, quantity, deliveryDate);
        boolean result = RepositorySQL.addDelivery(delivery);
        if(result) {
            Alerts.confirmAdd("Delivery");
            return 0; // Delivery added successfully
        } else {
            Alerts.warnFailedToAdd("delivery");
            return -1; // Failed to add delivery
        }
    }

    public static String getOutletName() {
        return Session.getOutlet().getName();
    }

    public static void setSessionOutlet(OutletModel outlet) {
        Session.setOutlet(outlet);
    }

    public static void clearNonUserData() {
        Session.clearNonUserData();
    }
}
