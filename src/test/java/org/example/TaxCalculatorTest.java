package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaxCalculatorTest {
    @Test
    void calculatesEmploymentTaxWithoutBinaryFloatingPointArtifacts() {
        TaxCalculator calculator = new TaxCalculator(new EmploymentTaxStrategy());
        TaxCalculatorResult result = calculator.tax(new BigDecimal("1000"));

        assertDecimalEquals("97.6", result.getSocSecurity());
        assertDecimalEquals("111.25", result.getTaxDeductibleExpenses());
        assertDecimalEquals("46.33", result.getTaxFreeIncome());
        assertDecimalEquals("752", result.getTaxedIncome());
        assertDecimalEquals("22", result.getAdvanceTaxPaid());
        assertDecimalEquals("763.239", result.getNetIncome());
    }

    @Test
    void calculatesCivilTaxWithPercentageBasedDeductibleExpenses() {
        TaxCalculator calculator = new TaxCalculator(new CivilTaxStrategy());
        TaxCalculatorResult result = calculator.tax(new BigDecimal("1000"));

        assertDecimalEquals("172.58", result.getTaxDeductibleExpenses());
        assertDecimalEquals("0", result.getTaxFreeIncome());
        assertDecimalEquals("690", result.getTaxedIncome());
        assertDecimalEquals("57", result.getAdvanceTaxPaid());
        assertDecimalEquals("728.239", result.getNetIncome());
    }

    @Test
    void formatsCalculatorResultOutsideTheDto() {
        TaxCalculatorResult result = new TaxCalculator(new EmploymentTaxStrategy())
                .tax(new BigDecimal("1000"));
        String formatted = new TaxCalculatorResultFormatter().format(result);

        assertTrue(formatted.contains("grossAmount              = "
                + new DecimalFormat("#,##0.00").format(result.getGrossAmount())));
        assertTrue(formatted.contains("netIncome                = "
                + new DecimalFormat("#,##0.00").format(result.getNetIncome())));
    }

    private static void assertDecimalEquals(String expected, BigDecimal actual) {
        assertEquals(0, new BigDecimal(expected).compareTo(actual));
    }
}
