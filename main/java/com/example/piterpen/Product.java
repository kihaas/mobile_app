package com.example.piterpen;

public class Product {
    private String name;
    private String price;
    private int imageResId;
    private String category;

    public Product(String name, String price, int imageResId, String category) {
        this.name = name;
        this.price = price;
        this.imageResId = imageResId;
        this.category = category;
    }

    // Геттеры
    public String getCategory() {
        return category;
    }
    public String getName() { return name; }
    public String getPrice() { return price; }
    public int getImageResId() { return imageResId; }

}