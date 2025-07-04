package com.fawry.challenge.fawryecommerce.test;

import com.fawry.challenge.fawryecommerce.cart.Cart;
import com.fawry.challenge.fawryecommerce.customer.Customer;
import com.fawry.challenge.fawryecommerce.model.Cheese;
import com.fawry.challenge.fawryecommerce.model.Product;
import com.fawry.challenge.fawryecommerce.service.CheckoutService;
import java.time.LocalDate;

public class EdgeCaseTester {

    public static void runAllTests() {
        System.out.println("=== Edge Case Tests ===\n");

        // ✅ 1. Cart is empty
        try {
            Customer emptyCartCustomer = new Customer("Ali", 1000);
            Cart emptyCart = new Cart();
            CheckoutService.checkout(emptyCartCustomer, emptyCart);
        } catch (Exception e) {
            System.out.println("✔️ Cart is empty: " + e.getMessage());
        }

        // ✅ 2. Expired product
        try {
            Customer expiredProductCustomer = new Customer("Mona", 1000);
            Product expired = new Cheese("Expired Cheese", 100, 2, 0.5, LocalDate.of(2020, 1, 1));
            Cart cart = new Cart();
            cart.add(expired, 1);
            CheckoutService.checkout(expiredProductCustomer, cart);
        } catch (Exception e) {
            System.out.println("✔️ Expired product: " + e.getMessage());
        }

        // ✅ 3. Out of stock
        try {
            Customer outOfStockCustomer = new Customer("Sara", 1000);
            Product lowStockProduct = new Cheese("Cheese", 100, 1, 0.5, LocalDate.of(2025, 8, 1));
            Cart cart = new Cart();
            cart.add(lowStockProduct, 2);
            CheckoutService.checkout(outOfStockCustomer, cart);
        } catch (Exception e) {
            System.out.println("✔️ Out of stock: " + e.getMessage());
        }

        // ✅ 4. Insufficient balance
        try {
            Customer lowBalanceCustomer = new Customer("Youssef", 10);
            Product cheese = new Cheese("Cheese", 100, 2, 0.5, LocalDate.of(2025, 8, 1));
            Cart cart = new Cart();
            cart.add(cheese, 1);
            CheckoutService.checkout(lowBalanceCustomer , cart);
        } catch (Exception e) {
            System.out.println("✔️ Insufficient balance: " + e.getMessage());
        }

        System.out.println("\n=== End of Tests ===\n");
    }
}
