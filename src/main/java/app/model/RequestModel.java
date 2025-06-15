package app.model;

import java.time.LocalDate;

/**
 * Model representing a request.
 */
public class RequestModel {
    private Integer deliveryID = null;
    private Integer rewardID = null;
    private String rewardName = null;
    private Integer quantity = null;
    private LocalDate reportDate = null;

    public RequestModel(Integer deliveryID, Integer rewardID, String rewardName, Integer quantity, LocalDate reportDate) {
        this.deliveryID = deliveryID;
        this.rewardID = rewardID;
        this.rewardName = rewardName;
        this.quantity = quantity;
        this.reportDate = reportDate;
    }

    public Integer getDeliveryID() {
        return deliveryID;
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

    public LocalDate getReportDate() {
        return reportDate;
    }
}
