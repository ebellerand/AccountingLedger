package com.learntocode;

import java.util.Scanner;

public class Reports {
    public void reportsScreen() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Reports Screen where you can filter transactions.");
            System.out.println("----------------------------------------------------------------");
            System.out.println("How would you like to filter results: 1) Month to Date, 2) Previous Month, 3) Year To Date, 4) Previous Date, 5) Search by Vendor, 6) Custom Search, 0) Back ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Please enter your selection: ");
            int command = scanner.nextInt();

            switch (command) {
                case 1:

                    break;
                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;

                case 5:

                    break;

                case 6:

                    break;

                default:
                    System.out.println("Invalid selection ");
            }

        } catch (Exception e) {
            System.out.println("There was an error. ");
            System.out.println("-----------------------------------");
        }
    }
}
