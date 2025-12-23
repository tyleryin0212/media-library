package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OneTimeDonationsTest {
    OneTimeDonations otdTest;

    @BeforeEach
    void setUp() {
        otdTest = new OneTimeDonations(100.5, LocalDateTime.of(2025, 2, 10, 12, 45));

    }

    @Test
    void getAmount() {
        assertEquals(100.5, otdTest.getAmount(), 0.001);
    }

    @Test
    void testGetAmountForSameYear() {
        assertEquals(100.5, otdTest.getAmount(2025), 0.001);

    }

    @Test
    void testGetAmountForDifferentYear() {
        assertEquals(0.0, otdTest.getAmount(2024), 0.001);
    }

    @Test
    void testGetAmountWithNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new OneTimeDonations(-50.0, LocalDateTime.of(2025, 1, 10, 12, 45));
        });
    }

    @Test
    void testGetDateOfDonation() {
        assertEquals(LocalDateTime.of(2025, 2, 10, 12, 45), otdTest.getDonationTime());
    }

    @Test
    void testToString() {
        String result = otdTest.toString();
        assertTrue(result.contains("One time donation"));
        assertTrue(result.contains("100.5"));
        assertTrue(result.contains("2025"));
    }
}