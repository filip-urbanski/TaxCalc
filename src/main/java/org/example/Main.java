package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static EmploymentType contractType;
    public static double income;

    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            while (!validateIncome(income)) {
                System.out.print("Enter income (must be greater than 0): ");
                try {
                    income = Double.parseDouble(br.readLine());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                }
            }

            while (contractType == null) {
                System.out.print("Contract Type: (E)mployment, (C)ivil: ");
                contractType = stringToEmploymentType(br.readLine());
            }

            TaxCalculator taxCalculator = new TaxCalculator();
            TaxCalculatorResult result = taxCalculator.tax(income, contractType);
            System.out.println(result);

        } catch (Exception ex) {
            System.out.println("Incorrect input or fatal error.");
        }
    }

    private static EmploymentType stringToEmploymentType(String input) {
        if (input == null) return null;

        return switch (input.toUpperCase()) { // Added toUpperCase() so 'e' and 'c' also work
            case "E" -> EmploymentType.EMPLOYMENT;
            case "C" -> EmploymentType.CIVIL;
            default -> null;
        };
    }

    private static boolean validateIncome(double income) {
        return income > 0;
    }
}