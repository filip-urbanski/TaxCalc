package org.example;

public interface TaxCalculationStrategy {
    double taxDeductibleExpenses(double incomeAfterSocial);

    double taxFreeIncome();

    boolean minimumAdvanceTaxAtZero();
}
