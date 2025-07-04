package com.fawry.challenge.fawryecommerce.service;

import com.fawry.challenge.fawryecommerce.customer.Customer;
import com.fawry.challenge.fawryecommerce.cart.CartItem;
import com.fawry.challenge.fawryecommerce.cart.Cart;
import com.fawry.challenge.fawryecommerce.model.Shippable;
import com.fawry.challenge.fawryecommerce.model.Expirable;
import com.fawry.challenge.fawryecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

//    public static void checkout(Customer customer, Cart cart) {
//        if (cart.isEmpty()) {
//            throw new RuntimeException("Cart is empty");
//        }
//
//        double subtotal = 0;
//        List<Shippable> shippableItems = new ArrayList<>();
//
//        for (CartItem item : cart.getItems()) {
//            Product product = item.product;
//
//            if (product instanceof Expirable) {
//                if (((Expirable) product).isExpired()) {
//                    throw new RuntimeException(product.getName() + " is expired");
//                }
//            }
//
//            if (item.quantity > product.getQuantity()) {
//                throw new RuntimeException(product.getName() + " is out of stock");
//            }
//
//            subtotal += item.getTotalPrice();
//
//            if (product instanceof Shippable) {
//                for (int i = 0; i < item.quantity; i++) {
//                    shippableItems.add((Shippable) product);
//                }
//            }
//        }
//
//        double shippingFee = shippableItems.isEmpty() ? 0 : 30;
//        double total = subtotal + shippingFee;
//
//        if (customer.getBalance() < total) {
//            throw new RuntimeException("Insufficient balance");
//        }
//
//        for (CartItem item : cart.getItems()) {
//            item.product.reduceQuantity(item.quantity);
//        }
//
//        customer.pay(total);
//
//        if (!shippableItems.isEmpty()) {
//            ShippingService.ship(shippableItems);
//        }
//
//        System.out.println("- * Checkout receipt **");
//        for (CartItem item : cart.getItems()) {
//            System.out.println(item.quantity + "x " + item.product.getName() + "    " + item.getTotalPrice());
//        }
//        System.out.println("------------------------");
//        System.out.println("Subtotal     " + subtotal);
//        System.out.println("Shipping     " + shippingFee);
//        System.out.println("Amount       " + total);
//        System.out.println("Remaining Balance: " + customer.getBalance());
//    }
    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double subtotal = 0;
        List<Shippable> shippableItems = new ArrayList<>();

        for (CartItem item : cart.getItems()) {
            Product product = item.product;

            if (product instanceof Expirable expirable) {
                if (expirable.isExpired()) {
                    throw new RuntimeException(product.getName() + " is expired");
                }
            }

            if (item.quantity > product.getQuantity()) {
                throw new RuntimeException(product.getName() + " is out of stock");
            }

            subtotal += item.getTotalPrice();

            if (product instanceof Shippable shippable) {
                for (int i = 0; i < item.quantity; i++) {
                    shippableItems.add(shippable);
                }
            }
        }

        double shippingFee = shippableItems.isEmpty() ? 0 : 30;
        double total = subtotal + shippingFee;

        if (customer.getBalance() < total) {
            throw new RuntimeException("Insufficient balance");
        }

        for (CartItem item : cart.getItems()) {
            item.product.reduceQuantity(item.quantity);
        }

        customer.pay(total);

        if (!shippableItems.isEmpty()) {
            ShippingService.ship(shippableItems);
        }

        //Formating       
        System.out.println("\n========== CHECKOUT RECEIPT ==========\n");
        for (CartItem item : cart.getItems()) {
            String nameWithQty = item.quantity + "x " + item.product.getName();
            double price = item.getTotalPrice();
            System.out.printf("%-30s %10.2f EGP\n", nameWithQty, price);

        }
        System.out.println("----------------------------------------");
        System.out.printf("%-25s %10.2f\n", "Subtotal", subtotal);
        System.out.printf("%-25s %10.2f\n", "Shipping", shippingFee);
        System.out.printf("%-25s %10.2f\n", "Amount", total);
        System.out.printf("%-25s %10.2f\n", "Remaining Balance:", customer.getBalance());
        System.out.println("\n===============================================\n");
    }

}
