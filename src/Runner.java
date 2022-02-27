import by.epam.lab.beans.ExtraTrial;
import by.epam.lab.beans.LightTrial;
import by.epam.lab.beans.StrongTrial;
import by.epam.lab.beans.Trial;
import jdk.dynalink.Operation;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static by.epam.lab.utils.Constants.*;

public class Runner {
    public static void main(String[] args) {
        //initial collection - 1
        List<Trial> trials = Arrays.asList(
                new Trial("Kursakou", 97, 82),
                new Trial("Melnikou", 29, 66),
                new Trial("Sidorenko", 79, 84),
                new LightTrial("Kvackevich", 94, 55),
                new LightTrial("Ilyashenko", 89, 85),
                new StrongTrial("Ivanou", 89, 95),
                new StrongTrial("Svichko", 82, 91),
                new ExtraTrial("Denisou", 92, 57, 81),
                new ExtraTrial("Malikou", 92, 57, 71)
        );
        ToIntFunction<Trial> getResultOp =
                trial -> trial.getFirstTestMark() + trial.getSecondTestMark();
        //print the trials list - 2
        trials.forEach(System.out::println);
        System.out.println(SEPARATOR_LINE);
        //print number of passed trials - 3
        System.out.println(PASSED_TRIALS_COUNT_MESSAGE +
                trials
                        .stream()
                        .filter(Trial::trialIsPassed)
                        .count());
        System.out.println(SEPARATOR_LINE);
        //sort the trials list by sum of the mark1 and mark2 - 4
        trials.sort(Comparator.comparingInt(getResultOp));
        //print the trials list after sorting
        trials.forEach(System.out::println);
        System.out.println(SEPARATOR_LINE);
        //print each sum of marks - 5
        trials
                .stream()
                .mapToInt(getResultOp)
                .forEach(System.out::println);
        System.out.println(SEPARATOR_LINE);
        //add failed trials in the list, delete all trial's marks and print it - 6
        List<Trial> failedTrial = trials
                .stream().filter(trial -> !trial.trialIsPassed())
                .map(Trial::getClone)
                .peek(Trial::clearAllMarks)
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(SEPARATOR_LINE);
        //print boolean that all trials in the failed trials list are failed
        boolean allResultsAreFailed = failedTrial
                .stream()
                .noneMatch(Trial::trialIsPassed);
        System.out.println(ALL_TRIALS_ARE_FAILED + allResultsAreFailed);
        System.out.println(SEPARATOR_LINE);
        //create an int array and convert it to a string  - 7
        int[] sum = trials
                .stream()
                .mapToInt(getResultOp)
                .toArray();
        String strTrials = Arrays
                .stream(sum)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList())
                .toString();
        System.out.println(strTrials);

    }
}
