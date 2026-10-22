package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static EmploymentType contractType = null;
    public static double income = 0;


    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            while (!validateIncome(income)){
                System.out.print("Enter income: ");
                income = Double.parseDouble(br.readLine());
            }

            while (contractType == null){
                System.out.print("Contract Type: (E)mployment, (C)ivil: ");
                contractType = stringToEmploymentType(br.readLine());
            }

            TaxCalculator taxCalculator = new TaxCalculator();
            taxCalculator.tax(income, contractType);

        } catch (Exception ex) {
            System.out.println("Incorrect");
            System.err.println(ex);
            return;
        }
    }

    private static EmploymentType stringToEmploymentType(String input){
        return switch (input) {
            case "E" -> EmploymentType.EMPLOYMENT;
            case "C" -> EmploymentType.CIVIL;
            default -> null;
        };
    }

    private static boolean validateIncome(Double income){
        return income <= 0;
    }
}