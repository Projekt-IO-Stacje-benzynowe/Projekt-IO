package app.model;

public class PromotionsModel {
    private Integer ID = null;
    private String name = null;

    public PromotionsModel(Integer ID, String name){
        this.ID = ID;
        this.name = name;
    }
    public Integer getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
}
