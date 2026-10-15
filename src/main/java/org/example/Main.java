package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static char contractType = ' ';
    public static double income = 0;


    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Enter income: ");
            income = Double.parseDouble(br.readLine());

            System.out.print("Contract Type: (E)mployment, (C)ivil: ");
            contractType = br.readLine().charAt(0);
            TaxCalculator taxCalculator = new TaxCalculator();

        } catch (Exception ex) {
            System.out.println("Incorrect");
            System.err.println(ex);
            return;
        }

    }
}