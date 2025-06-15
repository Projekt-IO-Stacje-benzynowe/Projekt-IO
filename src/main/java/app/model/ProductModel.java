package app.model;

public class ProductModel {
    Integer id = null;
    String name = null;
    String brand = null;

    public ProductModel(Integer id, String name, String brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

    public Integer getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }
}
