package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContractFileManager {
    public void saveContract(Contract contract){
        try (BufferedWriter writer =new BufferedWriter(new FileWriter("src/main/resources/contracts.csv",true))){
            Vehicle vehicle = contract.getVehicleSold();
            if (contract instanceof SalesContract sale){
                writer.write("SALE|" + sale.getDate() + "|" +
                        sale.getCustomerName() + "|" +
                        sale.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        sale.getSalesTax() + "|" +
                        sale.getRecordingFee() + "|" +
                        sale.getProcessingFee() + "|" +
                        sale.getTotalPrice() + "|" +
                        sale.getFinance() + "|" +
                        sale.getMonthlyPayment());
                writer.write("\n");
                AsciiTable at = new AsciiTable();
                at.addRule();
                at.addRow("TYPE","CUSTOMER","VEHICLE","TOTAL","MONTHLY");
                at.addRule();

                at.addRow("SALE",sale.getCustomerName(),vehicle.getMake() +" " + vehicle.getModel(),
                        sale.getTotalPrice(),sale.getMonthlyPayment());
                at.addRule();
                System.out.println(at.render());
            } else if (contract instanceof LeaseContract lease) {
                writer.write("LEASE|" +lease.getDate() + "|" +
                        lease.getCustomerName() + "|" +
                        lease.getCustomerEmail() + "|" +
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice() + "|" +
                        lease.getExpectedEndingValue() + "|" +
                        lease.getLeaseFee() + "|" +
                        lease.getTotalPrice() + "|" +
                        lease.getMonthlyPayment());
                writer.write("\n");
                AsciiTable at = new AsciiTable();
                at.addRule();
                at.addRow("TYPE","CUSTOMER","VEHICLE","TOTAL","MONTHLY");
                at.addRule();
                at.addRow("LEASE", lease.getCustomerName(),vehicle.getMake() + " " +
                        vehicle.getModel(),lease.getTotalPrice(),lease.getMonthlyPayment());
                at.addRule();
                System.out.println(at.render());
                writer.close();
            }
        }catch (IOException e){
            System.out.println("Something went wrong to saving contract. Try again!");

        }
    }
    public List<String> getAllContracts() {

        List<String> contracts = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(
                             new FileReader("src/main/resources/contracts.csv"))) {

            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    contracts.add(line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading contracts file");
        }

        return contracts;
    }

}
