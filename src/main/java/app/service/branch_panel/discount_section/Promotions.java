package app.service.branch_panel.discount_section;

import app.model.PromotionsModel;
import app.db.repo.RepositorySQL;
import java.util.List;

public class Promotions {
    public static List<PromotionsModel> GetPromotions(String branchName) {
        return RepositorySQL.GetPromotions(branchName);
    }

}
