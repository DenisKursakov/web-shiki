package by.epam.lab.threads;

import by.epam.lab.beans.FakeTrial;
import by.epam.lab.beans.Trial;
import by.epam.lab.services.TrialBuffer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import static by.epam.lab.utils.Constants.*;

public class TrialProducer implements Runnable {
    private TrialBuffer trialBuffer;
    private String path;

    public TrialProducer(TrialBuffer drop, String path) {
        this.trialBuffer = drop;
        this.path = path;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(new FileReader(CSV_NAME))) {
            while (sc.hasNextLine()) {
                trialBuffer.put(new Trial(sc.next().split(DELIMITER)));
            }
        } catch (FileNotFoundException e) {
            System.err.println(FILE_IS_NOT_FOUND);
        } finally {
            trialBuffer.put(new FakeTrial());
        }
    }
}
