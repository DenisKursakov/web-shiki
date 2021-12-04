package by.epam.lab;

public class Purchase {
    private String name;
    private Byn price;
    private int numberOfUnits;

    public Purchase() {

    }

    public Purchase(String name, Byn price, int numberOfUnits) {
        this.name = name;
        this.price = price;
        this.numberOfUnits = numberOfUnits;
    }

    public Purchase(String[] elements) {
        this.name = elements[Constants.IN_LINE_NAME];
        this.price = new Byn(Integer.parseInt(elements[Constants.IN_LINE_PRICE]));
        this.numberOfUnits = Integer.parseInt(elements[Constants.IN_LINE_NUMBER]);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byn getPrice() {
        return price;
    }

    public void setPrice(Byn price) {
        this.price = price;
    }

    public int getNumberOfUnits() {
        return numberOfUnits;
    }

    public void setNumberOfUnits(int numberOfUnits) {
        this.numberOfUnits = numberOfUnits;
    }

    public Byn getCost() {
        return new Byn(price).mul(numberOfUnits);
    }

    @Override
    public String toString() {
        return fieldsToString() + Constants.SEMICOLON + getCost();
    }

    protected String fieldsToString() {
        return name + Constants.SEMICOLON + price + Constants.SEMICOLON + numberOfUnits;
    }

}
