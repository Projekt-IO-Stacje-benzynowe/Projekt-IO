package app.model;

public class PromotionModel {
    private Integer id = null;
    private String name = null;
    private String desc = null;

    public PromotionModel(Integer id, String name, String description){
        this.id = id;
        this.name = name;
        this.desc = description;
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
}
