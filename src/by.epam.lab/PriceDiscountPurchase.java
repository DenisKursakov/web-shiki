package by.epam.lab;

public class PriceDiscountPurchase extends Purchase {
    private Byn discount;

    public PriceDiscountPurchase() {

    }

    public PriceDiscountPurchase(String name, Byn price, int numberOfUnits, Byn discount) {
        super(name, price, numberOfUnits);
        this.discount = new Byn(discount);
    }

    public PriceDiscountPurchase(String[] elements) {
        super(elements);
        discount = new Byn(Integer.parseInt(elements[Constants.IN_LINE_DISCOUNT]));
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = new Byn(discount);
    }

    public Byn getCost() {
        return new Byn(super.getPrice()).diff(discount).mul(getNumberOfUnits());
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + Constants.SEMICOLON + discount;
    }
}
