package app.controllers.business_panel;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PromotionModel;
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
    private TableView<PromotionModel> PromotionsTableView;
    @FXML
    private StackPane tablePane; // stackPane do ładowania m.in tabelek

    @FXML
    private Text testText;
    public void initialize() { //inicjalizacja głównego Panelu tabelką z promocjami
        try {
            PromotionsTableView = MainService.getAllPromotions();
            PromotionsTableView.setOnMouseClicked(e -> clickTable(e));
            tablePane.getChildren().add(PromotionsTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickTable(MouseEvent event) {
        String text = PromotionsTableView.getSelectionModel().getSelectedItem().getName();
        testText.setText(text);
    }

}
