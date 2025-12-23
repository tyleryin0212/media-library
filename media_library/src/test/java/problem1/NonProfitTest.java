package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NonProfitTest {

    NonProfit nonProfit;
    List<Donations> donationsList;

    @BeforeEach
    void setUp() {
        donationsList = new ArrayList<>();

        // Add a one-time donation in 2024
        donationsList.add(new OneTimeDonations(200.0, LocalDateTime.of(2024, 5, 1, 12, 0)));

        // Add a monthly donation starting in 2023 (still active)
        donationsList.add(new MonthlyDonations(50.0, LocalDateTime.of(2023, 6, 1, 0, 0)));


        // Add a pledge processed in 2025
        donationsList.add(new Pledges(500.0, LocalDateTime.of(2024, 12, 1, 0, 0),
                LocalDateTime.of(2025, 2, 1, 0, 0)));

        nonProfit = new NonProfit("Charity", donationsList);
    }

    @Test
    void testGetTotalDonationsForYear_2024() {
        // Only the one-time donation and part of monthly apply
        double total = nonProfit.getTotalDonationsForYear(2024);

        // 200 (one-time) + 12 * 50 (monthly)
        double expected = 200.0 + (12 * 50.0);
        assertEquals(expected, total, 0.001);
    }

    @Test
    void testGetTotalDonationsForYear_2025() {
        double total = nonProfit.getTotalDonationsForYear(2025);

        // monthly + pledge processed in 2025
        double expected = (12 * 50.0) + 500.0;
        assertEquals(expected, total, 0.001);
    }

    @Test
    void testGetTotalDonationsForYear_NoDonations() {
        double total = nonProfit.getTotalDonationsForYear(2020);
        assertEquals(0.0, total, 0.001);
    }

    @Test
    void testEmptyDonationList() {
        NonProfit emptyNonProfit = new NonProfit("Empty Org", new ArrayList<>());
        assertEquals(0.0, emptyNonProfit.getTotalDonationsForYear(2025), 0.001);
    }
}