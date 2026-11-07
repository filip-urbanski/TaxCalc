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

            TaxCalculationStrategy strategy = switch (contractType) {
                case EMPLOYMENT -> new EmploymentTaxStrategy();
                case CIVIL -> new CivilTaxStrategy();
            };
            TaxCalculator taxCalculator = new TaxCalculator(strategy);
            TaxCalculatorResult result = taxCalculator.tax(income);
            System.out.println(result.toString());

        } catch (Exception ex) {
            System.out.println("Incorrect input or fatal error.");
        }
    }

    private static EmploymentType stringToEmploymentType(String input) {
        if (input == null) return null;

        return switch (input.toUpperCase()) {
            case "E" -> EmploymentType.EMPLOYMENT;
            case "C" -> EmploymentType.CIVIL;
            default -> null;
        };
    }

    private static boolean validateIncome(double income) {
        return income > 0;
    }
}