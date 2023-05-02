package com.learntocode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Transaction { //Define attributes
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private float amount;
    private String type;

    //Constructor
    public Transaction(LocalDate date, LocalTime time, String description, String vendor, float amount) throws IOException {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.type = type;
    }
    //Getters and Setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;

    }

    public String getType() {
        return "Transaction";
    }

    public void setType(String type) {
        this.type = type;
    }

    class Deposit extends Transaction {
        public Deposit(LocalDate date, LocalTime time, String description, String vendor, float amount) throws IOException {
            super(date, time, description, vendor, amount);

        }

        class Payment extends Transaction {
            public Payment(LocalDate date, LocalTime time, String description, String vendor, float amount) throws IOException {
                super(date, time, description, vendor, amount);
            }
        }
    }
}