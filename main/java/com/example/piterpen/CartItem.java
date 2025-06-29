package com.example.piterpen;

public class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public void incrementQuantity() { quantity++; }
    public void decrementQuantity() { if(quantity > 1) quantity--; }
}