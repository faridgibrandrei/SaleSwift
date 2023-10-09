package drop.swift.sale.product;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import drop.swift.sale.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView productName, productPrice, productStock;
    public ImageView displayProduct;

    public CardView productContainer;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        productName = itemView.findViewById(R.id.tv_product_name);
        productPrice = itemView.findViewById(R.id.tv_product_price);
        productStock = itemView.findViewById(R.id.tv_product_unit);
        productContainer = itemView.findViewById(R.id.product_container);
    }
}
