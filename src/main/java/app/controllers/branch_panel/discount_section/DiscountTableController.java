package app.controllers.branch_panel.discount_section;

import java.util.List;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.model.PromotionModel;

import app.service.SceneManager;
import app.service.Session;
import app.service.branch_panel.discount_section.Promotions;

public class DiscountTableController {
    @FXML
    private TableColumn<PromotionModel, String> nameColumn;

    @FXML
    private TableColumn<PromotionModel, String> descColumn;

    @FXML
    private TableView<PromotionModel> table;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue().getName()));

        descColumn.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue().getDesc()));


    // do poprawki, przeneis do service
        List<PromotionModel> promotions = Promotions.GetPromotions(Session.user.getNameBranch());
        table.getItems().addAll(promotions);
    }

    @FXML
    public void goBackButton(){
        SceneManager.showScene("branch");
    }

}
