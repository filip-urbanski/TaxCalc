package org.example;

public class EmploymentTaxStrategy implements TaxCalculationStrategy {
    @Override
    public double taxDeductibleExpenses(double incomeAfterSocial) {
        return TaxConfig.EMPLOYMENT_TAX_DEDUCTIBLE;
    }

    @Override
    public double taxFreeIncome() {
        return TaxConfig.EMPLOYMENT_TAX_FREE_INCOME;
    }

    @Override
    public boolean minimumAdvanceTaxAtZero() {
        return true;
    }
}
