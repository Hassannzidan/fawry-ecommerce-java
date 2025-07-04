package com.fawry.challenge.fawryecommerce.customer;

public class Customer {

    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void pay(double amount) {
        if (amount > balance) {
            throw new RuntimeException("Insufficient balance.");
        }
        balance -= amount;
    }

    public String getName() {
        return name;
    }

}
