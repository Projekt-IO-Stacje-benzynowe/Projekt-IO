package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TableService;
import javafx.scene.control.TableView;

public class ChooseDeliveryService {
    public static TableView<DeliveryModel> getDeliveriesForOutletView() {
        Integer outletID = Session.getOutlet().getID();
        return TableService.getDeliveriesForOutletTable(outletID);
    }

    public static int setSessionDelivery(DeliveryModel delivery) {
        if (delivery == null) {
            Alerts.warnSelect("delivery");
            return -1; // Invalid delivery
        }
        Session.setDelivery(delivery);
        return 0; // Delivery set successfully
    }

    public static void setSessionOutlet(OutletModel outlet) {
        Session.setOutlet(outlet);
    }

    public static int deleteDelivery(DeliveryModel delivery) {
        if (delivery == null) {
            Alerts.warnSelectToDelete("delivery");
            return -1; // No delivery selected
        }
        boolean result = RepositorySQL.deleteDelivery(delivery.getId());
        if (result) {
            Alerts.confirmDelete("Delivery");
            return 0; // Delivery deleted successfully
        } else {
            Alerts.warnFailedToDelete("delivery");
            return -1; // Failed to delete delivery
        }
    }
}
