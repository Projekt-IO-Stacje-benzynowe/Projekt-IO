package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.service.Session;
import app.service.TableService;

import javafx.scene.control.TableView;

public class ChooseDeliveryService {
    public static TableView<DeliveryModel> getDeliveriesForOutletView() {
        Integer outletID = Session.getOutlet().getID();
        return TableService.getDeliveriesForOutletTable(outletID);
    }

    public static void setSessionDelivery(DeliveryModel delivery) {
        Session.setDelivery(delivery);
    }

    public static void setSessionOutlet(OutletModel outlet) {
        Session.setOutlet(outlet);
    }

    public static boolean deleteDelivery(DeliveryModel delivery) {
        return RepositorySQL.deleteDelivery(delivery.getId());
    }
}
