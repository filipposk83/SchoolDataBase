package schooldatabase;

import java.util.Scanner;

public class Validate {

    public String returnValidDate(Scanner sc) {
        String s = sc.next();
        boolean isDate = true;
        isDate = s.matches("\\d{4}-\\d{2}-\\d{2}");
        while (!isDate) {
            System.out.println("You must input a date like yyyy-mm-dd. Please re-enter.......");
            s = sc.next();
            isDate = s.matches("\\d{4}-\\d{2}-\\d{2}");
        }
        return s;
    }

    public String returnValidString(Scanner sc) {

        String s = sc.next();
        boolean isString = s.matches("[A-Za-z]+");
        while (!isString) {
            System.out.println("Numbers are not allowed in this input. Please re-enter....... ");
            s = sc.next();
            isString = s.matches("[A-Za-z]+");
        }
        return s;
    }

    public double returnValidDouble(Scanner sc) {
        double x;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                isFirstTime = false;
            } else {
                System.out.println("Please enter a number greater than 0.");
            }
            while (!sc.hasNextDouble()) {
                System.out.println("Your input must be a number. Please re-enter.......");
                sc.next();
            }
            x = sc.nextDouble();
        } while (x < 0);
        return x;
    }
    
    public int returnValidInteger(Scanner sc) {
        int x;
        boolean isFirstTime = true;
        do {
            if (isFirstTime) {
                isFirstTime = false;
            } else {
                System.out.println("Please enter a number greater than 0.");
            }
            while (!sc.hasNextInt()) {
                System.out.println("Your input must be a number. Enter number");
                sc.next();
            }
            x = sc.nextInt();
        } while (x < 0);
        return x;

    }
}
