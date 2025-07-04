package com.fawry.challenge.fawryecommerce.cart;

import com.fawry.challenge.fawryecommerce.model.Product;

public class CartItem {

    public Product product;
    public int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
