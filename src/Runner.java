import by.epam.lab.beans.Trial;
import by.epam.lab.threads.consumers.TrialConsumer;
import by.epam.lab.threads.producers.TrialProducer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.*;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle(BASE_NAME);
        String folderName = rb.getString(FOLDER_NAME_KEY);
        int maxProducersNumber = getNextIntValue(rb, MAX_PRODUCED_KEY);
        int maxConsumersNumber = getNextIntValue(rb, MAX_CONSUMER_KEY);
        int queueStrLength = getNextIntValue(rb, LENGTH_STR_TRIAL_KEY);
        int queuePassedLength = getNextIntValue(rb, LENGTH_PASSED_KEY);
        BlockingQueue<Trial> passedTrials = new ArrayBlockingQueue<>(queuePassedLength);
        BlockingQueue<String> strQueue = new ArrayBlockingQueue<>(queueStrLength);
        ExecutorService producerService = Executors.newFixedThreadPool(maxProducersNumber);
        ExecutorService consumerService = Executors.newFixedThreadPool(maxConsumersNumber);
        File folder = new File(folderName);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            String fileName = folderName + file.getName();
            if (fileName.matches(folderName + CSV_REG)) {
                TrialProducer tp = new TrialProducer(strQueue, fileName);
                TrialConsumer tc = new TrialConsumer(passedTrials, strQueue);
                producerService.execute(tp);
                consumerService.execute(tc);
            }
        }
        producerService.shutdown();
        consumerService.shutdown();
        printResults(RESULT_CSV_NAME);
    }

    private static int getNextIntValue(ResourceBundle rb, String key) {
        return Integer.parseInt(rb.getString(key));
    }

    private static void printResults(String csvName) {
        try (Scanner sc = new Scanner(new FileReader(csvName))) {
            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(FILE_IS_NOT_FOUND);
        }
    }
}

