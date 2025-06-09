package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.RewardModel;
import javafx.collections.ObservableList;

public class PlanDeliveryService {
    public static ObservableList<RewardModel> getRewards() {
        return RepositorySQL.getAllRewards();
    }
}
