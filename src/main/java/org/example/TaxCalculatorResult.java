package org.example;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.text.DecimalFormat;

@Value
@Builder
public class TaxCalculatorResult {
    BigDecimal grossAmount;

    // Social security taxes
    BigDecimal socSecurity;        // 9.76% of basis
    BigDecimal socHealthSecurity;  // 1.50% of basis
    BigDecimal socSickSecurity;    // 2.45% of basis

    // Health-related & income taxes
    BigDecimal taxDeductibleExpenses;
    BigDecimal socHealth1;                 // 9% health tax
    BigDecimal socHealth2;                 // 7.75% health tax deductible from advance
    BigDecimal advanceTaxPaidAdvanceTax;   // Raw advance tax (e.g. 18%)
    BigDecimal taxedIncome;
    BigDecimal taxFreeIncome;              // Monthly tax-free allowance
    BigDecimal advanceTaxPaid;             // Final advance tax paid
    BigDecimal netIncome;

    @Override
    public String toString() {
        DecimalFormat dfMoney = new DecimalFormat("#,##0.00");

        return "PayrollBreakdown {\n" +
                "  grossAmount              = " + formatSafe(dfMoney, grossAmount) + "\n" +
                "  socSecurity              = " + formatSafe(dfMoney, socSecurity) + "\n" +
                "  socHealthSecurity        = " + formatSafe(dfMoney, socHealthSecurity) + "\n" +
                "  socSickSecurity          = " + formatSafe(dfMoney, socSickSecurity) + "\n" +
                "  taxDeductibleExpenses    = " + formatSafe(dfMoney, taxDeductibleExpenses) + "\n" +
                "  socHealth1               = " + formatSafe(dfMoney, socHealth1) + "\n" +
                "  socHealth2               = " + formatSafe(dfMoney, socHealth2) + "\n" +
                "  advanceTaxPaidAdvanceTax = " + formatSafe(dfMoney, advanceTaxPaidAdvanceTax) + "\n" +
                "  taxedIncome (Basis)      = " + formatSafe(dfMoney, taxedIncome) + "\n" +
                "  taxFreeIncome            = " + formatSafe(dfMoney, taxFreeIncome) + "\n" +
                "  advanceTaxPaid (To US)   = " + formatSafe(dfMoney, advanceTaxPaid) + "\n" +
                "  netIncome                = " + formatSafe(dfMoney, netIncome) + "\n" +
                '}';
    }

    private String formatSafe(DecimalFormat formatter, BigDecimal value) {
        return value == null ? "0.00" : formatter.format(value);
    }
}