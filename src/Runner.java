import by.gsu.epamlab.BusinessTrip;

public class Runner {
    public static void main(String[] args) {
        BusinessTrip[] businessTrips = {
                new BusinessTrip("Kursakou Dzianis", 1720, 4),
                new BusinessTrip("Denisov Kirill", 2470, 7),
                null,
                new BusinessTrip("Ivanov Alex", 1700, 3),
                new BusinessTrip("Malikou Dzianis", 1530, 5),
                new BusinessTrip()
        };
        BusinessTrip tripWithMaxCost = businessTrips[0];
        for (BusinessTrip businessTrip : businessTrips) {
            if (businessTrip == null) {
                continue;
            } 
            businessTrip.show();

            if (businessTrip.getTotal() > tripWithMaxCost.getTotal()) {
                tripWithMaxCost = businessTrip;
            }
        }

        System.out.println("Max cost trip = " + tripWithMaxCost);
        businessTrips[businessTrips.length - 1].setTransportationExpenses(99);
        System.out.println("Total duration " + (businessTrips[0].getNumberOfDays() + businessTrips[1].getNumberOfDays())); //output the Total duration
        for (BusinessTrip businessTrip : businessTrips) {
            System.out.println(businessTrip);
        }

    }
}
