package app.service.business_panel;

import app.model.RewardModel;
import app.service.TableService;
import javafx.scene.control.TableView;

/**
 * Service class for managing rewards in the business panel.
 * Provides methods to retrieve reward data.
 */
public class rewardService {
    public static TableView<RewardModel> getAllRewards() {
        return TableService.getAllRewardsTable();
    }
}
