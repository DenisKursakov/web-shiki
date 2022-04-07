package by.epam.lab.threads.consumers;

import by.epam.lab.beans.Trial;
import by.epam.lab.threads.TrialWriter;

import java.util.concurrent.BlockingQueue;

import static by.epam.lab.utils.Constants.*;

public class TrialConsumer implements Runnable {
    private final BlockingQueue<Trial> passedTrials;
    private final BlockingQueue<String> strTrialsQueue;
    private final TrialWriter writer;

    public TrialConsumer(BlockingQueue<Trial> passedTrials, BlockingQueue<String> strTrialsQueue,
                         TrialWriter writer) {
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
                                (new Thread(writer)).start();
                            }
                        } catch (InterruptedException e) {
                            System.err.println(INTERRUPTED_MESSAGE_PUT);
                        }
                    }
                } else {
                    if (strTrialsQueue.remainingCapacity() == 0){
                        (new Thread(writer)).start();
                    }
                    break;
                }

            } catch (InterruptedException e) {
                System.err.println(INTERRUPTED_MESSAGE_TAKE);
            }
        }
    }
}
