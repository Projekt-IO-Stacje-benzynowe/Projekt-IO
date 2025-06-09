package app.model;

public class RewardModel{
    Integer rewardProductID;
    Integer promotionID;
    String name;
    Integer unitPrice;
    Integer requiredProductsNumber;
    Integer productID;

    public RewardModel(){};

    public RewardModel(Integer rpID, Integer pID, String name, Integer up, Integer rpn, Integer productID){
        this.rewardProductID = rpID;
        this.promotionID = pID;
        this.name = name;
        this.unitPrice = up;
        this.requiredProductsNumber = rpn;
        this.productID = productID;
    }

    public Integer getRewardProductID(){
        return rewardProductID;
    }
    public Integer getPromotionID(){
        return promotionID;
    }
    public Integer getUnitPrice(){
        return unitPrice;
    }
    public Integer getRequiredProductsNumber(){
        return requiredProductsNumber;
    }
    public String getName(){
        return name;
    }
    public Integer getProductID(){
        return productID;
    }

    @Override
    public String toString() {
        return name;
    }

}
