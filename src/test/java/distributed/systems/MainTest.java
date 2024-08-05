package distributed.systems;

import distributed.systems.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void testCalculatePowerStandard() {
        // Standard case: 2^3 = 8
        assertEquals(8.0, Main.calculatePower(2, 3), 0.0001);
    }

    @Test
    public void testCalculatePowerAnyNumberToZero() {
        // Any number to the power of 0 should be 1

        assertEquals(1.0, Main.calculatePower(5, 0), 0.0001);
        assertEquals(1.0, Main.calculatePower(-3, 0), 0.0001);
    }

    @Test
    public void testCalculatePowerZeroToPositive() {
        // 0^positive number should be 0
        assertEquals(0.0, Main.calculatePower(0, 2), 0.0001);
    }

    @Test
    public void testCalculatePowerZeroToZero() {
        // 0^0 should throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Main.calculatePower(0, 0);
        });
        assertEquals("0^0 is undefined.", exception.getMessage());
    }

    @Test
    public void testCalculatePowerNegativeBaseNonIntegerExponent() {
        // Negative base with non-integer exponent should throw an exception
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Main.calculatePower(-2, 0.5);
        });
        assertEquals("Negative base with non-integer exponent results in a complex number.", exception.getMessage());
    }
}

