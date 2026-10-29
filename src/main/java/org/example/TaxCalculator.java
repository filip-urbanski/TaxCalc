package org.example;

import java.math.BigDecimal;

public class TaxCalculator {

    public TaxCalculator() {
    }

    public TaxCalculatorResult tax(Double income, EmploymentType employmentType) {
        return switch (employmentType) {
            case EMPLOYMENT -> employmentTaxResult(income);
            case CIVIL -> civilTaxResult(income);
        };
    }

    public static TaxCalculatorResult civilTaxResult(Double baseIncome) {
        double socSecurity = (baseIncome * TaxConfig.SOCIAL_SECURITY_RATE) / 100;
        double socHealthSecurity = (baseIncome * TaxConfig.HEALTH_SECURITY_RATE) / 100;
        double socSickSecurity = (baseIncome * TaxConfig.SICKNESS_SECURITY_RATE) / 100;

        double totalEmployeeSocial = socSecurity + socHealthSecurity + socSickSecurity;
        double income = baseIncome - totalEmployeeSocial;

        double socHealth1 = (income * TaxConfig.HEALTH_TAX_RATE_1) / 100;
        double socHealth2 = (income * TaxConfig.HEALTH_TAX_RATE_2) / 100;

        double taxFreeIncome = 0;
        double taxDeductibleExpenses = (income * TaxConfig.CIVIL_TAX_DEDUCTIBLE_RATE) / 100;

        double taxedIncome = income - taxDeductibleExpenses;
        double taxedIncome0 = Math.round(taxedIncome);

        double advanceTax = (taxedIncome0 * TaxConfig.ADVANCE_TAX_RATE) / 100;

        double advanceTaxPaid = advanceTax - socHealth2 - taxFreeIncome;
        double advanceTaxPaid0 = Math.round(advanceTaxPaid); // PL Advance tax is rounded to whole numbers

        double netIncome = baseIncome - (totalEmployeeSocial + socHealth1 + advanceTaxPaid0);

        return TaxCalculatorResult.builder()
                .grossAmount(BigDecimal.valueOf(baseIncome))
                .socSecurity(BigDecimal.valueOf(socSecurity))
                .socHealthSecurity(BigDecimal.valueOf(socHealthSecurity))
                .socSickSecurity(BigDecimal.valueOf(socSickSecurity))
                .taxDeductibleExpenses(BigDecimal.valueOf(taxDeductibleExpenses))
                .socHealth1(BigDecimal.valueOf(socHealth1))
                .socHealth2(BigDecimal.valueOf(socHealth2))
                .advanceTaxPaidAdvanceTax(BigDecimal.valueOf(advanceTax))
                .taxedIncome(BigDecimal.valueOf(taxedIncome0))
                .taxFreeIncome(BigDecimal.valueOf(taxFreeIncome))
                .advanceTaxPaid(BigDecimal.valueOf(advanceTaxPaid0))
                .netIncome(BigDecimal.valueOf(netIncome))
                .build();
    }

    public static TaxCalculatorResult employmentTaxResult(Double baseIncome) {
        double socSecurity = (baseIncome * TaxConfig.SOCIAL_SECURITY_RATE) / 100;
        double socHealthSecurity = (baseIncome * TaxConfig.HEALTH_SECURITY_RATE) / 100;
        double socSickSecurity = (baseIncome * TaxConfig.SICKNESS_SECURITY_RATE) / 100;

        double totalEmployeeSocial = socSecurity + socHealthSecurity + socSickSecurity;
        double dIncome = baseIncome - totalEmployeeSocial;

        double socHealth1 = (dIncome * TaxConfig.HEALTH_TAX_RATE_1) / 100;
        double socHealth2 = (dIncome * TaxConfig.HEALTH_TAX_RATE_2) / 100;

        double taxDeductibleExpenses = TaxConfig.EMPLOYMENT_TAX_DEDUCTIBLE;
        double taxFreeIncome = TaxConfig.EMPLOYMENT_TAX_FREE_INCOME;

        double taxedIncome = dIncome - taxDeductibleExpenses;
        double taxedIncome0 = Math.round(taxedIncome); // PL Tax basis is rounded to whole numbers

        double advanceTax = (taxedIncome0 * TaxConfig.ADVANCE_TAX_RATE) / 100;

        double advanceTaxPaid = advanceTax - socHealth2 - taxFreeIncome;

        // Prevent negative advance tax
        if (advanceTaxPaid < 0) {
            advanceTaxPaid = 0;
        }

        double advanceTaxPaid0 = Math.round(advanceTaxPaid); // PL Advance tax is rounded to whole numbers

        double netIncome = baseIncome - (totalEmployeeSocial + socHealth1 + advanceTaxPaid0);

        return TaxCalculatorResult.builder()
                .grossAmount(BigDecimal.valueOf(baseIncome))
                .socSecurity(BigDecimal.valueOf(socSecurity))
                .socHealthSecurity(BigDecimal.valueOf(socHealthSecurity))
                .socSickSecurity(BigDecimal.valueOf(socSickSecurity))
                .taxDeductibleExpenses(BigDecimal.valueOf(taxDeductibleExpenses))
                .socHealth1(BigDecimal.valueOf(socHealth1))
                .socHealth2(BigDecimal.valueOf(socHealth2))
                .advanceTaxPaidAdvanceTax(BigDecimal.valueOf(advanceTax))
                .taxedIncome(BigDecimal.valueOf(taxedIncome0))
                .taxFreeIncome(BigDecimal.valueOf(taxFreeIncome))
                .advanceTaxPaid(BigDecimal.valueOf(advanceTaxPaid0))
                .netIncome(BigDecimal.valueOf(netIncome))
                .build();
    }
}