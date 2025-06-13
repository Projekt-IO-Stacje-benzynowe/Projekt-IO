package app.service.control_panel.logistics_coordinator_section;

import java.time.LocalDate;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.RewardModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TypeValidation;
import javafx.collections.ObservableList;

/**
 *  Service class for modifying delivery details in the logistics coordinator section.
 *  This class provides methods to retrieve and modify delivery information,
 *  including the delivery model, outlet name, reward ID, quantity, and delivery date.
 *  It also allows setting the session delivery and retrieving available rewards.
 */
public class ModifyDeliveryService {
    public static DeliveryModel getDelivery() {
        return Session.getDelivery();
    }

    public static String getOutletName() {
        return Session.getOutlet().getName();
    }

    public static Integer getRewardID() {
        return Session.getDelivery().getRewardID();
    }

    public static void setSessionDelivery(DeliveryModel delivery) {
        Session.setDelivery(delivery);
    }

    public static void clearNonUserData() {
        Session.clearNonUserData();
    }

    public static String getQuantity() {
        Integer quantity = Session.getDelivery().getQuantity();
        return quantity.toString();
    }

    public static LocalDate getDate() {
        return Session.getDelivery().getShipmentDate();
    }

    public static int modifyDelivery(RewardModel reward, String quantityText, LocalDate deliveryDate) {
        Integer quantity = TypeValidation.intValidation(quantityText);
        if (reward == null) {
            Alerts.warnSelectToModify("reward");
            return -1;
        }
        if (quantity == null || quantity <= 0) {
            Alerts.warnInvalidInput("quantity");
            return -1;
        }
        if (deliveryDate == null) {
            Alerts.warnSelectToModify("delivery date");
            return -1;
        }
        boolean result = RepositorySQL.modifyDelivery(reward.getRewardProductID(), quantity, deliveryDate, Session.getDelivery().getId());
        if (result) {
            Alerts.confirmModify("Delivery");
            return 0; // Delivery modified successfully
        } else {
            Alerts.warnFailedToModify("delivery");
            return -1; // Failed to modify delivery
        }
    }
    
    public static ObservableList<RewardModel> getRewards() {    
        return RepositorySQL.getAllRewards();
    }
}
