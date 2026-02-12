package math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigNum {
    public final double mantissa;
    public final double exponent;

    public BigNum(double num) {
        if (num == 0.0) {
            mantissa = 0.0;
            exponent = 0.0;
            return;
        }

        double sign = Math.signum(num);
        double positiveNum = Math.abs(num);
        double logNum = Math.log10(positiveNum);

        exponent = sign * Math.floor(logNum);
        mantissa = Math.pow(10.0, logNum - exponent);
    }

    public BigNum(double mantissa, double exponent) {
        if (mantissa == 0.0) {
            this.mantissa = 0.0;
            this.exponent = 0.0;
            return;
        }

        double flooredExponent = Math.floor(exponent);
        double adjustedMantissa = mantissa * Math.pow(10.0, exponent - flooredExponent);

        double shift = Math.floor(Math.log10(Math.abs(adjustedMantissa)));

        this.mantissa = adjustedMantissa / Math.pow(10.0, shift);
        this.exponent = flooredExponent + shift;

        assert !Double.isNaN(this.mantissa);
        assert !Double.isNaN(this.exponent);
    }

    public static BigNum pow10(double num) {
        double exponent = Math.floor(num);
        double mantissa = Math.pow(10.0, num - exponent);
        return new BigNum(mantissa, exponent);
    }


    public static BigNum add(BigNum lhs, BigNum rhs) {
        if (lhs.isZero()) return rhs;
        if (rhs.isZero()) return lhs;

        BigNum higher = max(lhs, rhs);
        BigNum lower = min(lhs, rhs);

        return higher.isPositive()
               ? addPositiveHigherLhs(higher, lower)
               : addPositiveHigherLhs(lower.neg(), higher.neg()).neg();
    }

    private static BigNum addPositiveHigherLhs(BigNum lhs, BigNum rhs) {
        assert lhs.isPositive();

        double exponentDifference = lhs.exponent - rhs.exponent;
        double adjustedRhsMantissa = rhs.mantissa / Math.pow(10.0, exponentDifference);

        double sumMantissa = lhs.mantissa + adjustedRhsMantissa;

        return new BigNum(sumMantissa, lhs.exponent);
    }

    public BigNum add(BigNum rhs) {
        return add(this, rhs);
    }

    public BigNum add(double rhs) {
        return add(this, new BigNum(rhs));
    }


    public static BigNum sub(BigNum lhs, BigNum rhs) {
        return BigNum.add(lhs, rhs.neg());
    }

    public BigNum sub(BigNum rhs) {
        return this.add(rhs.neg());
    }

    public BigNum sub(double rhs) {
        return this.sub(new BigNum(rhs));
    }


    public static BigNum mul(BigNum lhs, BigNum rhs) {
        if (lhs.isZero() || rhs.isZero()) return new BigNum(0);

        double productMantissa = lhs.mantissa * rhs.mantissa;
        double productExponent = lhs.exponent + rhs.exponent;
        return new BigNum(productMantissa, productExponent);
    }

    public BigNum mul(BigNum rhs) {
        return mul(this, rhs);
    }

    public BigNum mul(double rhs) {
        return this.mul(new BigNum(rhs));
    }


    public static BigNum div(BigNum lhs, BigNum rhs) {
        return mul(lhs, rhs.recip());
    }

    public BigNum div(BigNum rhs) {
        return this.mul(rhs.recip());
    }

    public BigNum div(double rhs) {
        return this.div(new BigNum(rhs));
    }


    public BigNum neg() {
        return new BigNum(-mantissa, exponent);
    }

    public BigNum recip() {
        return new BigNum(1.0 / mantissa, -exponent);
    }

    public BigNum abs() {
        return new BigNum(Math.abs(mantissa), exponent);
    }


    public static BigNum pow(BigNum base, BigNum exponent) {
        double expValue = exponent.mantissa * Math.pow(10.0, exponent.exponent);
        return pow10(expValue * (base.exponent + Math.log10(base.mantissa)));
    }

    public static BigNum pow(double base, BigNum exponent) {
        return pow(new BigNum(base), exponent);
    }

    public static BigNum pow(BigNum base, double exponent) {
        return pow(base, new BigNum(exponent));
    }

    public static BigNum pow(double base, double exponent) {
        return pow(new BigNum(base), new BigNum(exponent));
    }

    public BigNum pow(BigNum exponent) {
        return BigNum.pow(this, exponent);
    }

    public BigNum pow(double exponent) {
        return BigNum.pow(this, new BigNum(exponent));
    }


    public static BigNum sqrt(BigNum num) {
        assert !num.isNegative();
        return new BigNum(Math.sqrt(num.mantissa), num.exponent / 2.0);
    }

    public BigNum sqrt() {
        return sqrt(this);
    }


    public static BigNum log10(BigNum num) {
        assert num.isPositive();
        return new BigNum(Math.log10(num.mantissa) + num.exponent);
    }

    public BigNum log10() {
        return log10(this);
    }


    public static int cmp(BigNum lhs, BigNum rhs) {
        if (lhs.isZero()) {
            return Double.compare(0.0, rhs.mantissa);
        }

        if (lhs.isPositive()) {
            if (!rhs.isPositive()) return 1;

            if (lhs.exponent > rhs.exponent) return 1;
            if (lhs.exponent < rhs.exponent) return -1;

            return Double.compare(lhs.mantissa, rhs.mantissa);
        }

        return -lhs.neg().cmp(rhs.neg());
    }

    public int cmp(BigNum rhs) {
        return cmp(this, rhs);
    }

    public int cmp(double rhs) {
        return cmp(this, new BigNum(rhs));
    }

    public static BigNum max(BigNum a, BigNum b) {
        return b.cmp(a) == -1 ? a : b;
    }

    public static BigNum min(BigNum a, BigNum b) {
        return a.cmp(b) == -1 ? a : b;
    }

    public boolean isZero() {
        return mantissa == 0.0;
    }

    public boolean isPositive() {
        return mantissa > 0.0;
    }

    public boolean isNegative() {
        return mantissa < 0.0;
    }


    public String format(
        int decimalPlaces,
        double scientificNotationThreshold,
        boolean trimDecimals
    ) {
        if (cmp(scientificNotationThreshold) < 0) {
            double value = mantissa * Math.pow(10, exponent);
            return trimDecimals
                   ? formatDoubleAndTrim(value, decimalPlaces)
                   : formatDouble(value, decimalPlaces);
        }

        String formattedMantissa = formatDouble(mantissa, decimalPlaces);
        String formattedExponent = String.format("%.0f", exponent);
        return formattedMantissa + "e" + formattedExponent;
    }

    private static String formatDouble(double value, int decimalPlaces) {
        return BigDecimal.valueOf(value)
                         .setScale(decimalPlaces, RoundingMode.HALF_UP)
                         .toPlainString();
    }

    private static String formatDoubleAndTrim(double value, int decimalPlaces
    ) {
        return BigDecimal.valueOf(value)
                         .setScale(decimalPlaces, RoundingMode.HALF_UP)
                         .stripTrailingZeros()
                         .toPlainString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BigNum value)) return false;
        return mantissa == value.mantissa && exponent == value.exponent;
    }

    @Override
    public String toString() {
        return mantissa + "e" + exponent;
    }
}
