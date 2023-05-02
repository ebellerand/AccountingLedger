package com.learntocode;

import java.time.LocalDate;
import java.util.Scanner;

public class AccountingLedgerMain {
    public static void main(String[] args) {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                Ledger ledger = new Ledger();
                ledger.readTransactionsFromFile(ledger.filename);

                System.out.println("Welcome to your Accounting Ledger, where we help you track your transactions.");
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("What would you like to do? Enter 'D' to add a deposit, 'P' to make a payment, 'L' to display the ledger screen or 'X' to exit: ");
                System.out.println("Please enter your selection: ");

                String command = scanner.nextLine();

                if (command.equalsIgnoreCase("d")) {
                    ledger.addDeposit();
                } else if (command.equalsIgnoreCase("P")) {
                    ledger.makePayment();

                } else if (command.equalsIgnoreCase("L")) {
                    ledger.LedgerScreen();

                } else if (command.equalsIgnoreCase("X")) {
                    System.out.println("Goodbye! ");
                    return;

                } else {
                    System.out.println("Please enter a valid selection: ");
                    System.out.println("-----------------------------------");
                }

            } catch (Exception e) {
                System.out.println("There was an error.");
                System.out.println("----------------------");
            }

        }
    }
}
