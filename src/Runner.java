import by.epam.lab.beans.ExtraTrial;
import by.epam.lab.beans.LightTrial;
import by.epam.lab.beans.StrongTrial;
import by.epam.lab.beans.Trial;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        List<Trial> trials = new ArrayList<>(TRIAL_LIST_LENGTH);
        trials.add(new Trial("Kursakou", 97, 82));
        trials.add(new Trial("Melnikou", 29, 66));
        trials.add(new Trial("Sidorenko", 79, 84));
        trials.add(new LightTrial("Kvackevich", 94, 55));
        trials.add(new LightTrial("Ilyashenko", 89, 85));
        trials.add(new StrongTrial("Ivanou", 89, 95));
        trials.add(new StrongTrial("Svichko", 82, 91));
        trials.add(new ExtraTrial("Denisou", 92, 57, 81));
        trials.add(new ExtraTrial("Malikou", 92, 57, 71));
        //print the trials list
        trials.forEach(System.out::println);
        System.out.println(SEPARATOR_LINE);
        //print number of passed trials
        System.out.println(PASSED_TRIALS_COUNT_MESSAGE +
                trials.stream().filter(Trial::trialIsPassed).count());
        System.out.println(SEPARATOR_LINE);
        //sort the trials list by sum of the mark1 and mark2
        trials.sort(Comparator.comparingInt(Trial::getResult));
        //print the trials list after sorting
        trials.forEach(System.out::println);
        System.out.println(SEPARATOR_LINE);
        //print each sum of marks
        trials.forEach(trial -> System.out.println(trial.getResult()));
        System.out.println(SEPARATOR_LINE);
        //create a new list for failed trials
        List<Trial> failedTrial = new ArrayList<>();
        //add failed trials in the list, delete all trial's marks and print it
        trials.stream().filter(trial -> !trial.trialIsPassed())
                .forEach(trial -> failedTrial.add(trial.getClone()));
        failedTrial.forEach(trial -> {
            trial.clearAllMarks();
            System.out.println(trial);
        });
        System.out.println(SEPARATOR_LINE);
        //print boolean that all trials in the failed trials list are failed
        boolean allResultsAreFailed = failedTrial.stream().noneMatch(Trial::trialIsPassed);
        System.out.println(ALL_TRIALS_ARE_FAILED + allResultsAreFailed);
        System.out.println(SEPARATOR_LINE);
        //create an array
        int[] sum = trials.stream().mapToInt(Trial::getResult).toArray();
        System.out.println(Arrays.toString(sum));

    }
}
