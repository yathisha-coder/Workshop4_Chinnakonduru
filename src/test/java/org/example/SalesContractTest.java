package org.example;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest {
    @Test
    public void testGetSalesTax() {
        // Create test data and objects
        Vehicle vehicle = new Vehicle(101, 2022, "Toyota", "Camry",
                "Sedan", "Black", 15000, 20000);

        SalesContract contract = new SalesContract("20260515", "John Doe",
                "john@email.com", vehicle, true);

        BigDecimal expected = BigDecimal.valueOf(1000.00).setScale(2);

        // Call the method being tested
        BigDecimal actual = contract.getSalesTax();

        // Compare expected vs actual result
        assertEquals(expected, actual);
    }
}

