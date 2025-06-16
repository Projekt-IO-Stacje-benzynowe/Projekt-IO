package app.service.branch_panel.Rewards;


import app.db.repo.RepositorySQL;
import app.model.RewardToIssuanceModel;
import app.service.Session;

import java.util.List;

/**
 * Service for handling rewards operations in the branch panel.
 */
public class RewardsOperation {
    public static void deleteIssuancedReward(int issuanceID){
        RepositorySQL.deleteByIssuanceID(issuanceID);
    }

    public static void insertRewardToIssuance(RewardToIssuanceModel data){
        RepositorySQL.insertRewardIssuance(data);
    }

    public static List<RewardToIssuanceModel> getRewardsToIssuance(){
        return RepositorySQL.FetchRewardToIssuance(RepositorySQL.findOutletIDByName(Session.getUser().getNameBranch()));
    }

}
