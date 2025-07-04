package com.fawry.challenge.fawryecommerce.service;

import com.fawry.challenge.fawryecommerce.model.Shippable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {

    public static void ship(List<Shippable> items) {
        System.out.println("\n- * Shipment notice **\n");

        double totalWeight = 0;
        Map<String, Integer> itemCount = new HashMap<>();
        Map<String, Double> itemWeight = new HashMap<>();

        for (Shippable item : items) {
            itemCount.put(item.getName(), itemCount.getOrDefault(item.getName(), 0) + 1);
            itemWeight.put(item.getName(), item.getWeight());
            totalWeight += item.getWeight();
        }

        for (String name : itemCount.keySet()) {
            int count = itemCount.get(name);
            double totalItemWeight = itemWeight.get(name) * count;
            System.out.printf("%dx %-15s %6.0fg\n", count, name, totalItemWeight * 1000);
        }
        
        System.out.println("Total package weight " + totalWeight + "kg\n");
    }
}
