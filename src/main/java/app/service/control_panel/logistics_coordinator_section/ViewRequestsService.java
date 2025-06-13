package app.service.control_panel.logistics_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.model.RequestModel;
import app.service.Session;
import app.service.TableService;
import app.service.Alerts;
import app.service.Dialogs;
import javafx.scene.control.TableView;

public class ViewRequestsService {

    /**
     * Retrieves a TableView of requests for the current outlet.
     * This method fetches all requests associated with the outlet currently set in the session.
     * @return  A TableView containing RequestModel objects for the current outlet.
     */
    public static TableView<RequestModel> getRequests() {
        return TableService.getRequestsForOutletTable(Session.getOutlet().getID());
    }

    /**
     * Deletes a request from the database and updates the UI accordingly.
     *
     * @param request The request to be deleted.
     * @return An integer indicating the result of the deletion operation:
     *          1 if the request was deleted and the user wants to view more requests,
     *          0 if the request was deleted but the user does not want to view more requests,
     *           -1 if no request was selected or deletion failed.
     */
    public static int deleteRequest(RequestModel request) {
        if (request != null) {
            boolean result = RepositorySQL.deleteDelivery(request.getDeliveryID());
            if (result) {
                Alerts.confirmDelete("Request");
                boolean decision = Dialogs.askIfRepeat("Do you want to view more requests?");
                if (decision) {
                    return 1; // Indicates that the request was deleted and the user wants to view more requests
                }
                else {
                    return 0; // Indicates that the request was deleted but the user does not want to view more requests
                }
            } else {
                Alerts.warnFailedToDelete("request");
            }
        } else {
            Alerts.warnSelectToDelete("Request");
        }
        return -1; // Indicates that no request was selected or deletion failed
    }


    /**
     * Sets the current outlet in the session.
     * @param outlet  The OutletModel object representing the outlet to be set in the session.
     */
    public static void setSessionOutlet(OutletModel outlet) {
        Session.setOutlet(outlet);
    }

    public static void clearNonUserData() {
        Session.clearNonUserData();
    }

    /**
     * Sets the current request in the session.
     * @param request
     * @return  true if the request was successfully set, false otherwise.
     */
    public static boolean setSessionRequest(RequestModel request) {
        if (request != null) {
            Session.setDelivery(new DeliveryModel(request.getDeliveryID(), null, request.getRewardID(), request.getRewardName(), request.getQuantity(), null));
            return true;
        }
        else {
            Alerts.warnSelectToModify("request");
            return false;
        }
    }
}
