package app.controllers.branch_panel.discount_section;

import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.model.PromotionsModel;

import app.service.SceneManager;
import app.service.Session;
import app.service.branch_panel.discount_section.Promotions;

public class DiscountTableController {
    @FXML
    private TableColumn<PromotionsModel, String> nameColumn;

    @FXML
    private TableColumn<PromotionsModel, String> descColumn;

    @FXML
    private TableView<PromotionsModel> table;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue().getName()));

        descColumn.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue().getDesc()));


    // do poprawki, przeneis do service
        List<PromotionsModel> promotions = Promotions.GetPromotions(Session.User.getNameBranch());
        table.getItems().addAll(promotions);
    }

    @FXML
    public void goBackButton(){
        SceneManager.showScene("branch");
    }

}
