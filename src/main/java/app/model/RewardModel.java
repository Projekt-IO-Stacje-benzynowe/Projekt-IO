package app.model;

public class RewardModel{
    Integer RewardProductID;
    Integer PromotionID;
    String Name;
    Integer UnitPrice;
    Integer RequiredProductsNumber;
    Integer ProductID;

    public RewardModel(){};

    public RewardModel(Integer rpID, Integer pID, String Name, Integer up, Integer rpn, Integer ProductID){
        this.RewardProductID = rpID;
        this.PromotionID = pID;
        this.Name = Name;
        this.UnitPrice = up;
        this.RequiredProductsNumber = rpn;
        this.ProductID = ProductID;
    }

    public Integer getRewardProductID(){
        return RewardProductID;
    }
    public Integer getPromotionID(){
        return PromotionID;
    }
    public Integer getUnitPrice(){
        return UnitPrice;
    }
    public Integer getRequiredProductsNumber(){
        return RequiredProductsNumber;
    }
    public String getName(){
        return Name;
    }

    public Integer getProductID(){
        return ProductID;
    }

}
