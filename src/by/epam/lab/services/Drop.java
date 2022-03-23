package by.epam.lab.services;

import static by.epam.lab.utils.Constants.*;

public class Drop {
    private String message;
    private boolean isEmpty = true;

    public synchronized String take() {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(MESSAGE_GOT + message);
        isEmpty = true;
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = false;
        this.message = message;
        notifyAll();
    }
}
