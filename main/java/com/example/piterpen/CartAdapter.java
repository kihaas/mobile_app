package com.example.piterpen;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;
    private final CartInteractionListener listener;

    public interface CartInteractionListener {
        void onItemRemoved(CartItem cartItem);
        void onQuantityChanged();
    }

    public CartAdapter(List<CartItem> cartItems, CartInteractionListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        Product product = cartItem.getProduct();

        holder.productImage.setImageResource(product.getImageResId());
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.quantityText.setText(String.valueOf(cartItem.getQuantity()));

        holder.removeBtn.setOnClickListener(v -> listener.onItemRemoved(cartItem));
        holder.increaseBtn.setOnClickListener(v -> {
            cartItem.incrementQuantity();
            holder.quantityText.setText(String.valueOf(cartItem.getQuantity()));
            listener.onQuantityChanged();
        });
        holder.decreaseBtn.setOnClickListener(v -> {
            cartItem.decrementQuantity();
            holder.quantityText.setText(String.valueOf(cartItem.getQuantity()));
            listener.onQuantityChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public void updateCartItems(List<CartItem> newCartItems) {
        cartItems = newCartItems;
        notifyDataSetChanged();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        TextView quantityText;
        ImageButton increaseBtn;
        ImageButton decreaseBtn;
        ImageButton removeBtn;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.cartProductImage);
            productName = itemView.findViewById(R.id.cartProductName);
            productPrice = itemView.findViewById(R.id.cartProductPrice);
            quantityText = itemView.findViewById(R.id.quantityText);
            increaseBtn = itemView.findViewById(R.id.increaseBtn);
            decreaseBtn = itemView.findViewById(R.id.decreaseBtn);
            removeBtn = itemView.findViewById(R.id.removeBtn);
        }
    }
}