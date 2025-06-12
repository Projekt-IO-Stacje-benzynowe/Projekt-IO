package app.service.control_panel.logistics_coordinator_section;

import app.model.OutletModel;
import app.service.TableService;

import javafx.scene.control.TableView;

public class LogisticsMainService {
    public static TableView<OutletModel> getAllOutletsView() {
        return TableService.getAllOutletsTable();
    }
}
