package by.epam.lab.threads.producers;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialProducer implements Runnable {
    private final BlockingQueue<String> strTrialsQueue;
    private final Scanner sc;

    public TrialProducer(BlockingQueue<String> strTrialsQueue, String path)
            throws FileNotFoundException {
        this.strTrialsQueue = strTrialsQueue;
        this.sc = new Scanner(new FileReader(path));
    }

    @Override
    public void run() {
        try {
            while (sc.hasNextLine()) {
                String strTrial = sc.next();
                strTrialsQueue.put(strTrial);
            }
            strTrialsQueue.put(EMPTY_STR);
        } catch (InterruptedException e) {
            System.err.println(INTERRUPTED_MESSAGE_PUT);
        }
    }
}
