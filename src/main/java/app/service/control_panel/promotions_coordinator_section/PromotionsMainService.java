package app.service.control_panel.promotions_coordinator_section;

import app.db.repo.RepositorySQL;
import app.model.PromotionModel;
import app.service.Alerts;
import app.service.Session;
import app.service.TableService;

import javafx.scene.control.TableView;

import java.time.LocalDate;

public class PromotionsMainService {
    public static TableView<PromotionModel> getAllPromotions() {
        return TableService.getAllPromotionsTable();
    }

    public static int setPromotion(PromotionModel promotion) {
        if (promotion == null) {
            Alerts.warnSelectToModify("promotion");
            return -1; // Error: No promotion selected
        }
        else {
            Session.setPromotion(promotion);
            return 0; // Success: Promotion set
        }
    }

    public static int deletePromotion(PromotionModel promotion) {
        if (promotion == null) {
            Alerts.warnSelectToDelete("promotion");
            return -1; // Error: No promotion selected
        }
        else {
            if (promotion.getStartDate().isBefore(LocalDate.now())) {
                Alerts.warnCannotDeleteBegunPromotion();
                return -1; // Error: Cannot delete begun promotion
            }

            boolean result = RepositorySQL.deletePromotion(promotion.getID());
            if (result) {
                Alerts.confirmDelete("Promotion");
                return 0; // Success: Promotion deleted
            } else {
                Alerts.warnFailedToDelete("promotion");
                return -1; // Error: Failed to delete promotion
            }
        }
    }
}
