package app.controllers.branch_panel.discount_section;

import javafx.fxml.FXML;
import app.service.SceneManager;
import javafx.event.ActionEvent;

public class DiscountMenuController {
    @FXML

    /**
     * This method is called when the user clicks the "Go to Table" button.
     * It navigates to the "table_promotions" scene where the current promotions are displayed.
     * @param event
     */
    public void goToTable(ActionEvent event){
        SceneManager.addScene("table_promotions");
        SceneManager.showScene("table_promotions");
    }


}
