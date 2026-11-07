package org.example;

import java.math.BigDecimal;

public class CivilTaxStrategy implements TaxCalculationStrategy {
    @Override
    public BigDecimal taxDeductibleExpenses(BigDecimal incomeAfterSocial) {
        return incomeAfterSocial
                .multiply(TaxConfig.CIVIL_TAX_DEDUCTIBLE_RATE)
                .divide(BigDecimal.valueOf(100));
    }

    @Override
    public BigDecimal taxFreeIncome() {
        return BigDecimal.ZERO;
    }

    @Override
    public boolean minimumAdvanceTaxAtZero() {
        return false;
    }
}
