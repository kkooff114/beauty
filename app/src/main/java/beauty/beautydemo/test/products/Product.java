package beauty.beautydemo.test.products;

/**
 * Created by chenqiming on 2/3/15.
 */
public abstract class Product {
    private Color color;
    private String brand;
    private String name;
    private String discription;
    private int price;

    public Product(Color c, String brand, String name, String discription, int price) {
        this.color = c;
        this.name = name;
        this.brand = brand;
        this.discription = discription;
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public String getDiscription() {
        return discription;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }
}
