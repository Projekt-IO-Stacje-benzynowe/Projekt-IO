package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.model.RewardModel;
import app.service.Session;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class PlanDeliveryService {
    public static ObservableList<RewardModel> getRewards() {
        return RepositorySQL.getAllRewards();
    }

    public static boolean addDelivery(RewardModel selectedReward, int quantity, LocalDate deliveryDate) {
        DeliveryModel delivery = new DeliveryModel(null, Session.getOutlet().getID(), selectedReward.getPromotionID(), null, quantity, deliveryDate);
        return RepositorySQL.addDelivery(delivery);
    }

    public static String getOutletName() {
        return Session.getOutlet().getName();
    }

    public static void setSessionOutlet(OutletModel outlet) {
        Session.setOutlet(outlet);
    }
}
