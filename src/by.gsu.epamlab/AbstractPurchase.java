package by.gsu.epamlab;


public abstract class AbstractPurchase implements Comparable<AbstractPurchase> {
    private final Product product;
    private int numberOfUnits;

    public AbstractPurchase() {
        this(null, 0);
    }

    public AbstractPurchase(Product product, int numberOfUnits) {
        this.product = product;
        this.numberOfUnits = numberOfUnits;
    }

    public Product getProduct() {
        return product;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Byn getCost() {
        Byn baseCost = product.getPrice().mul(numberOfUnits);
        Byn finalCost = costCalculation(baseCost);
        return finalCost.round(RoundMethod.FLOOR, 2);
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public int compareTo(AbstractPurchase abstractPurchase) {
        return getCost().compareTo(abstractPurchase.getCost());
    }

    protected String fieldsToString() {
        return product + ";" + numberOfUnits;
    }

    protected abstract Byn costCalculation(Byn baseCost);
}
