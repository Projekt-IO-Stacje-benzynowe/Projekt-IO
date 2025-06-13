package app.service.business_panel;

import app.model.OutletModel;
import app.model.ProductModel;
import app.service.TableService;
import javafx.scene.control.TableView;

public class ProductsMainService {
    public static TableView<ProductModel> getAllProducts() {
       return TableService.getAllProdutsTable();
    }
}
