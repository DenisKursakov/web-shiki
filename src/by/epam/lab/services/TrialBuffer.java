package by.epam.lab.services;

import by.epam.lab.beans.FakeTrial;
import by.epam.lab.beans.Trial;

import static by.epam.lab.utils.Constants.*;

public class TrialBuffer {
    private Trial trial;
    private boolean isEmpty = true;

    public synchronized Trial take() {
        while (isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (trial.getClass() != FakeTrial.class) {
            System.out.println(MESSAGE_GOT + trial);
        }
        isEmpty = true;
        notifyAll();
        return trial;
    }

    public synchronized void put(Trial trial) {
        while (!isEmpty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isEmpty = false;
        this.trial = trial;
        notifyAll();
    }
}
