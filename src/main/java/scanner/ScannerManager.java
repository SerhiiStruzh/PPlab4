package scanner;

import java.util.Scanner;

public class ScannerManager {
    private static Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    public static void closeScanner() {
        scanner.close();
    }
    
    public static int getInt() {
        while (true) {
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Введені дані не є цілим числом. Будь ласка, введіть число заново.");
                scanner.nextLine();
            }
        }
    }
}