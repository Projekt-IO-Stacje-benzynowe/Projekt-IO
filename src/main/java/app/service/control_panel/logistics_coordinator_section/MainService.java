package app.service.control_panel.logistics_coordinator_section;

import app.model.PromotionsModel;
import app.service.TableService;

import javafx.scene.control.TableView;

public class MainService {

    public static TableView<PromotionsModel> getPromotionsTable(String promotionName) {
        return TableService.getPromotionsTable(promotionName);
    }
}
