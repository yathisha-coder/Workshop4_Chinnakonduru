package org.example;

import de.vandermeer.asciitable.AsciiTable;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    private Scanner scanner;
    private ContractFileManager contractFileManager;
    public AdminUserInterface(){
        scanner =new Scanner(System.in);
        contractFileManager = new ContractFileManager();
    }
    public void display(){
        while (true) {
            System.out.println("\u001B[34m=====ADMIN MENU=====\u001B[0m");
            System.out.println("1.View All Contracts");
            System.out.println("2.View Last 10 Contracts");
            System.out.println("99.Exit Admin");
            int option = Integer.parseInt(scanner.nextLine());
            switch (option) {
                case 1:
                    displayContracts(false);
                    break;
                case 2:
                    displayContracts(true);
                    break;
                case 99:
                    return;
                default:
                    System.out.println("Invalid option.Try again!");
            }
        }
    }
    private void displayContracts(boolean last10) {

        List<String> contracts = contractFileManager.getAllContracts();

        AsciiTable at = new AsciiTable();

        at.addRule();
        at.addRow("TYPE", "DATE", "NAME", "EMAIL", "TOTAL");
        at.addRule();

        int start = 0;

        if (last10 && contracts.size() > 10) {
            start = contracts.size() - 10;
        }

        for (int i = start; i < contracts.size(); i++) {

            String[] parts = contracts.get(i).split("\\|");

            at.addRow(
                    parts[0], // TYPE
                    parts[1], // DATE
                    parts[2], // NAME
                    parts[3], // EMAIL
                    parts[parts.length - 2] // TOTAL (adjust if needed)
            );

            at.addRule();
        }

        System.out.println(at.render());
    }
}