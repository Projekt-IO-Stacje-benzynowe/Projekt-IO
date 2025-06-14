package app.controllers.branch_panel.discount_section;
import java.util.List;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PanelList;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import app.model.PromotionModel;

import app.service.Session;
import app.service.branch_panel.discount_section.Promotions;

public class DiscountTableController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }
    @FXML
    private TableColumn<PromotionModel, String> nameColumn;

    @FXML
    private TableColumn<PromotionModel, String> descColumn;

    @FXML
    private TableView<PromotionModel> table;


    // initialize the table and fill it with data
    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue().getName()));

        descColumn.setCellValueFactory(cellData ->
            new ReadOnlyStringWrapper(cellData.getValue().getDesc()));

        List<PromotionModel> promotions = Promotions.GetPromotions(Session.getUser().getNameBranch());
        table.getItems().addAll(promotions);
    }

    @FXML
    public void goBackButton(){
        mainController.showDynamicContent("branch");
    }

}
