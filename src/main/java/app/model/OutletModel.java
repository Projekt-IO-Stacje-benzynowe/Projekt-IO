package app.model;

/**
 *  Model representing an outlet.
 */
public class OutletModel {
    private Integer id = null;
    private String name = null;
    private String address = null;
    private String city = null;
    private String postalCode = null;
    private String region = null;

    public OutletModel(Integer id, String name, String address, String city, String postalCode, String region){
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.region = region;
    }
    public Integer getID(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getAddress(){
        return this.address;
    }
    public String getCity(){
        return this.city;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public String getRegion(){
        return this.region;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
