package app.service.control_panel.logistics_coordinator_section;

import app.model.RequestModel;
import app.service.Session;
import app.service.TableService;
import javafx.scene.control.TableView;

public class ViewRequestsService {
    public static TableView<RequestModel> getRequests() {
        return TableService.getRequestsForOutletTable(Session.getOutlet().getID());
    }
}
