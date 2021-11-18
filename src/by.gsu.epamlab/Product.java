package by.gsu.epamlab;

public class Product {
    private final String name;
    private final Byn price;

    public Product() {
        this(null, null);
    }

    public Product(String name, Byn price) {
        this.name = name;
        this.price = price;
    }

    public Product(Product product) {
        this(product.name, product.price);
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + ";" + price;
    }
}
