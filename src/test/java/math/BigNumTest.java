package math;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.Pane;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class BigNumTest {
    @Test
    void testConstructor() {
        BigNum a = new BigNum(1.125);
        assertEquals(1.125, a.mantissa);
        assertEquals(0.0, a.exponent);

        BigNum b = new BigNum(0);
        assertEquals(0.0, b.mantissa);
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
}
