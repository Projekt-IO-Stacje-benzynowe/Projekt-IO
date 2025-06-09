package app.service.branch_panel.ClientSimulation;

import java.util.Map;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import app.db.repo.RepositorySQL;
import app.model.RewardModel;

import app.service.Session;

public class ServerTask{
    public ServerTask(){};

    public void run(){
        Server server = new Server(9999);

        while (true) {  
            System.out.println("Odebrano wiadomość: " + server.read());
            Map<String, Integer>  dataMap = Parser.parseJson(server.read());

            Integer productID = dataMap.get("productID");
            Integer quantity = dataMap.get("quantity");

            System.out.println(productID);
            System.out.println(quantity);

            List<Integer> promotions = RepositorySQL.findPromotionsByProductID(productID);

            for(Integer x : promotions){
                System.out.print(x + " ");
            }

            List<RewardModel> rewards = new ArrayList<>();
            for(int promotionID : promotions){
                List<RewardModel> res = RepositorySQL.findProductReward(promotionID);
                for(RewardModel reward : res){
                    rewards.add(reward);
                }
            }
            System.out.println(Session.user.getNameBranch());
            
            int nextSaleID = RepositorySQL.getMaxSaleID() + 1;
            int nextIssuanceID = RepositorySQL.getMaxIssuanceID() + 1; 
            double priceBeforePromotion = RepositorySQL.getProductPriceById(productID);

            for(RewardModel reward : rewards){
                int issued = quantity / reward.getRequiredProductsNumber();

                if(Session.user == null) break;
                int OutletID = RepositorySQL.findOutletIDByName(Session.user.getNameBranch());

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
                System.out.println("Wątek został przerwany");
                break;
            }
        }
    }

    public static String generateNotes(int soldProducts, int requiredPerReward) {
        return String.format("Wydano za %d sprzedanych produktów (wymagane %d na nagrodę)", soldProducts, requiredPerReward);
    }

}
