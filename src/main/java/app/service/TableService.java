package app.service;

import java.util.Arrays;
import java.util.List;

import app.db.repo.RepositorySQL;
import app.model.PromotionsModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableService {
    private static List<PromotionsModel> promotionsList;

    private static <E, T> TableColumn<E, T> createTableColumn(String columnName, String propertyName, int width) {
        TableColumn<E, T> column = new TableColumn<E, T>(columnName);
        column.setMinWidth(width);
        column.setCellValueFactory(
            new PropertyValueFactory<E, T>(propertyName));
        return column;
    }

    private static ObservableList<PromotionsModel> getPromotionsObservableList(String outletName){
        promotionsList = RepositorySQL.getPromotions(outletName);
        ObservableList<PromotionsModel> promotionsObservableList = FXCollections.observableArrayList();
        for (PromotionsModel promotion : promotionsList) {
            promotionsObservableList.add(promotion);
        }
        promotionsList = null;
        return promotionsObservableList;
    }

    public static TableView<PromotionsModel> getPromotionsTable(String promotionName) {
        TableView<PromotionsModel> promotionsTableView = new TableView<>();
        promotionsTableView.setEditable(true);
        promotionsTableView.setMaxWidth(400);
        TableColumn<PromotionsModel, String> promotionNameCol = createTableColumn("Promotion name", "name", 200);

        TableColumn<PromotionsModel, String> promotionDescCol = createTableColumn("Description", "desc", 200);

        promotionsTableView.setItems(getPromotionsObservableList(promotionName));
        promotionsTableView.getColumns().addAll(Arrays.asList(promotionNameCol, promotionDescCol));
        return promotionsTableView;
    }

    public static TableView<PromotionsModel> getAllPromotionsTable() {
        TableView<PromotionsModel> promotionsTableView = new TableView<>();
        promotionsTableView.setEditable(true);
        promotionsTableView.setPrefWidth(400);
        TableColumn<PromotionsModel, String> promotionNameCol = createTableColumn("Promotion name", "name", 200);

        TableColumn<PromotionsModel, String> promotionDescCol = createTableColumn("Description", "desc", 200);

        promotionsTableView.setItems(RepositorySQL.getAllPromotions());
        promotionsTableView.getColumns().addAll(Arrays.asList(promotionNameCol, promotionDescCol));
        return promotionsTableView;
    }
}
