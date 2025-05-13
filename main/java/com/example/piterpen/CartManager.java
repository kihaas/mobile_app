package com.example.piterpen;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<CartItem> cartItems = new ArrayList<>();

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Product product) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equals(product.getName())) {
                item.incrementQuantity();
                return;
            }
        }
        cartItems.add(new CartItem(product, 1));
    }

    public void removeFromCart(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        double total = 0;
        for (CartItem item : cartItems) {
            String priceStr = item.getProduct().getPrice().replace("$", "");
            total += Double.parseDouble(priceStr) * item.getQuantity();
        }
        return total;
    }

    public void clearCart() {
        cartItems.clear();
    }
}