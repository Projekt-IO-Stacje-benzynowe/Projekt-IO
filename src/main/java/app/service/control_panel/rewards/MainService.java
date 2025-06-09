package app.service.control_panel.rewards;

import app.model.OutletModel;
import app.model.PromotionModel;
import app.service.TableService;
import javafx.scene.control.TableView;

public class MainService {

    public static TableView<PromotionModel> getAllPromotions() {
        return TableService.getAllPromotionsTable();
    }
}
