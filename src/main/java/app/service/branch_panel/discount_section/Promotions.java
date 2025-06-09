package app.service.branch_panel.discount_section;

import app.model.PromotionModel;
import app.db.repo.RepositorySQL;
import java.util.List;

public class Promotions {
    public static List<PromotionModel> GetPromotions(String branchName) {
        return RepositorySQL.getPromotionsForOutlet(branchName);
    }

}
