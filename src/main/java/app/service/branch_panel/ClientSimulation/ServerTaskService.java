package app.service.branch_panel.ClientSimulation;

import app.db.repo.RepositorySQL;
import app.model.RewardModel;
import app.service.Session;

import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *  ServerTaskService class for processing purchase data from clients.
 *  It reads data from a server, parses it, and updates the RewardsToIssuance and Sales tables in the database.
 *  The service runs indefinitely, processing incoming data every 3 seconds.
 */
public class ServerTaskService {

    public void run(){
        ServerService server = new ServerService(9999);
        while (true) {              
            // we parse the data, then prepare it for the RewardsToIssuance table. 
            // we also take care of consistency with the Sales table

            Map<String, Integer>  dataMap = ParserService.parseJson(server.read());

            Integer productID = dataMap.get("productID");
            Integer quantity = dataMap.get("quantity");

            List<Integer> promotions = RepositorySQL.findPromotionsByProductID(productID);


            // it is possible that one product has more than one promotion
            List<RewardModel> rewards = new ArrayList<>();
            for(int promotionID : promotions){
                List<RewardModel> res = RepositorySQL.findProductReward(promotionID);
                for(RewardModel reward : res){
                    rewards.add(reward);
                }
            }
            // fetch next valid id from tables
            int nextSaleID = RepositorySQL.getMaxSaleID() + 1;
            int nextIssuanceID = RepositorySQL.getMaxIssuanceID() + 1; 
            double priceBeforePromotion = RepositorySQL.getProductPriceById(productID);


            // inserting data to RewardsToIssuance and Sales tables
            for(RewardModel reward : rewards){
                int issued = quantity / reward.getRequiredProductsNumber();

                if(!Session.isUserInitialized()) break;
                int OutletID = RepositorySQL.findOutletIDByName(Session.getUser().getNameBranch());

                double priceAfterPromotion = RepositorySQL.getPromotionPrice(reward.getPromotionID());

                Date month = Date.valueOf(LocalDate.now());

                RepositorySQL.insertRewardToIssuance
                (
                nextIssuanceID,
                reward.getRewardProductID(),
                reward.getPromotionID(),
                OutletID,
                month,
                quantity,
                productID,
                quantity,
                generateNotes(issued, reward.getRequiredProductsNumber())
                );

                RepositorySQL.insertSale
                (
                nextSaleID,
                reward.getPromotionID(),
                OutletID,
                productID,
                month,
                quantity,
                quantity * priceAfterPromotion,
                quantity*priceBeforePromotion - quantity*priceAfterPromotion
                );

                nextSaleID++;
                nextIssuanceID++;
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
                break;
            }
        }
    }

    public static String generateNotes(int soldProducts, int requiredPerReward) {
        return String.format("Wydano za %d sprzedanych produktów (wymagane %d na nagrodę)", soldProducts, requiredPerReward);
    }

}
