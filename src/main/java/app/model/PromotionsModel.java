package app.model;

public class PromotionsModel {
    private String name = null;
    private String desc = null;

    public PromotionsModel(String name, String description){
        this.name = name;
        this.desc = description;
    }
    public String getName(){
        return this.name;
    }
    public String getDesc(){
        return this.desc;
    }
}
