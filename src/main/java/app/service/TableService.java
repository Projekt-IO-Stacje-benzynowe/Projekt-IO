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

    private static ObservableList<PromotionsModel> getPromotionsObservableList(String promotionName){
        promotionsList = RepositorySQL.GetPromotions(promotionName);
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

        TableColumn<PromotionsModel, String> promotionNameCol = createTableColumn("Promotion name", "name", 100);

        TableColumn<PromotionsModel, String> promotionDescCol = createTableColumn("Description", "desc", 100);

        promotionsTableView.setItems(getPromotionsObservableList(promotionName));
        promotionsTableView.getColumns().addAll(Arrays.asList(promotionNameCol, promotionDescCol));
        return promotionsTableView;
    }
}
