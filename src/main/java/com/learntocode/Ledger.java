package com.learntocode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Ledger {
    String filename = "transactions.csv";
    private List<Transaction> transactions;

    public Ledger() {
        transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void readTransactionsFromFile(String filename) throws IOException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                LocalDate date = LocalDate.parse(fields[0], dateFormatter);
                LocalTime time = LocalTime.parse(fields[1], timeFormatter);
                String description = fields[2];
                String vendor = fields[3];
                float amount = Float.parseFloat(fields[4]);
                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                addTransaction(transaction);


            }
        } catch (IOException e) {
            System.out.println("There has been an error. Sorry for the inconvenience. ");
            System.out.println("-------------------------------------------------------");
        }
    }
    Scanner scanner = new Scanner(System.in);
    public void makePayment() throws IOException {

        try {
            System.out.println("Please enter the payment date in yyyy-MM-dd format: ");
            LocalDate date = LocalDate.parse(scanner.nextLine());

            System.out.println("Enter time in HH:mm:ss format: ");
            LocalTime time = LocalTime.parse(scanner.nextLine());

            System.out.println("Enter description: ");
            String description = scanner.nextLine();

            System.out.println("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.println("Enter amount: ");
            float amount = scanner.nextFloat();
            scanner.nextLine();

            Transaction payment = new Transaction(date, time, description, vendor, amount);
            addTransaction(payment);

            System.out.println("Transaction added to ledger. ");

        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date/time format. ");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input for amount. ");
            scanner.nextLine();
        }
    }

        public void addDeposit() throws IOException {
            try {
                System.out.println("Please enter the deposit date in yyyy-MM-dd format: ");
                LocalDate date = LocalDate.parse((scanner.nextLine()));

                System.out.println("Enter time in HH:mm:ss format: ");
                LocalTime time = LocalTime.parse(scanner.nextLine());

                System.out.println("Enter description: ");
                String description = scanner.nextLine();

                System.out.println("Enter payer: ");
                String payer = scanner.nextLine();

                System.out.println("Enter amount: ");
                float amount = scanner.nextFloat();
                scanner.nextLine();

                Transaction deposit = new Transaction(date, time, description, payer, amount);
                addTransaction(deposit);

                System.out.println("Deposit added to ledger. ");
            } catch (DateTimeParseException e) {
                System.out.println("Error parsing date/time format. ");
            } catch (InputMismatchException e) {
                System.out.println("Invalid input for amount. ");
                scanner.nextLine();
            }
        }

       /* public void makePayment(Scanner scanner) throws IOException {
                try {
                    System.out.println("Please enter the payment date in yyyy-MM-dd format: ");
                    LocalDate date = LocalDate.parse((scanner.nextLine()));

                    System.out.println("Enter time in HH:mm:ss format: ");
                    LocalTime time = LocalTime.parse(scanner.nextLine());

                    System.out.println("Enter description: ");
                    String description = scanner.nextLine();

                    System.out.println("Enter payer: ");
                    String payer = scanner.nextLine();

                    System.out.println("Enter amount: ");
                    float amount = scanner.nextFloat();
                    scanner.nextLine();

                    Transaction payment = new Transaction(date, time, description, payer, amount);
                    addTransaction(payment);

                    System.out.println("Payment added to ledger. ");
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing date/time format. ");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for amount. ");
                    scanner.nextLine();
                }
                 } */

    public void displayAllTransactions() {
        System.out.println("Here are all your transactions: ");
        System.out.println("--------------------------------");
        if (transactions.size() <= 0) {
            System.out.println("You have no transactions. ");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println("Type: " + transaction.getType());
                System.out.println("Date: " + transaction.getDate());
                System.out.println("Time: " + transaction.getTime());
                System.out.println("Description: " + transaction.getDescription());
                System.out.println("Vendor: " + transaction.getVendor());
                System.out.println("Amount " + transaction.getAmount());
                System.out.println("-----------------------------------------------");
            }
        }
    }  public void displayDeposits() {
        System.out.println("Here are your deposits: ");
        System.out.println("----------------------------------------------------------");
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getTime() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }

    } public void displayPayments() {
        System.out.println("Here are your payments: ");
        System.out.println("-------------------------------------------------------------");
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getTime() + " " + transaction.getVendor() + " $" + transaction.getAmount());
            }
        }
    }
    public void determineType() {
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0.0f) {

    transaction.setType("Deposit");

            } else {
    transaction.setType("Payment");
            }
        }
    }
            public void LedgerScreen() {
        try {
            System.out.println("Ledger: ");
            System.out.println("---------------------------------------------------------");
            System.out.println("What would you like to do? 'A' to display all entries, 'D' to display all deposits, 'P' to display all payments, 'R' for reports.");
            System.out.println("---------------------------");
            System.out.println("Please enter your selection: ");

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("A")) {
                displayAllTransactions();
            } else if (command.equalsIgnoreCase("D")) {
                displayDeposits();
            } else if (command.equalsIgnoreCase("P")) {
                displayPayments();

            } else if (command.equalsIgnoreCase("R")) {
                reportsScreen();
            }

        } catch (Exception e) {
            System.out.println("There was an error.");
            System.out.println("----------------------");
        }
    }
}


