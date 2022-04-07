package by.epam.lab.threads;

import by.epam.lab.beans.Trial;

import static by.epam.lab.utils.Constants.*;

import java.io.*;
import java.util.concurrent.BlockingQueue;

public class TrialWriter implements Runnable {
    private final BlockingQueue<Trial> passedTrials;
    private final BufferedWriter writer;

    public TrialWriter(BlockingQueue<Trial> passedTrials, BufferedWriter writer) {
        this.passedTrials = passedTrials;
        this.writer = writer;
    }

    @Override
    public void run() {
        try {
            for (Trial result : passedTrials) {
                writer.write(result + TABULATION);
                System.out.println(result);
            }
            writer.flush();
            passedTrials.clear();
        } catch (IOException e) {
            System.err.println(WRITER_ERROR_MESS);
        }
    }

}
