package com.fawry.challenge.fawryecommerce.cart;

import com.fawry.challenge.fawryecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private final List<CartItem> items = new ArrayList<>();

    public void add(Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        for (CartItem item : items) {
            if (item.product == product) { // نفس الكائن
                int newQuantity = item.quantity + quantity;
                if (newQuantity > product.getQuantity()) {
                    throw new IllegalArgumentException("Not enough stock for " + product.getName());
                }
                item.quantity = newQuantity;
                return;
            }
        }

        if (quantity > product.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock for " + product.getName());
        }

        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
