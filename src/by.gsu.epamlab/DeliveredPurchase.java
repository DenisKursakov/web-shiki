package by.gsu.epamlab;

public class DeliveredPurchase extends AbstractPurchase {
    private Byn transportCosts;

    public DeliveredPurchase() {
    }


    public DeliveredPurchase(Product product, int numberOfUnits, Byn transportCosts) {
        super(product, numberOfUnits);
        this.transportCosts = transportCosts;
    }


    public Byn getTransportCosts() {
        return transportCosts;
    }

    public void setTransportCosts(Byn transportCosts) {
        this.transportCosts = transportCosts;
    }


    protected String fieldsToString() {
        return super.fieldsToString() + ";" + transportCosts;
    }

    @Override
    protected Byn costCalculation() {
        return new Byn(getProduct().getPrice()).mul(getNumberOfUnits()).add(transportCosts);
    }
}



