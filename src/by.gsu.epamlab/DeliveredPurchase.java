package by.gsu.epamlab;

public class DeliveredPurchase extends AbstractPurchase {
    private final Byn transportCosts;

    public DeliveredPurchase() {
        super();
        this.transportCosts = new Byn(0);
    }

    public DeliveredPurchase(Product product, int numberOfUnits, Byn transportCosts) {
        super(product, numberOfUnits);
        this.transportCosts = transportCosts;
    }

    public Byn getTransportCosts() {
        return transportCosts;
    }

    protected String fieldsToString() {
        return super.fieldsToString() + ";" + transportCosts;
    }

    @Override
    protected Byn costCalculation(Byn baseCost) {
        return new Byn(baseCost).add(transportCosts);
    }
}



