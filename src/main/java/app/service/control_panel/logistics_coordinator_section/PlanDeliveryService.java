package app.service.control_panel.logistics_coordinator_section;

import java.time.LocalDate;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.RewardModel;
import app.service.Session;
import javafx.collections.ObservableList;

public class PlanDeliveryService {
    public static ObservableList<RewardModel> getRewards() {
        return RepositorySQL.getAllRewards();
    }

    public static void addDelivery(RewardModel selectedReward, int quantity, LocalDate deliveryDate) {
        DeliveryModel delivery = new DeliveryModel(null, Session.getOutlet().getID(), selectedReward.getPromotionID(), null, quantity, deliveryDate);
        RepositorySQL.addDelivery(delivery);
    }
}
