package org.example;

import java.math.BigDecimal;

public class EmploymentTaxStrategy implements TaxCalculationStrategy {
    @Override
    public BigDecimal taxDeductibleExpenses(BigDecimal incomeAfterSocial) {
        return TaxConfig.EMPLOYMENT_TAX_DEDUCTIBLE;
    }

    @Override
    public BigDecimal taxFreeIncome() {
        return TaxConfig.EMPLOYMENT_TAX_FREE_INCOME;
    }

    @Override
    public boolean minimumAdvanceTaxAtZero() {
        return true;
    }
}
