package by.epam.lab.beans;

import by.epam.lab.Constants;
import by.epam.lab.enums.RoundMethod;

public class Service implements Priceable {
    private String name;
    private Byn totalCost;
    private int numberOfServiceUsers;

    public Service() {
    }

    public Service(String name, Byn totalCost, int numberOfServiceUsers) {
        this.name = name;
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
    public Byn getPrice() {
        return totalCost.divide(numberOfServiceUsers, RoundMethod.CEIL, 0);
    }

    @Override
    public String toString() {
        return name + Constants.SEMICOLON + totalCost + Constants.SEMICOLON + numberOfServiceUsers;
    }
}
