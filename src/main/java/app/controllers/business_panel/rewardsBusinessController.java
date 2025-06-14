package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.ProductModel;
import app.model.PromotionModel;
import app.model.RewardModel;
import app.service.SceneManager;
import app.service.Session;
import app.service.control_panel.rewards.MainService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;


public class rewardsBusinessController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) { // funkcja, która pozwala przypisać główny kontroler do tego kontrolera
        this.mainController = mainController;
    }
    @FXML
    private StackPane tablePane; // stackPane do ładowania m.in tabelek
    private TableView <RewardModel> RewardTable;
    @FXML
    private Text testText;
    public void initialize() { //inicjalizacja głównego Panelu tabelką z promocjami
        try {
            RewardTable =
            RewardTable.setOnMouseClicked(e -> clickTable(e));
            tablePane.getChildren().add(RewardTable);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickTable(MouseEvent event) {
        String text = RewardTable.getSelectionModel().getSelectedItem().getName();
        testText.setText(text);
    }

    public void goToChoice(ActionEvent actionEvent) {
        if(RewardTable.getSelectionModel().getSelectedItem()!=null){
            Session.setProduct(RewardTable.getSelectionModel().getSelectedItem());
            SceneManager.addScene("choose_product_business");
            mainController.showDynamicContent("choose_product_business");
    }
}
