package org.example;

import java.math.BigDecimal;

public interface TaxCalculationStrategy {
    BigDecimal taxDeductibleExpenses(BigDecimal incomeAfterSocial);

    BigDecimal taxFreeIncome();

    boolean minimumAdvanceTaxAtZero();
}
