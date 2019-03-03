package eliana.cappello.models;

public class Product {

    private String title;
    private int kcal_per_100g;
    private double unit_price;
    private String description;

    public Product(){}

    public Product(String title, int kcal100g, double unitPrice, String description) {
        this.title = title;
        this.kcal_per_100g = kcal100g;
        this.unit_price = unitPrice;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getKcal_per_100g() {
        return kcal_per_100g;
    }

    public void setKcal_per_100g(int kcal_per_100g) {
        this.kcal_per_100g = kcal_per_100g;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
