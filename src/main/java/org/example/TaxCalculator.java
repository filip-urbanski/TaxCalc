package org.example;

import java.math.BigDecimal;

public class TaxCalculator {
    private final TaxCalculationStrategy strategy;

    public TaxCalculator(TaxCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    public TaxCalculatorResult tax(Double baseIncome) {
        double socSecurity = (baseIncome * TaxConfig.SOCIAL_SECURITY_RATE) / 100;
        double socHealthSecurity = (baseIncome * TaxConfig.HEALTH_SECURITY_RATE) / 100;
        double socSickSecurity = (baseIncome * TaxConfig.SICKNESS_SECURITY_RATE) / 100;

        double totalEmployeeSocial = socSecurity + socHealthSecurity + socSickSecurity;
        double incomeAfterSocial = baseIncome - totalEmployeeSocial;

        double socHealth1 = (incomeAfterSocial * TaxConfig.HEALTH_TAX_RATE_1) / 100;
        double socHealth2 = (incomeAfterSocial * TaxConfig.HEALTH_TAX_RATE_2) / 100;
        double taxFreeIncome = strategy.taxFreeIncome();
        double taxDeductibleExpenses = strategy.taxDeductibleExpenses(incomeAfterSocial);

        double taxedIncome = incomeAfterSocial - taxDeductibleExpenses;
        double roundedTaxedIncome = Math.round(taxedIncome);
        double advanceTax = (roundedTaxedIncome * TaxConfig.ADVANCE_TAX_RATE) / 100;
        double advanceTaxPaid = advanceTax - socHealth2 - taxFreeIncome;

        if (strategy.minimumAdvanceTaxAtZero() && advanceTaxPaid < 0) {
            advanceTaxPaid = 0;
        }

        double roundedAdvanceTaxPaid = Math.round(advanceTaxPaid);
        double netIncome = baseIncome - (totalEmployeeSocial + socHealth1 + roundedAdvanceTaxPaid);

        return TaxCalculatorResult.builder()
                .grossAmount(BigDecimal.valueOf(baseIncome))
                .socSecurity(BigDecimal.valueOf(socSecurity))
                .socHealthSecurity(BigDecimal.valueOf(socHealthSecurity))
                .socSickSecurity(BigDecimal.valueOf(socSickSecurity))
                .taxDeductibleExpenses(BigDecimal.valueOf(taxDeductibleExpenses))
                .socHealth1(BigDecimal.valueOf(socHealth1))
                .socHealth2(BigDecimal.valueOf(socHealth2))
                .advanceTaxPaidAdvanceTax(BigDecimal.valueOf(advanceTax))
                .taxedIncome(BigDecimal.valueOf(roundedTaxedIncome))
                .taxFreeIncome(BigDecimal.valueOf(taxFreeIncome))
                .advanceTaxPaid(BigDecimal.valueOf(roundedAdvanceTaxPaid))
                .netIncome(BigDecimal.valueOf(netIncome))
                .build();
    }
}
