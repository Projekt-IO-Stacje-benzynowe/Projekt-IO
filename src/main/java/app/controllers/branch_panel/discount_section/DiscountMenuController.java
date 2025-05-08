package app.controllers.branch_panel.discount_section;

import javafx.fxml.FXML;
import app.controllers.SceneManager;
import javafx.event.ActionEvent;

public class DiscountMenuController {
    @FXML
    public void goToTable(ActionEvent event){
        //przenosi nas do nastepnego screena gdzie bedzie tabela z aktualnymi promocjami
        // List<PromotionsModel> promotions = RepositorySQL.GetPromotions(Session.User.getNameBranch());
        SceneManager.addScene("table_promotions", "/view/branch_panel/discount_panel_table_promotions.fxml");
        // SceneManager.addScene("table_promotions", "/resources/view/branch_panel/discount_panel_table_promotions.fxml");
        SceneManager.showScene("table_promotions");
    }


}
