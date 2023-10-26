package drop.swift.sale.product;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import drop.swift.sale.R;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView productName, productPrice, productStock, tv_quantity;
    public TextView productNameQuantityLayer, productPriceQuantityLayer;
    public ImageView displayProduct;
    public ImageButton btn_decrease_quantity, btn_increase_quantity;
    public ConstraintLayout quantityLayer;

    public CardView productContainer;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        displayProduct = itemView.findViewById(R.id.iv_product_display);
        productName = itemView.findViewById(R.id.tv_product_name);
        productPrice = itemView.findViewById(R.id.tv_product_price);
        productStock = itemView.findViewById(R.id.tv_product_unit);
        productContainer = itemView.findViewById(R.id.product_container);
        quantityLayer = itemView.findViewById(R.id.quantity_layer);
        productNameQuantityLayer = itemView.findViewById(R.id.tv_product_name_quantity_layer);
        productPriceQuantityLayer = itemView.findViewById(R.id.tv_product_price_quantity_layer);
        btn_decrease_quantity = itemView.findViewById(R.id.btn_decrease_quantity);
        btn_increase_quantity = itemView.findViewById(R.id.btn_increase_quantity);
        tv_quantity = itemView.findViewById(R.id.tv_quantity);
    }
}
