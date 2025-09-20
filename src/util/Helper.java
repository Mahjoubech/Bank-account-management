package util;

import java.util.Random;

public class Helper {
    public static String generateCompteCode() {
        Random rand = new Random();
        int number = rand.nextInt(100000);
        return String.format("CPT-%05d", number);
    }
}