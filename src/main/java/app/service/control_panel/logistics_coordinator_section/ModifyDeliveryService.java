package app.service.control_panel.logistics_coordinator_section;

import java.time.LocalDate;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.RewardModel;
import app.service.Session;

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

    public static String getQuantity() {
        Integer quantity = Session.getDelivery().getQuantity();
        return quantity.toString();
    }

    public static LocalDate getDate() {
        return Session.getDelivery().getShipmentDate();
    }

    public static void modifyDelivery(RewardModel reward, Integer quantity, LocalDate deliveryDate) {
        RepositorySQL.modifyDelivery(reward.getRewardProductID(), quantity, deliveryDate, Session.getDelivery().getId());
    }
}
