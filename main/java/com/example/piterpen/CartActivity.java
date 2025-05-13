package com.example.piterpen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartInteractionListener {

    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private TextView totalPriceView;
    private Button checkoutBtn;
    private Button clearCartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartRecycler);
        totalPriceView = findViewById(R.id.totalPrice);
        checkoutBtn = findViewById(R.id.checkoutBtn);
        clearCartBtn = findViewById(R.id.clearCartBtn);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(CartManager.getInstance().getCartItems(), this);
        recyclerView.setAdapter(adapter);

        updateTotalPrice();

        checkoutBtn.setOnClickListener(v -> {
            Toast.makeText(this, "Order placed! Total: $" +
                            String.format(Locale.US, "%.2f", CartManager.getInstance().getTotalPrice()),
                    Toast.LENGTH_LONG).show();
            CartManager.getInstance().clearCart();
            finish();
        });

        clearCartBtn.setOnClickListener(v -> {
            CartManager.getInstance().clearCart();
            adapter.updateCartItems(CartManager.getInstance().getCartItems());
            updateTotalPrice();
        });
    }

    private void updateTotalPrice() {
        double total = CartManager.getInstance().getTotalPrice();
        totalPriceView.setText(String.format(Locale.US, "Total: $%.2f", total));
    }

    @Override
    public void onItemRemoved(CartItem cartItem) {
        CartManager.getInstance().removeFromCart(cartItem);
        updateTotalPrice();
    }

    @Override
    public void onQuantityChanged() {
        updateTotalPrice();
    }
}