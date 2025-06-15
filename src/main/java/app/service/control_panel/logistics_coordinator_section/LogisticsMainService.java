package app.service.control_panel.logistics_coordinator_section;

import app.model.OutletModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TableService;

import javafx.scene.control.TableView;

/**
 * Service class for managing outlets in the logistics coordinator section.
 * Provides methods to retrieve outlet data and set the current outlet.
 */
public class LogisticsMainService {
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
