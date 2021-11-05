package by.gsu.epamlab;

public class Product {
    private String productName;
    private Byn price;

    public Product() {

    }

    public Product(String productName, Byn price) {
        this.productName = productName;
        this.price = price;
    }

    public Product(Product product) {
        this(product.productName, product.price);
    }

    public Byn mulPrice(int a) {
        return new Byn(price).mul(a);
    }

    public Byn diffPrice(Byn byn) {
        return new Byn(price).diff(byn);
    }


    @Override
    public String toString() {
        return productName + ";" + price;
    }
}
