package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

class LeaseContractTest {

    @Test
    void testLeaseCalculation() {
        // Create test data and objects
        Vehicle vehicle = new Vehicle(2, 2023, "Honda", "Civic",
                "Sedan", "Black", 10000, 30000);
        //Create object for test the lease contract
        LeaseContract lease = new LeaseContract("20260515", "Jane Doe",
                "jane@email.com", vehicle);
        //It will check the results
        assertNotNull(lease.getMonthlyPayment());
    }
}