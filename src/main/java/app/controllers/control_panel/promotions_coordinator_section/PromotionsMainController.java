package app.controllers.control_panel.promotions_coordinator_section;

import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.PromotionModel;
import app.service.SceneManager;
import app.service.control_panel.promotions_coordinator_section.PromotionsMainService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;

/**
 *   Controller for the main view of the promotions coordinator section in the control panel.
 */
public class PromotionsMainController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private StackPane tablePane;
    @FXML
    private BorderPane contentPane;
    private TableView<PromotionModel> PromotionsTableView;
    public void initialize() {
        try {
            PromotionsTableView = PromotionsMainService.getAllPromotions();
            tablePane.getChildren().add(PromotionsTableView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToCreatePromotion(ActionEvent event) {
        SceneManager.addScene("create_promotion");
        mainController.showDynamicContent("create_promotion");
    }


    public void goToModifyPromotion(ActionEvent event) {
        int result = PromotionsMainService.setPromotion(PromotionsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            SceneManager.addScene("modify_promotion");
            mainController.showDynamicContent("modify_promotion");
        }
    }

    public void deletePromotion(ActionEvent event) {
        int result = PromotionsMainService.deletePromotion(PromotionsTableView.getSelectionModel().getSelectedItem());
        if (result == 0) {
            PromotionsTableView.getItems().remove(PromotionsTableView.getSelectionModel().getSelectedItem());
        }
    }

}
