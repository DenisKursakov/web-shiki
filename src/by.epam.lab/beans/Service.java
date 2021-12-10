package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Service extends AbstractItem {
    private Byn totalCost;
    private int numberOfServiceUsers;

    public Service() {
        super();
    }

    public Service(String name, Byn totalCost, int numberOfServiceUsers) {
        super(name,totalCost.divide(numberOfServiceUsers, RoundMethod.ROUND, 2));
        this.totalCost = totalCost;
        this.numberOfServiceUsers = numberOfServiceUsers;
    }

    public Byn getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Byn totalCost) {
        this.totalCost = totalCost;
    }

    public int getNumberOfServiceUsers() {
        return numberOfServiceUsers;
    }

    public void setNumberOfServiceUsers(int numberOfServiceUsers) {
        this.numberOfServiceUsers = numberOfServiceUsers;
    }

    @Override
    protected String fieldToString() {
        return super.fieldToString() + Constants.SEMICOLON + totalCost
                + Constants.SEMICOLON + numberOfServiceUsers;
    }
}
