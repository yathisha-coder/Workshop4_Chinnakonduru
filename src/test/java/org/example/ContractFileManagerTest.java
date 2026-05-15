package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContractFileManagerTest {

    @Test
    void testFileSave() {
       //create ContractFileManager object
        ContractFileManager manager = new ContractFileManager();
       //create sample vehicle object
        Vehicle vehicle = new Vehicle(
                3, 2022, "Ford", "F150",
                "Truck", "Red", 20000, 25000
        );
        //create sample sales contract
        SalesContract contract = new SalesContract(
                "20260515",
                "Mike",
                "mike@email.com",
                vehicle,
                false
        );
        //it will check the save contract if it doesn't throw exception       assertDoesNotThrow(() -> manager.saveContract(contract));
    }
}