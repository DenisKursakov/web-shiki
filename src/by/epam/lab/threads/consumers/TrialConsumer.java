package by.epam.lab.threads.consumers;

import by.epam.lab.beans.Trial;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<Trial> passedTrials;
    private final BlockingQueue<String> strTrialsQueue;
    private final PrintWriter writer;

    public TrialConsumer(BlockingQueue<Trial> passedTrials, BlockingQueue<String> strTrialsQueue,
                         PrintWriter writer) {
        this.passedTrials = passedTrials;
        this.strTrialsQueue = strTrialsQueue;
        this.writer = writer;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String str = strTrialsQueue.take();
                if (!str.equals(EMPTY_STR)) {
                    Trial trial = new Trial(str.split(DELIMITER));
                    if (trial.isPassed()) {
                        try {
                            passedTrials.put(trial);
                            if (passedTrials.remainingCapacity() == 0) {
                                uploadResult(passedTrials);
                                passedTrials.clear();
                            }
                        } catch (InterruptedException e) {
                            System.err.println(INTERRUPTED_MESSAGE_PUT);
                        }
                    }
                } else {
                    uploadResult(passedTrials);
                    break;
                }

            } catch (InterruptedException e) {
                System.err.println(INTERRUPTED_MESSAGE_TAKE);
            }
        }
    }

    private void uploadResult(BlockingQueue<Trial> bq) {
        for (Trial result : bq) {
            writer.write(result + TABULATION);
        }
    }
}
