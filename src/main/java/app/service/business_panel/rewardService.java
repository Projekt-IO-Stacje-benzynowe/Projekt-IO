package app.service.business_panel;

import app.model.PromotionModel;
import app.model.RewardModel;
import app.service.TableService;
import javafx.scene.control.TableView;

public class rewardService {
    public static TableView<RewardModel> getAllRewards() {
        return TableService.getAllRewardsTable();
    }
}
