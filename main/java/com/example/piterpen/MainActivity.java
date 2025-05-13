package com.example.piterpen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener {

    private List<Product> productList = new ArrayList<>();
    private ProductAdapter adapter;
    private ImageView cartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.productRecycler);
        cartIcon = findViewById(R.id.cartIcon);

        // Настройка сетки (2 колонки)
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Инициализация списка товаров
        initializeProducts();

        // Устанавливаем адаптер
        adapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(adapter);

        // Настройка кнопок категорий
        setupCategoryButtons();

        ImageView cartIcon = findViewById(R.id.cartIcon);

        // Настройка иконки корзины
        cartIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }

    private void initializeProducts() {
        productList.add(new Product("Orange raf", "$2.99", R.drawable.orraf, "Coffee"));
        productList.add(new Product("Donut", "$4.99", R.drawable.donut, "Cakes"));
        productList.add(new Product("Latte", "$4.75", R.drawable.latte, "Coffee"));
        productList.add(new Product("Cappuccino", "$3.75", R.drawable.cappuccino, "Coffee"));
        productList.add(new Product("Flat White", "$3.50", R.drawable.flatwhite, "Coffee"));
        productList.add(new Product("Vanila Raf", "$4.75", R.drawable.vanraf, "Coffee"));
        productList.add(new Product("Black Tea", "$1.99", R.drawable.teagreen, "Tea"));
        productList.add(new Product("Green Tea", "$1.99", R.drawable.teablack, "Tea"));
        productList.add(new Product("Tea Spicy Mango", "$4.50", R.drawable.teamango, "Tea"));
        productList.add(new Product("Tea Raspberry Mint", "$4.50", R.drawable.rasberry, "Tea"));
        productList.add(new Product("Croissant", "$2.99", R.drawable.croissant, "Cakes"));
        productList.add(new Product("Cake", "$5.99", R.drawable.cake, "Cakes"));
        productList.add(new Product("Cookie", "$1.50", R.drawable.cookie, "Cakes"));
        productList.add(new Product("Cocoa", "$3.25", R.drawable.cocoa, "Other"));
        productList.add(new Product("Smoothie", "$4.75", R.drawable.smoothie, "Other"));
        productList.add(new Product("Milk Shake", "$4.75", R.drawable.milk, "Other"));
    }


    private void setupCategoryButtons() {
        Button coffeeBtn = findViewById(R.id.coffeeBtn);
        Button teaBtn = findViewById(R.id.teaBtn);
        Button cakesBtn = findViewById(R.id.cakesBtn);
        Button otherBtn = findViewById(R.id.otherBtn);
        Button allBtn = findViewById(R.id.allBtn);

        coffeeBtn.setOnClickListener(v -> filterProducts("Coffee"));
        teaBtn.setOnClickListener(v -> filterProducts("Tea"));
        cakesBtn.setOnClickListener(v -> filterProducts("Cakes"));
        otherBtn.setOnClickListener(v -> filterProducts("Other"));
        allBtn.setOnClickListener(v -> adapter.updateList(productList));
    }

    private void filterProducts(String category) {
        List<Product> filteredList = new ArrayList<>();
        for (Product product : productList) {
            if (product.getCategory().equals(category)) {
                filteredList.add(product);
            }
        }
        adapter.updateList(filteredList);
    }

    @Override
    public void onProductClick(Product product) {
        // Можно реализовать просмотр деталей товара
        Toast.makeText(this, product.getName() + " selected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAddToCartClick(Product product) {
        CartManager.getInstance().addToCart(product);
        Toast.makeText(this, product.getName() + " added to cart", Toast.LENGTH_SHORT).show();
    }
}