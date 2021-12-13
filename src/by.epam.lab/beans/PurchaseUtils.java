package by.epam.lab.beans;

import by.epam.lab.Constants;


public class PurchaseUtils<T extends Priceable, T2 extends Number> {
    private Purchase<T, T2> purchase;

    public PurchaseUtils() {
        this(null);
    }

    public PurchaseUtils(Purchase<T, T2> purchase) {
        this.purchase = purchase;
    }

    public Purchase<T, T2> getPurchase() {
        return purchase;
    }

    public void setPurchase(Purchase<T, T2> purchase) {
        this.purchase = purchase;
    }

    public void printPurchase() {
        System.out.println(purchase);
    }

    public void printCost() {
        System.out.println(Constants.COST + purchase.getCost() + Constants.BYN);
    }

    public void printCostDiff(Purchase<T, T2> p) {
        String diffPurchase = Constants.EMPTY_STRING;
        Byn p1Cost = purchase.getCost();
        Byn p2Cost = p.getCost();
        int value = p1Cost.compareTo(p2Cost);
        Byn diffByn = new Byn(0);
        if (value > 0) {
            diffPurchase = Constants.POSITIVE_DIFF;
            diffByn = p1Cost.diff(p2Cost);
        } else if (value < 0) {
            diffPurchase = Constants.NEGATIVE_DIFF;
            diffByn = p2Cost.diff(p1Cost);
        }
        System.out.println(diffPurchase + Constants.DIFF + Constants.EQUAL_SIGN
                + diffByn + Constants.BYN);
    }

    public void printIsSameCost(Purchase<?, T2>... purchases) {
        boolean purchaseWasFound = false;
        for (Purchase<?, T2> p : purchases) {
            if (purchase.getCost().compareTo(p.getCost()) == 0) {
                purchaseWasFound = true;
                break;
            }
        }
        System.out.println(purchaseWasFound ?
                Constants.PURCHASE_EXIST : Constants.PURCHASE_DOES_NOT_EXIST);
    }
}
