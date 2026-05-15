package org.example;

import de.vandermeer.asciitable.AsciiTable;
import org.stringtemplate.v4.ST;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private  Dealership dealership;
    private Scanner scanner;

    public UserInterface(){
        this.scanner = new Scanner(System.in);
        init();
    }

    public void display(){
    init();
    int option;
    while(true){
        displayMenu();
        option = Integer.parseInt(scanner.nextLine());
        switch (option){
            case 1:
                processGetByPriceRequest();
                break;
            case 2:
                processGetByMakeModelRequest();
                break;
            case 3:
                processGetByYearRequest();
                break;
            case 4:
                processGetByColorRequest();
                break;
            case 5:
                processGetByMileageRequest();
                break;
            case 6:
                processGetByVehicleTypeRequest();
                break;
            case 7:
                processGetAllVehiclesRequest();
                break;
            case 8:
                processAddVehicleRequest();
                break;
            case 9:
                processRemoveVehicleRequest();
                break;
            case 10:
                processContractRequest();
                break;
            case 99:
                System.out.println("\u001B[36mThank you! Come visit us again!\u001b[0m");
                System.exit(0);
            default:
                System.out.println("\u001B[31mInvalid option. Please Try again!\u001B[0m");
        }
    }
    }
    private void init(){
        DealershipFileManager fileManager = new DealershipFileManager();
        this.dealership = fileManager.getDealership();
    }
    private void displayMenu(){
        System.out.println("\u001B[33m  Welcome to Dealership menu\u001B[0m ");
        System.out.println("1.Find vehicles within a price range");
        System.out.println("2.Find vehicles by make/model");
        System.out.println("3.Find vehicles by year range");
        System.out.println("4.Find vehicles by color");
        System.out.println("5.Find vehicles by mileage range");
        System.out.println("6.Find vehicles by type (van, truck, SUV, etc.)");
        System.out.println("7.List All Vehicles");
        System.out.println("8.Add a vehicle");
        System.out.println("9.Remove a vehicle");
        System.out.println("10.Sell (or) Lease a vehicle");
        System.out.println("99.Quit");
        System.out.println("Enter you're option: ");
    }
    public void processGetByPriceRequest(){
        System.out.println("Enter Minimum Price: ");
        double minPrice = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter Maximum Price: ");
        double maxPrice = Double.parseDouble(scanner.nextLine());
        List<Vehicle> vehicles = dealership.getVehicleByPrice(minPrice, maxPrice);
        displayVehicles(vehicles);
    }
    public void processGetByMakeModelRequest(){
        System.out.println("Enter Make: ");
        String make = scanner.nextLine();
        System.out.println("Enter Model: ");
        String model = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(vehicles);
    }
    public void processGetByYearRequest(){
        System.out.println("Enter Minimum Year: ");
        int minYear = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Maximum Year: ");
        int maxYear = Integer.parseInt(scanner.nextLine());
        List<Vehicle> vehicles = dealership.getVehiclesByYear(minYear, maxYear);
        displayVehicles(vehicles);
    }
    public void processGetByColorRequest(){
        System.out.println("Enter Color: ");
        String color = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
        displayVehicles(vehicles);
    }
    public void processGetByMileageRequest(){
        System.out.println("Enter Minimum Mileage: ");
        int minMileage = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Maximum Mileage: ");
        int maxMileage = Integer.parseInt(scanner.nextLine());
        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);
        displayVehicles(vehicles);
    }
    public void processGetByVehicleTypeRequest(){
        System.out.println("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        List<Vehicle> vehicles = dealership.getVehiclesByType(vehicleType);
        displayVehicles(vehicles);
    }
    public void processGetAllVehiclesRequest(){
         List<Vehicle> vehicles = dealership.getAllVehicles();
         displayVehicles(vehicles);
    }
    public void processAddVehicleRequest(){
        System.out.println("Enter Vin: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter  Vehicle Make: ");
        String make = scanner.nextLine();
        System.out.println("Enter Vehicle Model: ");
        String model = scanner.nextLine();
        System.out.println("Enter Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.println("Enter Vehicle Color: ");
        String color = scanner.nextLine();
        System.out.println("Enter Vehicle Mileage: ");
        int mileage = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter Vehicle Price: ");
        double price = Double.parseDouble(scanner.nextLine());
        Vehicle newVehicle = new Vehicle(vin, year,make,
                model,vehicleType,color,mileage,price);
        dealership.addVehicle(newVehicle);
        DealershipFileManager fileManager = new DealershipFileManager();
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle Added!");
    }
    public void processRemoveVehicleRequest(){
        System.out.println("Enter vin to remove from the list: ");
        int vin = Integer.parseInt(scanner.nextLine());
        Vehicle foundVehicle = null;
        for (Vehicle vehicle : dealership.getAllVehicles()){
            if(vehicle.getVin() == vin){
                foundVehicle = vehicle;
                break;
            }
        }
        if (foundVehicle != null){
            dealership.removeVehicle(foundVehicle);
            DealershipFileManager fileManager =new DealershipFileManager();
            fileManager.saveDealership(dealership);
            System.out.println("Vehicle Removed!");;
        }else{
            System.out.println("Vehicle not found!");
        }
    }
    private void displayVehicles(List<Vehicle> vehicles){
        System.out.println("\u001B[33m=======Vehicle Inventory=============\u001B[0m");
        AsciiTable at = new AsciiTable();
        at.addRule();
        at.addRow("Vin","Year","Make","Model","Type","Color","Miles","Price");
        at.addRule();
        for(Vehicle vehicle : vehicles){
           at.addRow(vehicle.getVin(),
                   vehicle.getYear(),
                   vehicle.getMake(),
                   vehicle.getModel(),
                   vehicle.getVehicleType(),
                   vehicle.getColor(),
                   vehicle.getOdometer(),
                   String.format("%.2f",vehicle.getPrice())
                   );
                at.addRule();
        }
        System.out.println(at.render());
    }
    public void processContractRequest() {

        System.out.println("Enter VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        Vehicle selectedVehicle = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {

                selectedVehicle = vehicle;
                break;
            }
        }
        if (selectedVehicle == null) {

            System.out.println("Vehicle not found.");
            return;
        }
        String date =
                String.valueOf(java.time.Year.now().getValue());
        System.out.println("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.println("Enter Customer Email: ");
        String customerEmail = scanner.nextLine();
        System.out.println("SALE or LEASE?");
        String contractType = scanner.nextLine();
        Contract contract;
        if (contractType.equalsIgnoreCase("SALE")) {
            System.out.println("Finance? YES/NO");
            boolean finance =
                    scanner.nextLine().equalsIgnoreCase("YES");
            contract = new SalesContract(
                    date,
                    customerName,
                    customerEmail,
                    selectedVehicle,
                    finance
            );
        } else {
            int currentYear =
                    java.time.Year.now().getValue();
            if (currentYear - selectedVehicle.getYear() > 3) {
                System.out.println("Vehicle too old to lease.");
                return;
            }
            contract = new LeaseContract(
                    date,
                    customerName,
                    customerEmail,
                    selectedVehicle
            );
        }
        ContractFileManager contractFileManager =
                new ContractFileManager();

        contractFileManager.saveContract(contract);

        dealership.removeVehicle(selectedVehicle);

        DealershipFileManager dealershipFileManager =
                new DealershipFileManager();

        dealershipFileManager.saveDealership(dealership);

        System.out.println("Contract saved successfully!");
    }
}
