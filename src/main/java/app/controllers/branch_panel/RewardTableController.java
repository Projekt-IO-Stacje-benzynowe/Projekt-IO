package app.controllers.branch_panel;
import app.controllers.shared.DynamicContentController;
import app.controllers.shared.MainController;
import app.model.RewardToIssuanceModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;

import app.service.branch_panel.Rewards.RewardsOperation;
import app.service.Alerts;

public class RewardTableController implements DynamicContentController {
    private MainController mainController;
    @Override
    public void setMainController(app.controllers.shared.MainController mainController) { // funkcja, która pozwala
        this.mainController = mainController;
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


    // initialize the table and fill it with data
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
        
        // we remove selected reward from table and database
        if (selected != null) {
            rewardList.remove(selected);
            RewardsOperation.deleteIssuancedReward(selected.issuanceID);
            RewardsOperation.insertRewardToIssuance(selected);

        } else {
            Alerts.warnSelectToDelete("Select a row before confirming");
        }
    }
    @FXML
    public void goBack() {
        mainController.showDynamicContent("branch");
    }   
}

