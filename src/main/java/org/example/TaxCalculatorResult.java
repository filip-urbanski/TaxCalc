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
}