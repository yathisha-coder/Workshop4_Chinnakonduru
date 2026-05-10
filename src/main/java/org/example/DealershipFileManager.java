package org.example;

import java.io.*;


public class DealershipFileManager {
    public Dealership getDealership(){
        Dealership dealership = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Inventory.csv"));
            String input = reader.readLine();
            String[] dealershipInfo = input.split("\\|");
            dealership = new Dealership(dealershipInfo[0],
                    dealershipInfo[1], dealershipInfo[2]);
            while ((input = reader.readLine()) != null){
                String[] parts = input.split("\\|");
                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String type = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
                dealership.addVehicle(vehicle);


            }
            reader.close();
        }catch (IOException e){
            System.out.println("Error reading with inventory file.");
        }catch (Exception ex){
            System.out.println("Something went wrong in the file.");
        }
        return dealership;
    }
    public void  saveDealership(Dealership dealership){
        try{
            File file = new File("src/main/resources/Inventory.csv");
            FileWriter writer = new FileWriter(file);
            writer.write(dealership.getName() + "|" +
                    dealership.getAddress() + "|" +
                    dealership.getPhone());
            writer.write("\n");
            for(Vehicle vehicle : dealership.getAllVehicles()){
                writer.write(vehicle.getVin() +"|"+
                        vehicle.getYear()+"|"+
                        vehicle.getMake()+"|"+
                        vehicle.getModel()+"|"+
                        vehicle.getVehicleType()+"|"+
                        vehicle.getColor()+"|"+
                        vehicle.getOdometer()+"|"+
                        vehicle.getPrice());
                writer.write("\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println("Error writing to file.");
        }catch (Exception e){
            System.out.println("Something went wrong.");
        }
    }

}
