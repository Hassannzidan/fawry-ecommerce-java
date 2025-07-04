//package com.fawry.challenge.fawryecommerce.model;
//import java.time.LocalDate;
//
//public class Cheese extends Product{
//    private final double weight;
//    private final LocalDate expiryDate;
//
//    public Cheese(String name, double price, int quantity, double weight, LocalDate expiryDate) {
//        super(name, price, quantity);
//        this.weight = weight;
//        this.expiryDate = expiryDate;
//    }
//
//    public boolean isExpired() {
//        return LocalDate.now().isAfter(expiryDate);
//    }
//
//    public double getWeight() { return weight; }
//}
package com.fawry.challenge.fawryecommerce.model;

import java.time.LocalDate;

public class Cheese extends Product implements Expirable, Shippable {

    private final double weight;
    private final LocalDate expiryDate;

    public Cheese(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }
}
