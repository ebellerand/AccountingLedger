package com.learntocode;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Reports {
    public void reportsScreen(List<Transaction> transactions) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Reports Screen where you can filter transactions.");
            System.out.println("----------------------------------------------------------------");
            System.out.println("How would you like to filter results: 1) Month to Date, 2) Previous Month, 3) Year To Date, 4) Previous Year, 5) Search by Vendor, 6) Custom Search, 0) Back ");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Please enter your selection: ");
            int command = scanner.nextInt();

            LocalDate currentDate = LocalDate.now();
            int currentMonth = currentDate.getMonthValue();
            int currentYear = currentDate.getYear();

            switch (command) {

                case 1:

                    List<Transaction> monthToDateTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();

                        if (transactionDate.getYear() == currentYear && transactionDate.getMonthValue() == currentMonth) {
                            monthToDateTransactions.add(transaction);
                        }
                    }

                    System.out.println("Month to Date Transactions: ");
                    System.out.println("--------------------------------------------");
                    for (Transaction transaction : monthToDateTransactions) {
                        System.out.println(transaction.getType() + " " + transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());

                    }
                    if (monthToDateTransactions.isEmpty()) {
                        System.out.println("No transactions found. ");
                    }

                    break;

                case 2:
                    LocalDate firstDayOfPrevMonth = LocalDate.of(currentYear, currentMonth - 1, 1);
                    LocalDate lastDayOfPrevMonth = firstDayOfPrevMonth.withDayOfMonth(firstDayOfPrevMonth.lengthOfMonth());
                    List<Transaction> prevMonthTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();

                        if (transactionDate.isAfter(firstDayOfPrevMonth.minusDays(1)) && transactionDate.isBefore(lastDayOfPrevMonth.plusDays(1))) {
                            prevMonthTransactions.add(transaction);

                        } else if (prevMonthTransactions.isEmpty()) {
                            System.out.println("No transactions found. ");
                        }
                    }
                    System.out.println("Previous month transactions: ");
                    System.out.println("-----------------------------------");
                    for (Transaction prevMonthTransaction : prevMonthTransactions) {
                        System.out.println(prevMonthTransaction.getType() + " " + prevMonthTransaction.getDate() + " " + prevMonthTransaction.getDescription() + " " + prevMonthTransaction.getVendor() + " $" + prevMonthTransaction.getAmount());
                    }
                    break;

                case 3:
                    LocalDate today = LocalDate.now();
                    LocalDate firstDayOfTheYear = LocalDate.of(currentYear, 1, 1);
                    List<Transaction> yearToDateTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();
                        if ((transactionDate.getYear() == currentYear) && transactionDate.isEqual(firstDayOfTheYear) || transactionDate.isAfter(firstDayOfTheYear) && transactionDate.isBefore(today) && transactionDate.isEqual(today)) {
                            yearToDateTransactions.add(transaction);

                        }

                        System.out.println("Year-to-Date transactions: ");
                        System.out.println("---------------------------------------------------");
                        for (Transaction yearToDateTransaction : yearToDateTransactions) {
                            System.out.println(yearToDateTransaction.getType() + " " + yearToDateTransaction.getDate() + " " + yearToDateTransaction.getTime() + " " + yearToDateTransaction.getVendor() + " " + yearToDateTransaction.getDescription() + " " + yearToDateTransaction.getAmount());
                        }

                        if (yearToDateTransactions.isEmpty()) {
                            System.out.println("No transactions found. ");
                        }
                    }
                    break;
                case 4:
                    // LocalDate firstDayOfLastYear = currentDate.withDayOfYear(1).minusYears(1);
                    LocalDate lastYear = currentDate.withYear(currentYear - 1);
                    List<Transaction> lastYearTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        LocalDate transactionDate = transaction.getDate();
                        if (transactionDate.isEqual(lastYear)) {
                            lastYearTransactions.add(transaction);

                            System.out.println("Last Year's transactions: ");
                            System.out.println("-----------------------------------------------");
                            for (Transaction lastYearTransaction : lastYearTransactions) {
                                System.out.println(lastYearTransaction.getType() + " " + lastYearTransaction.getDate() + " " + lastYearTransaction.getDescription() + " " + lastYearTransaction.getVendor() + " $" + lastYearTransaction.getAmount());
                            }

                        } else if (lastYearTransactions.isEmpty()) {
                            System.out.println("No transactions found. ");
                        }
                    }

                    break;

                case 5:
                    System.out.println("Please enter the vendor: ");
                    scanner.nextLine();
                    String searchVendor = scanner.nextLine();
                    List<Transaction> vendorTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        if (searchVendor.equalsIgnoreCase(transaction.getVendor())) {
                            vendorTransactions.add(transaction);
                        }
                    }

                    System.out.println("Transactions for vendor: " + searchVendor);
                    System.out.println("------------------------------------------");
                    for (Transaction transaction : vendorTransactions) {
                        System.out.println(transaction.getType() + " " + transaction.getDate() + " " + transaction.getTime() + " " + transaction.getDescription() + " " + transaction.getVendor() + " $" + transaction.getAmount());
                    }

                    if (vendorTransactions.isEmpty()) {
                        System.out.println("No transactions found. ");
                    }

                    break;

                case 6:
                    //try {
                    System.out.println("Custom Search:");
                    System.out.println("---------------------------------------------");
                    System.out.println("Please enter the following search fields: ");
                    System.out.println("Start Date (yyyy-MM-dd)");
                    scanner.nextLine();
                    String searchStartDateStr = scanner.nextLine();
                    LocalDate searchStartDate = LocalDate.parse(searchStartDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

                    System.out.println("End Date (yyyy-MM-dd)");
                    String searchEndDateStr = scanner.nextLine();
                    LocalDate searchEndDate = LocalDate.parse(searchEndDateStr, DateTimeFormatter.ISO_LOCAL_DATE);

                    System.out.println("Description: ");
                    String searchDescription = scanner.nextLine();

                    System.out.println("Vendor: ");
                    String customSearchVendor = scanner.nextLine();

                    System.out.println("Amount: ");
                    float searchAmount = scanner.nextFloat();
                    scanner.nextLine();

                    List<Transaction> customSearchTransactions = new ArrayList<>();
                    for (Transaction transaction : transactions) {
                        if ((transaction.getDate().isAfter(searchStartDate) ||
                                transaction.getDate().isEqual(searchStartDate) && transaction.getDate().isBefore(searchEndDate) ||
                                transaction.getDate().isEqual(searchEndDate)) &&
                                transaction.getVendor().equalsIgnoreCase(customSearchVendor) &&
                                transaction.getDescription().equalsIgnoreCase(searchDescription) && transaction.getAmount() == searchAmount) {
                            customSearchTransactions.add(transaction);

                        }
                    }
                    if (!customSearchTransactions.isEmpty()) {
                        System.out.println("Custom Search Results: ");
                        System.out.println("-----------------------------------");
                        for (Transaction customTransaction : customSearchTransactions) {
                            System.out.println(customTransaction.getType() + " " + customTransaction.getDate() + " " + customTransaction.getTime() + " " + customTransaction.getDescription() + " " + customTransaction.getVendor() + " $" + customTransaction.getAmount());
                        }
                    } else {
                        System.out.println("No transactions found. ");
                    }
                    /*} catch (Exception e) {
                        System.out.println("There was an error. "); */
            //}

                    break;
                case 0:
                    return;

                default:
                    System.out.println("Invalid selection ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. ");

       /* } catch(Exception e){
                System.out.println("There was an error. ");
                System.out.println("-----------------------------------"); */
            }
        }
    }

