package util;

public class Validateur {
    public static boolean isValidCompteCode(String code) {
        return code != null && code.matches("CPT-\\d{5}");
    }
    public static boolean isPositiveAmount(double montant) {
        return montant > 0;
    }
    public static boolean isPositiveDouble(double value) {
        return value >= 0;
    }
}