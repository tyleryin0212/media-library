package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyDonationsTest {

    MonthlyDonations mdTest;

    @BeforeEach
    void setUp() {
        mdTest = new MonthlyDonations(55.5, LocalDateTime.of(2020, 1, 1, 15, 12));
    }

    @Test
    void setCancelDateTime() {
        mdTest.setCancelDateTime(LocalDateTime.of(2022, 4, 1, 0, 0));
        assertEquals(LocalDateTime.of(2022, 4, 1, 0, 0), mdTest.getCancelDateTime());
    }


    @Test
    void isActiveWithoutCancelDateTime() {
        assertTrue(mdTest.isActive());
    }

    @Test
    void isActiveWithCancelDateTime() {
        mdTest.setCancelDateTime(LocalDateTime.of(2022, 4, 1, 0, 0));
        assertFalse(mdTest.isActive());
    }

    @Test
    void getAmountActive() {
        assertEquals(3885.0, mdTest.getAmount());
    }

    @Test
    void getAmountNotActive() {
        mdTest.setCancelDateTime(LocalDateTime.of(2022, 4, 1, 0, 0));
        assertEquals(1498.5, mdTest.getAmount());
    }


    @Test
    void testGetAmount_FullYearNoCancel() {
        double expected = 12 * 55.5;
        assertEquals(expected, mdTest.getAmount(2021), 0.001);
    }

    @Test
    void testGetAmount_StartingMidYear() {
        mdTest = new MonthlyDonations(100.0, LocalDateTime.of(2024, 7, 5, 10, 0));
        double expected = 6 * 100.0;
        assertEquals(expected, mdTest.getAmount(2024), 0.001);
    }

    @Test
    void testGetAmount_CancelMidYear() {
        mdTest = new MonthlyDonations(40.0, LocalDateTime.of(2023, 1, 1, 0, 0));
        mdTest.setCancelDateTime(LocalDateTime.of(2023, 5, 15, 0, 0));
        double expected = 5 * 40.0;
        assertEquals(expected, mdTest.getAmount(2023), 0.001);
    }

    @Test
    void testGetAmount_CrossingYears() {
        mdTest = new MonthlyDonations(20.0, LocalDateTime.of(2023, 10, 10, 0, 0));
        mdTest.setCancelDateTime(LocalDateTime.of(2025, 3, 10, 0, 0));
        assertEquals(3 * 20.0, mdTest.getAmount(2023), 0.001);
        assertEquals(12 * 20.0, mdTest.getAmount(2024), 0.001);
        assertEquals(3 * 20.0, mdTest.getAmount(2025), 0.001);
    }

    @Test
    void testGetAmount_StartedAfterYear() {
        mdTest = new MonthlyDonations(75.0, LocalDateTime.of(2026, 2, 1, 0, 0));
        assertEquals(0.0, mdTest.getAmount(2025), 0.001);
    }

    @Test
    void testGetAmount_CancelBeforeYear() {
        mdTest = new MonthlyDonations(30.0, LocalDateTime.of(2022, 1, 1, 0, 0));
        mdTest.setCancelDateTime(LocalDateTime.of(2023, 3, 1, 0, 0));
        assertEquals(0.0, mdTest.getAmount(2024), 0.001);
    }


    @Test
    void testToString_ActiveDonation() {
        String result = mdTest.toString();
        String expectedAmount = String.format("%.2f", mdTest.getAmount());

        assertTrue(result.contains("Monthly Donation"));
        assertTrue(result.contains(expectedAmount));
        assertTrue(result.contains("active"));
        assertTrue(result.contains("2020-01-01T15:12"));
    }

    @Test
    void testToString_CanceledDonation() {
        LocalDateTime cancelTime = LocalDateTime.of(2022, 6, 15, 10, 0);
        mdTest.setCancelDateTime(cancelTime);

        String result = mdTest.toString();
        String expectedAmount = String.format("%.2f", mdTest.getAmount());

        assertTrue(result.contains("Monthly Donation"));
        assertTrue(result.contains(expectedAmount));
        assertTrue(result.contains("2020-01-01T15:12"));
        assertTrue(result.contains("2022-06-15T10:00"));
        assertFalse(result.contains("active"));
    }
}