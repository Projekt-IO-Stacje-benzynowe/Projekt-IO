package app.controllers.business_panel;

import app.model.ProductModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.business_panel.ProductsMainService;
import app.controllers.shared.MainController;
import app.controllers.shared.DynamicContentController;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;


/**
 *  Controller for managing the product page in the business panel.
 */
public class productPageController implements DynamicContentController {
    public StackPane tablePane;
    private MainController mainController;
    private TableView <ProductModel> ProductTable;

    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        ProductTable = ProductsMainService.getAllProducts();
        tablePane.getChildren().add(ProductTable);
    }

    public void goToChooseProduct(){
        if(ProductTable.getSelectionModel().getSelectedItem()!=null){
            Session.setProduct(ProductTable.getSelectionModel().getSelectedItem());
            SceneManager.addScene("choose_product_business");
            mainController.showDynamicContent("choose_product_business");
        }
        else {

        }
    }
}
