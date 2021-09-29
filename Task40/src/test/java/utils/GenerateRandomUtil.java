package utils;

import java.util.HashSet;
import java.util.Random;

public class GenerateRandomUtil {
    public static Integer[] getArrayWithRandomInt(int range, int quantity) {
        HashSet<Integer> numbers = new HashSet<>();
        Random randomGenerator = new Random();

        while (numbers.size() < quantity) {
            int random = randomGenerator.nextInt(range);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }

        return numbers.toArray(new Integer[numbers.size()]);
    }
}
