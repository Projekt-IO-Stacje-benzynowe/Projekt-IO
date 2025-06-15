package app.model;

import java.time.LocalDate;

/**
 *  Model representing a delivery.
 */
public class DeliveryModel {
    private Integer id = null;
    private Integer outletID = null;
    private Integer rewardID = null;
    private String rewardName = null;
    private Integer quantity = null;
    private LocalDate shipmentDate = null;

    public DeliveryModel(Integer id, Integer outletID, Integer rewardID, String rewardName, Integer quantity, LocalDate shipmentDate) {
        this.id = id;
        this.outletID = outletID;
        this.rewardID = rewardID;
        this.rewardName = rewardName;
        this.quantity = quantity;
        this.shipmentDate = shipmentDate;
    }

    public Integer getId() {
        return id;
    }
    
    public Integer getOutletID() {
        return outletID;
    }

    public Integer getRewardID() {
        return rewardID;
    }
    
    public String getRewardName() {
        return rewardName;
    }
    
    public Integer getQuantity() {
        return quantity;
    }

    public LocalDate getShipmentDate() {
        return shipmentDate;
    }
}
