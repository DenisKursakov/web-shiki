package by.epam.lab.threads;

import by.epam.lab.services.Drop;

import static by.epam.lab.utils.Constants.*;

public class Reader implements Runnable {
    private Drop drop;

    public Reader(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        for (String message = drop.take();
             !MESSAGE_PUT.equals(message);
             message = drop.take()) {
            System.out.println(MESSAGE_PUT + message);
        }
    }
}
