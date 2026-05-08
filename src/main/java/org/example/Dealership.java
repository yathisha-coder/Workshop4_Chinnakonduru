package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    public List<Vehicle> inventory = new ArrayList<>();;

    public Dealership(String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }
    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }
    public List<Vehicle> getAllVehicles(){
        return inventory;
    }
    public List<Vehicle> getVehicleByPrice(double min, double max){
        List<Vehicle> results = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getPrice() >= min &&
            vehicle.getPrice() <= max){
                results.add(vehicle);
            }
        }
        return results;
    }
    public List<Vehicle> getVehiclesByMakeModel(String make, String model){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if(vehicle.getMake().equalsIgnoreCase(make) &&
                    vehicle.getModel().equalsIgnoreCase(model)){
                results.add(vehicle);
            }
        }
        return results;
    }
    public  List<Vehicle> getVehiclesByYear(int min, int max){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if(vehicle.getYear() >= min &&
            vehicle.getYear() <= max){
                results.add(vehicle);
            }
        }
        return  results;
    }
    public List<Vehicle> getVehiclesByColor(String color){
        List<Vehicle> results = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getColor().equalsIgnoreCase(color)){
                results.add(vehicle);
            }
        }
        return  results;
    }
    public List<Vehicle> getVehiclesByMileage(int min, int max){
        List<Vehicle> results = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if(vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                results.add(vehicle);
            }
        }
        return results;
    }
    public List<Vehicle> getVehiclesByType(String vehicleType){
        List<Vehicle> results = new ArrayList<>();
        for(Vehicle vehicle : inventory){
            if(vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                results.add(vehicle);
            }
        }
        return  results;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public  String getPhone(){
        return phone;
    }

}
