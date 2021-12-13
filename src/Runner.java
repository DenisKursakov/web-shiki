import by.epam.lab.beans.*;


public class Runner {
    public static void main(String[] args) {
        Purchase<Priceable, Number> p1 = new Purchase<>(
                new Product("Milk", new Byn(170)), 20);
        PurchaseUtils<Priceable, Number> pu1 = new PurchaseUtils<>(p1);
        pu1.printPurchase();
        pu1.printCost();
        Purchase<Priceable, Number> p2 = new Purchase<>(
                new Product("Sugar", new Byn(300)), 12.5);
        PurchaseUtils<Priceable, Number> pu2 = new PurchaseUtils<>(p2);
        pu2.printCost();
        pu2.printCostDiff(p1);
        Purchase<Priceable, Number> p3 = new Purchase<>(new DiscountProduct(
                "Sugar", new Byn(280), new Byn(10)), 60);

        PurchaseUtils<Service, Number> pu4 = new PurchaseUtils<>(new Purchase<>(new Service(
                "Gym workout", new Byn(7560), 5), 2.25));
        Service p4 = pu4.getPurchase().getItem();
        System.out.println(p4);
        pu4.printCost();
        pu2.printIsSameCost(p1, p3, pu4.getPurchase());

    }
}

