package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
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

    public static void deleteDelivery(DeliveryModel delivery) {
        RepositorySQL.deleteDelivery(delivery.getId());
    }
}
