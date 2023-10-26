package drop.swift.sale.cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import drop.swift.sale.R;

public class CartViewHolder extends RecyclerView.ViewHolder {
    public ImageView cartDisplayProduct;
    public TextView cartProductName;
    public TextView cartProductPrice;
    public TextView cartProductQuantity;
    public CardView cartContainer;

    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        cartDisplayProduct = itemView.findViewById(R.id.iv_cart_product_display);
        cartContainer = itemView.findViewById(R.id.cart_container);
        cartProductName = itemView.findViewById(R.id.tv_cart_product_name);
        cartProductPrice = itemView.findViewById(R.id.tv_cart_product_price);
    }
}
