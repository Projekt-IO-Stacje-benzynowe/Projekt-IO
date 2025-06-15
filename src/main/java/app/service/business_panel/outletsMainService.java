package app.service.business_panel;

import app.model.OutletModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TableService;
import javafx.scene.control.TableView;

public class outletsMainService {
    public static TableView<OutletModel> getAllOutletsView() {
        return TableService.getAllOutletsTable();
    }

    public static int setOutlet(OutletModel outlet) {
        if(outlet == null) {
            Alerts.warnSelect("outlet");
            return -1; // Invalid outlet
        }
        else {
            Session.setOutlet(outlet);
            return 0; // Outlet set successfully
        }
    }
}
