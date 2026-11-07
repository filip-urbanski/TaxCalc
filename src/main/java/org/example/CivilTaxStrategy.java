package org.example;

public class CivilTaxStrategy implements TaxCalculationStrategy {
    @Override
    public double taxDeductibleExpenses(double incomeAfterSocial) {
        return (incomeAfterSocial * TaxConfig.CIVIL_TAX_DEDUCTIBLE_RATE) / 100;
    }

    @Override
    public double taxFreeIncome() {
        return 0;
    }

    @Override
    public boolean minimumAdvanceTaxAtZero() {
        return false;
    }
}
