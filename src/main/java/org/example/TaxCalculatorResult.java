package org.example;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class TaxCalculatorResult {
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
    public String toString(){
        return "PayrollBreakdown{" +
                "socSecurity=" + socSecurity +
                ", socHealthSecurity=" + socHealthSecurity +
                ", socSickSecurity=" + socSickSecurity +
                ", taxDeductibleExpenses=" + taxDeductibleExpenses +
                ", socHealth1=" + socHealth1 +
                ", socHealth2=" + socHealth2 +
                ", advanceTaxPaidAdvanceTax=" + advanceTaxPaidAdvanceTax +
                ", taxedIncome=" + taxedIncome +
                ", taxFreeIncome=" + taxFreeIncome +
                ", advanceTaxPaid=" + advanceTaxPaid +
                ", netIncome=" + netIncome +
                '}';
    }
}