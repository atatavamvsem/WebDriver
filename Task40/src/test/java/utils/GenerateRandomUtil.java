package utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GenerateRandomUtil {
    public static int[] getArrayWithRandomInt(int range, int quantity) {
        Set<Integer> numbers = new HashSet<>();
        Random randomGenerator = new Random();

        while (numbers.size() < quantity) {
            int random = randomGenerator.nextInt(range);
            numbers.add(random);
        }

        return numbers.stream().mapToInt(i -> i).toArray();
    }

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
