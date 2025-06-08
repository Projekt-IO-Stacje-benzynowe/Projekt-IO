package app.service.control_panel.logistics_coordinator_section;

import app.model.PromotionsModel;
import app.service.TableService;

import javafx.scene.control.TableView;

public class MainService {

    public static TableView<PromotionsModel> getPromotionsTable(String outletName) {
        return TableService.getPromotionsTable(outletName);
    }

    public static TableView<PromotionsModel> getAllPromotionsTable() {
        return TableService.getAllPromotionsTable();
    }
}
