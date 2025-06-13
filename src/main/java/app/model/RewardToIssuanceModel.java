package app.model;


import java.sql.Date;

// that class inherit from RewardModel because when we wanna issuance some reward we need some extra data
public class RewardToIssuanceModel extends RewardModel{
    public Integer issuanceID;
    public Integer totalValue;
    public String notes;
    public Date month;
    public Integer outletID;

    public RewardToIssuanceModel(int x, int quan){
        issuanceID = x;
        totalValue = quan;
    }

    public RewardToIssuanceModel(
        Integer IssuanceID,
        Integer RewardProductID,
        Integer PromotionID,
        Integer OutletID,
        Date Month,
        Integer QuantityIssued,
        Integer UnitPrice,
        Integer TotalValue,
        String notes
    ){
        super(RewardProductID, PromotionID, null, UnitPrice, null, null);
        this.month = Month;
        this.notes = notes;
        this.totalValue = TotalValue;
        this.issuanceID = IssuanceID;
        this.outletID = OutletID;
    }

    public Integer getIssuanceID() {
        return issuanceID;
    }
    public Integer getTotalValue() {
        return totalValue;
    }
    
    public Integer getOutletID(){
        return outletID;
    }

    public Date getMonth(){
        return this.month;
    }
    public String getNotes(){
        return this.notes;
    }


}
