package app.controllers.control_panel.rewards_coordinator_section;

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
import javafx.scene.layout.*;
import javafx.scene.text.Text;



public class RewardsMainPanel implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala przypisać główny kontroler do tego kontrolera
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

    public void goToCreateReward(ActionEvent event) {
        testText.setText("view requests?");
    }


    public void goToModifyReward(ActionEvent event) {
        Session.setPromotion(PromotionsTableView.getSelectionModel().getSelectedItem());
        SceneManager.addScene("modifyDelivery");
        mainController.showDynamicContent("modifyDelivery");
    }

}
