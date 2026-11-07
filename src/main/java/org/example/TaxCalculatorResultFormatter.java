package org.example;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class TaxCalculatorResultFormatter {
    public String format(TaxCalculatorResult result) {
        DecimalFormat formatter = new DecimalFormat("#,##0.00");

        return "PayrollBreakdown {\n" +
                "  grossAmount              = " + formatSafe(formatter, result.getGrossAmount()) + "\n" +
                "  socSecurity              = " + formatSafe(formatter, result.getSocSecurity()) + "\n" +
                "  socHealthSecurity        = " + formatSafe(formatter, result.getSocHealthSecurity()) + "\n" +
                "  socSickSecurity          = " + formatSafe(formatter, result.getSocSickSecurity()) + "\n" +
                "  taxDeductibleExpenses    = " + formatSafe(formatter, result.getTaxDeductibleExpenses()) + "\n" +
                "  socHealth1               = " + formatSafe(formatter, result.getSocHealth1()) + "\n" +
                "  socHealth2               = " + formatSafe(formatter, result.getSocHealth2()) + "\n" +
                "  advanceTaxPaidAdvanceTax = " + formatSafe(formatter, result.getAdvanceTaxPaidAdvanceTax()) + "\n" +
                "  taxedIncome (Basis)      = " + formatSafe(formatter, result.getTaxedIncome()) + "\n" +
                "  taxFreeIncome            = " + formatSafe(formatter, result.getTaxFreeIncome()) + "\n" +
                "  advanceTaxPaid (To US)   = " + formatSafe(formatter, result.getAdvanceTaxPaid()) + "\n" +
                "  netIncome                = " + formatSafe(formatter, result.getNetIncome()) + "\n" +
                '}';
    }

    private String formatSafe(DecimalFormat formatter, BigDecimal value) {
        return value == null ? "0.00" : formatter.format(value);
    }
}
