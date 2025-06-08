package app.service.branch_panel.Rewards;


import app.db.repo.RepositorySQL;
import app.model.RewardToIssuanceModel;
import app.service.Session;

import java.util.List;

public class RewardsOperation {
    public static void deleteIssuancedReward(int issuanceID){
        RepositorySQL.deleteByIssuanceID(issuanceID);
    }

    public static void insertRewardToIssuance(RewardToIssuanceModel data){
        RepositorySQL.insertRewardIssuance(data);
    }

    public static List<RewardToIssuanceModel> getRewardsToIssuance(){
        return RepositorySQL.FetchRewardToIssuance(RepositorySQL.findStationIDByName(Session.User.getNameBranch()));
    }

}
