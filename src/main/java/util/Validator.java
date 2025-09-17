package util;

public class Validator {

    public static boolean isValidCompteCode(String code) {
        return code.matches("CPT-\\d{5}");
    }

    public static boolean isPositive(double montant) {
        return montant > 0;
    }
}
