package app.controllers.branch_panel.discount_section;

import javafx.fxml.FXML;
import app.controllers.SceneManager;
import javafx.event.ActionEvent;

public class DiscountMenuController {
    @FXML
    public void goToTable(ActionEvent event){
        //przenosi nas do nastepnego screena gdzie bedzie tabela z aktualnymi promocjami
        // List<PromotionsModel> promotions = RepositorySQL.GetPromotions(Session.User.getNameBranch());

        System.out.println("DiscountMenuController");

        SceneManager.addScene("table_promotions", "/resources/view/branch_panel/discount_panel_table_promotions.fxml");
        SceneManager.showScene("table_promotions");
    }


}
