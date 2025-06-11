package app.controllers.branch_panel;
import app.controllers.branch_panel.DynamicContentController;
import app.db.repo.RepositorySQL;
import app.model.RewardToIssuanceModel;
import app.service.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import app.service.branch_panel.Rewards.RewardsOperation;;


public class RewardTableController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
    @FXML
    private void goToAnotherView(String fxml) {
        if (mainController != null) {
            mainController.loadContent(fxml);
        }
    }
    @FXML
    private TableView<RewardToIssuanceModel> rewardTable;

    @FXML
    private TableColumn<RewardToIssuanceModel, Integer> issuanceCol;

    @FXML
    private TableColumn<RewardToIssuanceModel, Integer> rewardCol;

    @FXML
    private Button confirmButton;

    private final ObservableList<RewardToIssuanceModel> rewardList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        issuanceCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("issuanceID"));
        rewardCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("totalValue"));

        List<RewardToIssuanceModel> ls = RewardsOperation.getRewardsToIssuance();
        
        for(RewardToIssuanceModel reward : ls){
            rewardList.add(reward);
        }
        rewardTable.setItems(rewardList);

        confirmButton.setOnAction(e -> onConfirm());
    }

    @FXML
    private void onConfirm() {
        RewardToIssuanceModel selected = rewardTable.getSelectionModel().getSelectedItem();
        
        if (selected != null) {
            rewardList.remove(selected);
            RewardsOperation.deleteIssuancedReward(selected.issuanceID);
            RewardsOperation.insertRewardToIssuance(selected);

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brak wyboru");
            alert.setHeaderText(null);
            alert.setContentText("Wybierz wiersz przed potwierdzeniem.");
            alert.showAndWait();
        }
    }

    @FXML
    public void goBack() {
        SceneManager.clearScene("rewards_table");
        goToAnotherView("menu_branch");

    }   
}

