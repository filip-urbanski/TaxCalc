package org.example;

import java.math.BigDecimal;

public class TaxConfig {
    public static final BigDecimal SOCIAL_SECURITY_RATE = BigDecimal.valueOf(9.76);
    public static final BigDecimal HEALTH_SECURITY_RATE = BigDecimal.valueOf(1.5);
    public static final BigDecimal SICKNESS_SECURITY_RATE = BigDecimal.valueOf(2.45);

    public static final BigDecimal HEALTH_TAX_RATE_1 = BigDecimal.valueOf(9.0);
    public static final BigDecimal HEALTH_TAX_RATE_2 = BigDecimal.valueOf(7.75);
    public static final BigDecimal ADVANCE_TAX_RATE = BigDecimal.valueOf(18.0);

    public static final BigDecimal EMPLOYMENT_TAX_FREE_INCOME = BigDecimal.valueOf(46.33);
    public static final BigDecimal EMPLOYMENT_TAX_DEDUCTIBLE = BigDecimal.valueOf(111.25);
    public static final BigDecimal CIVIL_TAX_DEDUCTIBLE_RATE = BigDecimal.valueOf(20.0);
}