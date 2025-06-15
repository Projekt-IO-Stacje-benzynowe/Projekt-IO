package app.model;

import java.time.LocalDate;

public class PromotionModel {
    private Integer id = null;
    private String name = null;
    private String desc = null;
    private Integer productID = null;
    private LocalDate startDate = null;
    private LocalDate endDate = null;
    private Double quantity = null;

    public PromotionModel(Integer id, String name, String description, Integer productID, LocalDate startDate, LocalDate endDate, Double quantity) {
        this.id = id;
        this.name = name;
        this.desc = description;
        this.productID = productID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.quantity = quantity;
    }
    public Integer getID(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }

    public String getDesc(){
        return this.desc;
    }

    public Integer getProductID(){
        return this.productID;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public Double getQuantity() {
        return this.quantity;
    }
}
