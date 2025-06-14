package app.service.control_panel.promotions_coordinator_section;

import app.model.PromotionModel;
import app.service.TableService;
import javafx.scene.control.TableView;

public class PromotionsMainService {

    public static TableView<PromotionModel> getAllPromotions() {
        return TableService.getAllPromotionsTable();
    }
}
