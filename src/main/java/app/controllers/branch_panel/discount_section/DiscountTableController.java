package app.controllers.branch_panel.discount_section;

import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.model.PromotionsModel;
import app.service.Session;

import app.service.branch_panel.discount_section.Promotions;;

public class DiscountTableController {
    @FXML
    private TableColumn<String, String> column;
    @FXML
    private TableView<String> table;
    
    @FXML
    public void initialize() {
        column.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()));
        List<PromotionsModel> promotions = Promotions.GetPromotions(Session.User.getNameBranch());

        for (PromotionsModel promotion : promotions) {
            table.getItems().add(promotion.getName());
        }
    }
}
