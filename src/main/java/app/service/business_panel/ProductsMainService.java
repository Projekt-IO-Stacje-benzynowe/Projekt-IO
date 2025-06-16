package app.service.business_panel;

import app.model.ProductModel;
import app.service.TableService;

import javafx.scene.control.TableView;

/**
 * Service class for managing products in the business panel.
 * Provides methods to retrieve product data.
 */
public class ProductsMainService {
    public static TableView<ProductModel> getAllProducts() {
       return TableService.getAllProdutsTable();
    }
}
