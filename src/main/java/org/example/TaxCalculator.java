package org.example;


import java.math.BigDecimal;

public class TaxCalculator {

    // social security taxes
    public static double socSecurity = 0; // 9,76% of basis
    public static double socHealthSecurity = 0; // 1,5% of basis
    public static double soc_sick_security = 0; // 2,45% of basis
    // health-related taxes
    public static double taxDeductibleExpenses = 111.25;
    public static double socHealth1 = 0; // of basis up to 9%
    public static double socHealth2 = 0; // of basis up to  7,75 %
    public static double advanceTaxPaidAdvanceTax = 0; // advance tax 18%
    public static double taxFreeIncome = 46.33; // tax-free income monthly 46,33 PLN
    public static double advanceTaxPaid0 = 0;

    public TaxCalculator() {

    }

    public void tax(Double income, EmploymentType employmentType){
        switch (employmentType)
        {
            case EMPLOYMENT:
                EmploymentTaxResult(income);
            case CIVIL:
                CivilTaxResult(income);
            default:
                System.out.println("Unknown type of contract!");
                break;
        }
    }
    public static void calculateAdvanceTax() {
        advanceTaxPaidadvanceTax = advanceTax - socHealth2 - taxFreeIncome;
    }

    public static void calculateTax(double income) {
        advanceTax = (income * 18) / 100;
    }

    public static double calculateIncome(double income) {
        socSecurity = (income * 9.76) / 100;
        socHealthSecurity = (income * 1.5) / 100;
        soc_sick_secur = (income * 2.45) / 100;
        return (income - socSecurity - socHealthSecurity - soc_sick_secur);
    }

    public static void calculateOtherTaxes(double income) {
        socHealth1 = (income * 9) / 100;
        socHealth2 = (income * 7.75) / 100;
    }

    public static void CivilTaxResult(Double baseIncome){

        TaxCalculatorResult civilResult = TaxCalculatorResult.builder()
                .grossAmount(new BigDecimal("5000.00"))
                .employeeSocialBasis(new BigDecimal("5000.00"))
                .pensionInsurance(new BigDecimal("488.00"))
                .disabilityInsurance(new BigDecimal("75.00"))
                .sicknessInsurance(BigDecimal.ZERO) // Optional on Umowa Zlecenie
                .totalEmployeeSocial(new BigDecimal("563.00"))
                .healthInsuranceBasis(new BigDecimal("4437.00"))
                .healthInsurance(new BigDecimal("399.33"))
                .taxDeductibleExpenses(new BigDecimal("887.40")) // 20% KUP
                .taxBasis(new BigDecimal("3550.00"))
                .advanceTaxOffice(new BigDecimal("426.00"))      // 12% scale
                .netAmount(new BigDecimal("3611.67"))
                .build();
        )
        System.out.println("income " + baseIncome);
        double income = calculateIncome(income);
        System.out.println("Social security tax = "
                + df00.format(socSecurity));
        System.out.println("Health social security tax = "
                + df00.format(socHealthSecurity));
        System.out.println("Sickness social security tax = "
                + df00.format(soc_sick_security));
        System.out
                .println("Income for calculating health security tax: "
                        + income);
        calculateOtherTaxes(income);
        System.out.println("Health security tax: 9% = "
                + df00.format(socHealth1) + " 7,75% = " + df00.format(socHealth2));
        taxFreeIncome = 0;
        taxDeductibleExpenses  = (income * 20) / 100;
        System.out.println("Tax deductible expenses = "
                + taxDeductibleExpenses
        );
        double taxedIncome = income - taxDeductibleExpenses ;
        double taxedIncome0 = Double.parseDouble(df.format(taxedIncome));
        System.out.println("income to be taxed = " + taxedIncome
                + " rounded = " + df.format(taxedIncome0));
        calculateTax(taxedIncome0);
        System.out.println("Advance tax 18 % = "
                + advanceTax);
        double  advanceTax;
        System.out.println("Already paid tax = "
                + df00.format(taxPaid));
        calculateAdvanceTax();
        advanceTaxPaid0 = Double.parseDouble(df.format(advanceTaxPaid));
        System.out.println("Advance tax  = "
                + df00.format(advanceTaxPaid) + " rounded = "
                + df.format(advanceTaxPaid0));
        double netIncome = income
                - ((socSecurity + socHealthSecurity + soc_sick_security) + socHealth1 + advanceTaxPaid0);
        System.out.println();
        System.out
                .println("Net income = "
                        + df00.format(netIncome));
    }

    public static void EmploymentTaxResult(Double baseIncome)
    {
        System.out.println("EMPLOYMENT");
        System.out.println("Income " + baseIncome);
        double d_income = calculateIncome(baseIncome);
        System.out.println("Social security tax "
                + df00.format(socSecurity));

        System.out.println("Health social security tax    "
                + df00.format(socHealthSecurity));
        System.out.println("Sickness social security tax  "
                + df00.format(soc_sick_security));
        System.out
                .println("Income basis for health social security: "
                        + baseIncome);
        calculateOtherTaxes(baseIncome);
        System.out.println("Health social security tax: 9% = "
                + df00.format(socHealth1) + " 7,75% = " + df00.format(socHealth2));
        System.out.println("Tax deductible expenses "
                + taxDeductibleExpenses);
        double taxedIncome = baseIncome - taxDeductibleExpenses ;
        double taxedIncome0 = Double
                .parseDouble(df.format(taxedIncome));
        System.out.println("income " + taxedIncome
                + " rounded " + df.format(taxedIncome0));
        calculateTax(taxedIncome0);
        System.out.println("Advance tax 18 % = "
                + advanceTax);
        System.out.println("Tax free income = " + taxFreeIncome);
        double taxPaid = advanceTax - taxFreeIncome;
        System.out.println("Reduced tax = "
                + df00.format(taxPaid));
        calculateAdvanceTax();
        advanceTaxPaid0 = Double.parseDouble(df.format(advanceTaxPaid));
        System.out.println("Advance tax paid = "
                + df00.format(advanceTaxPaid) + " rounded = "
                + df.format(advanceTaxPaid0));
        double netIncome = baseIncome
                - ((socSecurity + socHealthSecurity + soc_sick_security) + socHealth1 + advanceTaxPaid0);
        System.out.println();
        System.out
                .println("Net income = "
                        + df00.format(netIncome));
    }
}