package app.controllers.business_panel;

import app.model.ProductModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.business_panel.ProductsMainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import app.controllers.shared.MainController;
import app.controllers.shared.DynamicContentController;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;



public class productPageController implements DynamicContentController {
    public StackPane tablePane;
    private MainController mainController;
    private TableView <ProductModel> ProductTable;

    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
    }

    @FXML
    public void initialize() {
        ProductTable = ProductsMainService.getAllProducts();
        ProductTable.setOnMouseClicked(e -> clickTable(e));
        tablePane.getChildren().add(ProductTable);
    }
    public void clickTable(MouseEvent event) {
        String text = ProductTable.getSelectionModel().getSelectedItem().getName();
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
