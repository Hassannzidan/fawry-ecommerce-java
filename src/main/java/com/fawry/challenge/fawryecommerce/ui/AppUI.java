package com.fawry.challenge.fawryecommerce.ui;

import com.fawry.challenge.fawryecommerce.model.*;
import com.fawry.challenge.fawryecommerce.cart.*;
import com.fawry.challenge.fawryecommerce.customer.*;
import com.fawry.challenge.fawryecommerce.service.*;

import javax.swing.*;
import java.awt.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

public class AppUI extends JFrame {

    private final JTextArea outputArea;

//    public AppUI() {
//        setTitle("Fawry E-Commerce");
//        setSize(600, 400);
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//        outputArea = new JTextArea();
//        outputArea.setEditable(false);
//        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
//        add(new JScrollPane(outputArea), BorderLayout.CENTER);
//
//        JButton checkoutButton = new JButton("Checkout");
//        checkoutButton.addActionListener(e -> runCheckout());
//        add(checkoutButton, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
    public AppUI() {
        setTitle("Fawry E-Commerce");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Create buttons
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> runCheckout());

        JButton testEmptyCartButton = new JButton("Test Empty Cart");
        testEmptyCartButton.addActionListener(e -> {
            runWithCapturedOutput(() -> {
                Customer customer = new Customer("Ali", 1000);
                Cart cart = new Cart();
                CheckoutService.checkout(customer, cart);
            });
        });

        JButton testExpiredProductButton = new JButton("Test Expired Product");
        testExpiredProductButton.addActionListener(e -> {
            runWithCapturedOutput(() -> {
                Customer customer = new Customer("Mona", 1000);
                Product expired = new Cheese("Old Cheese", 100, 1, 0.3, LocalDate.of(2020, 1, 1));
                Cart cart = new Cart();
                cart.add(expired, 1);
                CheckoutService.checkout(customer, cart);
            });
        });

        JButton testOutOfStockButton = new JButton("Test Out of Stock");
        testOutOfStockButton.addActionListener(e -> {
            runWithCapturedOutput(() -> {
                Customer customer = new Customer("Sara", 1000);
                Product product = new Cheese("Cheese", 100, 1, 0.5, LocalDate.of(2025, 8, 1));
                Cart cart = new Cart();
                cart.add(product, 2); // more than available
                CheckoutService.checkout(customer, cart);
            });
        });

        JButton testInsufficientBalanceButton = new JButton("Test Insufficient Balance");
        testInsufficientBalanceButton.addActionListener(e -> {
            runWithCapturedOutput(() -> {
                Customer customer = new Customer("Youssef", 10);
                Product product = new Cheese("Cheese", 100, 2, 0.5, LocalDate.of(2025, 8, 1));
                Cart cart = new Cart();
                cart.add(product, 1);
                CheckoutService.checkout(customer, cart);
            });
        });

        // Add buttons to bottom panel
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(checkoutButton);
        bottomPanel.add(testEmptyCartButton);
        bottomPanel.add(testExpiredProductButton);
        bottomPanel.add(testOutOfStockButton);
        bottomPanel.add(testInsufficientBalanceButton);

        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void runWithCapturedOutput(Runnable task) {
        outputArea.setText("");
        PrintStream consoleStream = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputArea.append(String.valueOf((char) b));
            }
        }));

        try {
            task.run();
        } catch (Exception ex) {
            outputArea.append("\nError: " + ex.getMessage());
        }

        System.setOut(consoleStream);
    }

    private Cart prepareSampleCart() {
        Product cheese = new Cheese("Cheese", 100, 5, 0.2, LocalDate.of(2025, 7, 10));
        Product biscuits = new Cheese("Biscuits", 150, 2, 0.7, LocalDate.of(2025, 8, 1));
        Product tv = new TV("TV", 300, 3, 5);
        Product scratchCard = new ScratchCard("Scratch Card", 50, 10);

        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        return cart;
    }

    private void runCheckout() {
        Customer customer = new Customer("Hassan", 1000);
        Cart cart = prepareSampleCart();

        outputArea.setText("");
        PrintStream consoleStream = System.out;
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                outputArea.append(String.valueOf((char) b));
            }
        }));

        // run checkout
        try {
            CheckoutService.checkout(customer, cart);
        } catch (RuntimeException ex) {
            outputArea.append("\nError: " + ex.getMessage());
        }

        // return to default output
        System.setOut(consoleStream);
    }
}
