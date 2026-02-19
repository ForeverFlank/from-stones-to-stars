package fsts.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fsts.math.BigNum;

public final class MetricFormatter {

    public static String format(BigNum num) {
        return formatPositivePrefixes(num, "");
    }

    public static String formatPositivePrefixes(BigNum num, String unit) {
        boolean isNegative = num.isNegative();
        BigNum absNum = num.abs();

        if (absNum.cmp(0.01) < 0) {
            return (isNegative ? "−" : "") + "0.00 " + unit;
        }

        if (absNum.cmp(1) < 0) {
            double absNumDouble = absNum.mantissa() * Math.pow(10.0, absNum.exponent());
            BigDecimal bd = BigDecimal.valueOf(absNumDouble).setScale(2, RoundingMode.HALF_UP);
            return (isNegative ? "−" : "") + bd.toPlainString() + " " + unit;
        }

        if (absNum.cmp(1E27) >= 0) {
            return num.format(3, 0.0, false) + " " + unit;
        }

        int exponent = (int) Math.round(num.exponent());
        int exponentDiv3 = (int) Math.floor(exponent / 3.0);
        int decimalPlaces = 3 - ((exponent % 3) + 3) % 3;

        String prefix = switch (exponentDiv3) {
            case 1 -> "k";
            case 2 -> "M";
            case 3 -> "G";
            case 4 -> "T";
            case 5 -> "P";
            case 6 -> "E";
            case 7 -> "Z";
            case 8 -> "Y";
            default -> "";
        };

        double[] pow10 = {1.0, 10.0, 100.0, 1000.0};
        double mantissa = absNum.mantissa() * pow10[3 - decimalPlaces];
        String formattedMantissa = BigDecimal.valueOf(mantissa)
                                             .setScale(decimalPlaces, RoundingMode.HALF_UP)
                                             .toPlainString();

        return (isNegative ? "−" : "") + formattedMantissa + " " + prefix + unit;
    }
}
