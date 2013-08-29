package com.britensw.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.math.RoundingMode.HALF_UP;

/**
 * @author Brent Douglas <brent.n.douglas@gmail.com>
 */
public class Dec {

    public static final BigDecimal TWELVE = new BigDecimal(12);
    public static final BigDecimal THREE_HUNDRED_AND_SIXTY_FIVE = new BigDecimal(365);
    public static final BigDecimal THREE_HUNDRED_AND_SIXTY_SIX = new BigDecimal(366);
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    public static BigDecimal scale(final BigDecimal that, final int scale, final RoundingMode mode) {
        return that.setScale(scale, mode);
    }

    public static BigDecimal scale(final BigDecimal that, final RoundingMode mode) {
        return that.setScale(2, mode);
    }

    public static BigDecimal scale(final BigDecimal that) {
        return that.setScale(2, HALF_UP);
    }

    public static BigDecimal percentage(final BigDecimal that) {
        return that.setScale(4, HALF_UP);
    }

    // Formatting
    public static final String ZERO_DEC_CSV = "##0";
    public static final String TWO_DEC_CSV = "##0.00";

    public static final String ZERO_DEC = "#,##0";
    public static final String TWO_DEC = "#,##0.00";

    public static final String ZERO_PCT = "#,##0%";
    public static final String TWO_PCT = "#,##0.00%";

    public static final String ZERO_CUR = "$#,##0";
    public static final String TWO_CUR = "$#,##0.00";

    public static String format(final String format, final BigDecimal that) {
        return new DecimalFormat(format).format(that);
    }
}
