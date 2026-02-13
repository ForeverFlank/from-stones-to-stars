package fsts.math;

import fsts.util.MetricFormatter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BigNumTest {
    @Test
    void testConstructor() {
        BigNum a = new BigNum(1.125);
        assertEquals(1.125, a.mantissa());
        assertEquals(0.0, a.exponent());

        BigNum b = new BigNum(0);
        assertEquals(0.0, b.mantissa());
    }

    @Test
    void testMinMax() {
        BigNum a = new BigNum(10);
        BigNum b = new BigNum(20);

        assertEquals(a, BigNum.min(a, b));
        assertEquals(b, BigNum.max(a, b));
    }

    @Test
    void testFormat() {
        BigNum a = new BigNum(0);
        assertEquals("0", a.format(0, 1e6, false));
        assertEquals("0.0", a.format(1, 1e6, false));
        assertEquals("0.00", a.format(2, 1e6, false));

        BigNum b = new BigNum(0.009);
        assertEquals("0", b.format(0, 1e6, false));
        assertEquals("0.0", b.format(1, 1e6, false));
        assertEquals("0.01", b.format(2, 1e6, false));

        BigNum c = new BigNum(11.125);
        assertEquals("11", c.format(0, 1e6, false));
        assertEquals("11.1", c.format(1, 1e6, false));
        assertEquals("11.13", c.format(2, 1e6, false));
        assertEquals("11.125", c.format(3, 1e6, false));
    }

    @Test
    void testMetricFormat() {
        assertEquals("1.23 ", MetricFormatter.format(new BigNum(1.23e0)));
        assertEquals("12.3 ", MetricFormatter.format(new BigNum(1.23e1)));
        assertEquals("123 ", MetricFormatter.format(new BigNum(1.23e2)));

        assertEquals("1.23 k", MetricFormatter.format(new BigNum(1.23e3)));
        assertEquals("12.3 k", MetricFormatter.format(new BigNum(1.23e4)));
        assertEquals("123 k", MetricFormatter.format(new BigNum(1.23e5)));

        assertEquals("1.23 M", MetricFormatter.format(new BigNum(1.23e6)));
        assertEquals("12.3 M", MetricFormatter.format(new BigNum(1.23e7)));
        assertEquals("123 M", MetricFormatter.format(new BigNum(1.23e8)));

        assertEquals("1.23 G", MetricFormatter.format(new BigNum(1.23e9)));
        assertEquals("12.3 G", MetricFormatter.format(new BigNum(1.23e10)));
        assertEquals("123 G", MetricFormatter.format(new BigNum(1.23e11)));

        assertEquals("1.23 T", MetricFormatter.format(new BigNum(1.23e12)));
        assertEquals("12.3 T", MetricFormatter.format(new BigNum(1.23e13)));
        assertEquals("123 T", MetricFormatter.format(new BigNum(1.23e14)));

        assertEquals("1.23 P", MetricFormatter.format(new BigNum(1.23e15)));
        assertEquals("12.3 P", MetricFormatter.format(new BigNum(1.23e16)));
        assertEquals("123 P", MetricFormatter.format(new BigNum(1.23e17)));

        assertEquals("1.23 E", MetricFormatter.format(new BigNum(1.23e18)));
        assertEquals("12.3 E", MetricFormatter.format(new BigNum(1.23e19)));
        assertEquals("123 E", MetricFormatter.format(new BigNum(1.23e20)));

        assertEquals("1.23 Z", MetricFormatter.format(new BigNum(1.23e21)));
        assertEquals("12.3 Z", MetricFormatter.format(new BigNum(1.23e22)));
        assertEquals("123 Z", MetricFormatter.format(new BigNum(1.23e23)));

        assertEquals("1.23 Y", MetricFormatter.format(new BigNum(1.23e24)));
        assertEquals("−1.23 Y", MetricFormatter.format(new BigNum(-1.23e24)));
    }

    @Test
    void testMetricFormatOutOfRange() {
        assertEquals("1.234e30", MetricFormatter.format(new BigNum(1.234e30)));
        assertEquals("1.234e-30", MetricFormatter.format(new BigNum(1.234e-30)));
    }
}
