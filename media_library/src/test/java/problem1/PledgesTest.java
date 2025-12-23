package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PledgesTest {

    Pledges pTest1;
    Pledges pTest2;

    @BeforeEach
    void setUp() {
        pTest1 = new Pledges(500.0, LocalDateTime.of(2024, 12, 2, 3, 0), null);
        pTest2 = new Pledges(500.0, LocalDateTime.of(2024, 12, 2, 3, 0),
                LocalDateTime.of(2030, 5, 1, 0, 0));
    }

    @Test
    void testGetProcessDateTime() {
        assertNull(pTest1.getProcessDateTime());
        assertEquals(LocalDateTime.of(2030, 5, 1, 0, 0), pTest2.getProcessDateTime());
    }

    @Test
    void testSetProcessDateTimeValid() {
        LocalDateTime newTime = LocalDateTime.of(2025, 1, 1, 0, 0);
        pTest1.setProcessDateTime(newTime);
        assertEquals(newTime, pTest1.getProcessDateTime());
    }

    @Test
    void testSetProcessDateTimeInvalid() {
        LocalDateTime earlier = LocalDateTime.of(2024, 1, 1, 0, 0);
        assertThrows(IllegalArgumentException.class, () -> pTest1.setProcessDateTime(earlier));
    }

    @Test
    void testGetAmount_NoProcessDate() {
        assertEquals(0.0, pTest1.getAmount(), 0.001);
    }

    @Test
    void testGetAmount_FutureProcessDate() {
        assertEquals(0.0, pTest2.getAmount(), 0.001);
    }

    @Test
    void testGetAmount_PastProcessDate() {
        Pledges pPast = new Pledges(300.0, LocalDateTime.of(2023, 5, 1, 0, 0),
                LocalDateTime.of(2023, 6, 1, 0, 0));
        assertEquals(300.0, pPast.getAmount(), 0.001);
    }

    @Test
    void testGetAmountByYear_NoProcessDate() {
        assertEquals(0.0, pTest1.getAmount(2024), 0.001);
    }

    @Test
    void testGetAmountByYear_MatchingYear() {
        Pledges p = new Pledges(400.0, LocalDateTime.of(2024, 12, 2, 3, 0),
                LocalDateTime.of(2025, 3, 15, 0, 0));
        assertEquals(400.0, p.getAmount(2025), 0.001);
    }

    @Test
    void testGetAmountByYear_NonMatchingYear() {
        assertEquals(0.0, pTest2.getAmount(2025), 0.001);
    }

    @Test
    void testToString_NoProcessDate() {
        String result = pTest1.toString();
        assertTrue(result.contains("Pledge"));
        assertTrue(result.contains("500.00"));
        assertTrue(result.contains("process date to be determined"));
        assertTrue(result.contains("2024-12-02T03:00"));
    }

    @Test
    void testToString_FutureProcessDate() {
        String result = pTest2.toString();
        assertTrue(result.contains("Pledge"));
        assertTrue(result.contains("0.0"));
        assertTrue(result.contains("to be processed on"));
        assertTrue(result.contains("2030-05-01T00:00"));
    }

    @Test
    void testToString_PastProcessDate() {
        Pledges pPast = new Pledges(250.0, LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2023, 3, 1, 0, 0));
        String result = pPast.toString();
        assertTrue(result.contains("Pledge"));
        assertTrue(result.contains("250.00"));
        assertTrue(result.contains("processed on"));
        assertTrue(result.contains("2023-03-01T00:00"));
    }
}