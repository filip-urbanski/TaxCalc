package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class TaxCalculator {
    private static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    private final TaxCalculationStrategy strategy;

    public TaxCalculator(TaxCalculationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public TaxCalculatorResult tax(BigDecimal baseIncome) {
        BigDecimal socSecurity = percentage(baseIncome, TaxConfig.SOCIAL_SECURITY_RATE);
        BigDecimal socHealthSecurity = percentage(baseIncome, TaxConfig.HEALTH_SECURITY_RATE);
        BigDecimal socSickSecurity = percentage(baseIncome, TaxConfig.SICKNESS_SECURITY_RATE);

        BigDecimal totalEmployeeSocial = socSecurity.add(socHealthSecurity).add(socSickSecurity);
        BigDecimal incomeAfterSocial = baseIncome.subtract(totalEmployeeSocial);

        BigDecimal socHealth1 = percentage(incomeAfterSocial, TaxConfig.HEALTH_TAX_RATE_1);
        BigDecimal socHealth2 = percentage(incomeAfterSocial, TaxConfig.HEALTH_TAX_RATE_2);
        BigDecimal taxFreeIncome = strategy.taxFreeIncome();
        BigDecimal taxDeductibleExpenses = strategy.taxDeductibleExpenses(incomeAfterSocial);

        BigDecimal taxedIncome = incomeAfterSocial.subtract(taxDeductibleExpenses);
        BigDecimal roundedTaxedIncome = taxedIncome.setScale(0, RoundingMode.HALF_UP);
        BigDecimal advanceTax = percentage(roundedTaxedIncome, TaxConfig.ADVANCE_TAX_RATE);
        BigDecimal advanceTaxPaid = advanceTax.subtract(socHealth2).subtract(taxFreeIncome);

        if (strategy.minimumAdvanceTaxAtZero() && advanceTaxPaid.signum() < 0) {
            advanceTaxPaid = BigDecimal.ZERO;
        }

        BigDecimal roundedAdvanceTaxPaid = advanceTaxPaid.setScale(0, RoundingMode.HALF_UP);
        BigDecimal netIncome = baseIncome.subtract(
                totalEmployeeSocial.add(socHealth1).add(roundedAdvanceTaxPaid));

        return TaxCalculatorResult.builder()
                .grossAmount(baseIncome)
                .socSecurity(socSecurity)
                .socHealthSecurity(socHealthSecurity)
                .socSickSecurity(socSickSecurity)
                .taxDeductibleExpenses(taxDeductibleExpenses)
                .socHealth1(socHealth1)
                .socHealth2(socHealth2)
                .advanceTaxPaidAdvanceTax(advanceTax)
                .taxedIncome(roundedTaxedIncome)
                .taxFreeIncome(taxFreeIncome)
                .advanceTaxPaid(roundedAdvanceTaxPaid)
                .netIncome(netIncome)
                .build();
    }

    private static BigDecimal percentage(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).divide(ONE_HUNDRED);
    }
}
