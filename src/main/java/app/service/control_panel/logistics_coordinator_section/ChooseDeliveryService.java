package app.service.control_panel.logistics_coordinator_section;

import app.model.DeliveryModel;
import app.service.TableService;

import javafx.scene.control.TableView;

public class ChooseDeliveryService {
    public static TableView<DeliveryModel> getDeliveriesForOutletView(Integer outletID) {
        return TableService.getDeliveriesForOutletTable(outletID);
    }
}
