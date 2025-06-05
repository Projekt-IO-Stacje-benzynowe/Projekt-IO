package app.model;


import java.sql.Date;


public class RewardToIssuanceModel extends RewardModel{
    public Integer issuanceID;
    public Integer totalValue;
    public String notes;
    public Date month;
    public Integer stationID;

    public RewardToIssuanceModel(int x, int quan){
        issuanceID = x;
        totalValue= quan;
    }

    public RewardToIssuanceModel(
        Integer IssuanceID,
        Integer RewardProductID,
        Integer PromotionID,
        Integer StationID,
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
        this.stationID = StationID;
    }

    public Integer getIssuanceID() {
        return issuanceID;
    }
    public Integer getTotalValue() {
        return totalValue;
    }
    
    public Integer getStationID(){
        return stationID;
    }

    public Date getMonth(){
        return this.month;
    }
    public String getNotes(){
        return this.notes;
    }


}
