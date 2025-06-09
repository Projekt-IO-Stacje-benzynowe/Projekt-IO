package app.service;

import java.time.LocalDate;
import java.util.Arrays;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.model.PromotionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableService {
    private static <E, T> TableColumn<E, T> createTableColumn(String columnName, String propertyName, int width) {
        TableColumn<E, T> column = new TableColumn<E, T>(columnName);
        column.setMinWidth(width);
        column.setCellValueFactory(
            new PropertyValueFactory<E, T>(propertyName));
        return column;
    }

    public static TableView<PromotionModel> getPromotionsForOutletTable(String outletName) {
        TableView<PromotionModel> promotionsTableView = new TableView<>();
        promotionsTableView.setEditable(true);
        promotionsTableView.setMaxWidth(400);
        TableColumn<PromotionModel, String> promotionNameCol = createTableColumn("Promotion name", "name", 200);

        TableColumn<PromotionModel, String> promotionDescCol = createTableColumn("Description", "desc", 200);

        promotionsTableView.setItems(RepositorySQL.getPromotionsForOutlet(outletName));
        promotionsTableView.getColumns().addAll(Arrays.asList(promotionNameCol, promotionDescCol));
        return promotionsTableView;
    }

    public static TableView<PromotionModel> getAllPromotionsTable() {
        TableView<PromotionModel> promotionsTableView = new TableView<>();
        promotionsTableView.setEditable(true);
        promotionsTableView.setPrefWidth(400);
        TableColumn<PromotionModel, String> promotionNameCol = createTableColumn("Promotion name", "name", 200);

        TableColumn<PromotionModel, String> promotionDescCol = createTableColumn("Description", "desc", 200);

        promotionsTableView.setItems(RepositorySQL.getAllPromotions());
        promotionsTableView.getColumns().addAll(Arrays.asList(promotionNameCol, promotionDescCol));
        return promotionsTableView;
    }

    public static TableView<OutletModel> getAllOutletsTable() {
        TableView<OutletModel> outletsTableView = new TableView<>();
        outletsTableView.setEditable(true);
        outletsTableView.setPrefWidth(400);
        TableColumn<OutletModel, String> outletNameCol = createTableColumn("Name", "name", 100);

        TableColumn<OutletModel, String> outletAddressCol = createTableColumn("Address", "address", 100);

        TableColumn<OutletModel, String> outletCityCol = createTableColumn("City", "city", 100);

        TableColumn<OutletModel, String> outletPostalCodeCol = createTableColumn("Postal Code", "postalCode", 100);

        TableColumn<OutletModel, String> outletRegionCol = createTableColumn("Region", "region", 100);

        outletsTableView.setItems(RepositorySQL.getAllOutlets());
        outletsTableView.getColumns().addAll(Arrays.asList(outletNameCol, outletAddressCol, outletCityCol, outletPostalCodeCol, outletRegionCol));
        outletsTableView.setMaxWidth(600);
        return outletsTableView;
    }

    public static TableView<DeliveryModel> getDeliveriesForOutletTable(Integer outletID){
        TableView<DeliveryModel> deliveriesTableView = new TableView<>();
        deliveriesTableView.setEditable(true);
        deliveriesTableView.setPrefWidth(400);
        TableColumn<DeliveryModel, String> rewardNameCol = createTableColumn("Name", "rewardName", 100);

        TableColumn<DeliveryModel, Integer> deliveryQuantityCol = createTableColumn("Quantity", "quantity", 100);

        TableColumn<DeliveryModel, LocalDate> shipmentDateCol = createTableColumn("Shipment date", "shipmentDate", 100);

        deliveriesTableView.setItems(RepositorySQL.getDeliveriesForOutlet(outletID));
        deliveriesTableView.getColumns().addAll(Arrays.asList(rewardNameCol, deliveryQuantityCol, shipmentDateCol));
        deliveriesTableView.setMaxWidth(600);
        return deliveriesTableView;
    }
}
