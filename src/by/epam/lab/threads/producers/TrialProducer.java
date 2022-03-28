package by.epam.lab.threads.producers;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialProducer implements Runnable {
    private final BlockingQueue<String> strTrialsQueue;
    private Scanner sc;

    public TrialProducer(BlockingQueue<String> strTrialsQueue, String path) {
        this.strTrialsQueue = strTrialsQueue;
        try {
            this.sc = new Scanner(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.err.println(FILE_IS_NOT_FOUND);
        }
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
            e.printStackTrace();
        }
    }
}
