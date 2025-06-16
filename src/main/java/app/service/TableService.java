package app.service;

import app.db.repo.RepositorySQL;
import app.model.DeliveryModel;
import app.model.OutletModel;
import app.model.ProductModel;
import app.model.PromotionModel;
import app.model.RequestModel;
import app.model.RewardModel;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Service class for managing tables in the application.
 * Provides methods to create and retrieve various table views.
 */
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

    public static TableView<RequestModel> getRequestsForOutletTable(Integer outletID) {
        TableView<RequestModel> requestsTableView = new TableView<>();
        requestsTableView.setEditable(true);
        requestsTableView.setPrefWidth(400);

        TableColumn<RequestModel, String> rewardNameCol = createTableColumn("Reward", "rewardName", 100);

        TableColumn<RequestModel, Integer> quantityCol = createTableColumn("Quantity", "quantity", 100);

        TableColumn<RequestModel, LocalDate> reportDateCol = createTableColumn("Request date", "reportDate", 100);

        requestsTableView.setItems(RepositorySQL.getRequestsForOutlet(outletID));
        requestsTableView.getColumns().addAll(Arrays.asList(rewardNameCol, quantityCol, reportDateCol));
        requestsTableView.setMaxWidth(600);
        return requestsTableView;
    }

    public static TableView<ProductModel> getAllProdutsTable() {
        TableView<ProductModel> productsTableView = new TableView<>();
        productsTableView.setEditable(true);
        productsTableView.setPrefWidth(400);

        TableColumn<ProductModel, String> productNameCol = createTableColumn("Product name", "name", 200);
        TableColumn<ProductModel, String> productBrandCol = createTableColumn("Brand", "brand", 200);

        productsTableView.setItems(RepositorySQL.getAllProducts());
        productsTableView.getColumns().addAll(Arrays.asList(productNameCol, productBrandCol));
        productsTableView.setMaxWidth(600);
        return productsTableView;
    }

    public static TableView<RewardModel> getAllRewardsTable() {
        TableView<RewardModel> rewardsTableView = new TableView<>();
        rewardsTableView.setEditable(true);
        rewardsTableView.setPrefWidth(400);

        TableColumn<RewardModel, String> rewardNameCol = createTableColumn("Reward name", "name", 200);

        rewardsTableView.setItems(RepositorySQL.getAllRewards());
        rewardsTableView.getColumns().addAll(Arrays.asList(rewardNameCol));
        rewardsTableView.setMaxWidth(600);
        return rewardsTableView;
    }
}
