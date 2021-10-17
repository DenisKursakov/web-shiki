package by.gsu.epamlab;

public class BusinessTrip {
    private static final int DAILY_ALLOWANCE = 500; // BYN (bunks)
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

    private static String converter(int a) {
        if (a < 0) {
            return "Отрицательное число не может быть обработано";
        }
        if (a < 10) {
            return "0.0" + a;
        }
        if (a < 100) {
            return "0." + a;
        }
        String st = String.valueOf(a);
        return String.format("%s.%s", st.substring(0, st.length() - 2), st.substring(st.length() - 2));

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
                converter(DAILY_ALLOWANCE), employeeAccount, converter(transportationExpenses), numberOfDays, converter(getTotal()));
    }

    @Override
    public String toString() {
        return String.format("%s;%s;%d;%s", employeeAccount, converter(transportationExpenses), numberOfDays, converter(getTotal()));
    }
}

