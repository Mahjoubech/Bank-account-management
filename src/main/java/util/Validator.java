package util;

import java.util.Scanner;

public class Validator {
    private static Scanner scanner = new Scanner(System.in);

    public static int readInt(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextInt()) {
            System.out.println("Veuillez entrer un nombre entier.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static double readDouble(String msg) {
        System.out.print(msg);
        while (!scanner.hasNextDouble()) {
            System.out.println("Veuillez entrer un nombre décimal.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static String readString(String msg) {
        System.out.print(msg);
        return scanner.next();
    }

    public static String readCompteCode(String msg) {
        System.out.print(msg);
        String code = scanner.next();
        while (!code.matches("CPT-\\d{5}")) {
            System.out.println("Format invalide. Le code doit être 'CPT-XXXXX'");
            code = scanner.next();
        }
        return code;
    }
}