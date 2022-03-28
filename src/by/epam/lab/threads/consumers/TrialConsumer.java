package by.epam.lab.threads.consumers;

import by.epam.lab.beans.Trial;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<Trial> passedTrials;
    private final BlockingQueue<String> strTrialsQueue;

    public TrialConsumer(BlockingQueue<Trial> passedTrials, BlockingQueue<String> strTrialsQueue) {
        this.passedTrials = passedTrials;
        this.strTrialsQueue = strTrialsQueue;
    }

    @Override
    public void run() {
        try {
            String str;
            while (true) {
                if (!(str = strTrialsQueue.take()).equals(EMPTY_STR)) {
                    Trial trial = new Trial(str.split(DELIMITER));
                    if (trial.isPassed()) {
                        passedTrials.put(trial);
                        uploadQueue(passedTrials);
                    }
                } else {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void uploadQueue(BlockingQueue<Trial> bq) {
        try (PrintWriter writer = new PrintWriter(new File(RESULT_CSV_NAME))) {
            for (Trial result : bq) {
                writer.write(result.toString() + TABULATION);
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_IS_NOT_FOUND);
        }
    }
}
