package by.gsu.epamlab;

public class BusinessTrip {
    private final static int DAILY_ALLOWANCE = 500; // BYN (bunks)
    private String employeeAccount;
    private int transportationExpenses;                      // BYN (bunks)
    private int numberOfDays;

    public BusinessTrip() {

    }

    public BusinessTrip(String employeeAccount, int transportationExpenses, int numberOfDays) {
        this.employeeAccount = employeeAccount;
        this.transportationExpenses = transportationExpenses;
        this.numberOfDays = numberOfDays;
    }

    private static String convert(int a) {
        return String.format("%d.%02d", a / 100, a % 100);
    }


    public String getEmployeeAccount() {
        return employeeAccount;
    }

    public void setEmployeeAccount(String employeeAccount) {
        this.employeeAccount = employeeAccount;
    }

    public int getTransportationExpenses() {
        return transportationExpenses;
    }

    public void setTransportationExpenses(int transportationExpenses) {
        this.transportationExpenses = transportationExpenses;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getTotal() {
        return transportationExpenses + numberOfDays * DAILY_ALLOWANCE;
    }

    public void show() {
        System.out.printf("Daily_allowance = %s \nAccount = %s\nTransport = %s\nDays = %d\nTotal = %s%n",
                convert(DAILY_ALLOWANCE), employeeAccount, convert(transportationExpenses), numberOfDays, convert(getTotal()));
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%d;%s", employeeAccount, convert(transportationExpenses), numberOfDays, convert(getTotal()));
    }
}

