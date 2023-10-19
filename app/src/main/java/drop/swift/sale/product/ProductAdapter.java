package drop.swift.sale.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import drop.swift.sale.R;
import drop.swift.sale.model.ProductModel;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private List<ProductModel> productList;
    private int selectedItemPosition = -1; // Initially, no item is selected

    public ProductAdapter(List<ProductModel> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductModel selectedProduct = productList.get(position);

        // Set the CardView's selected state based on the position
        holder.productContainer.setSelected(position == selectedItemPosition);

        // Apply different background drawables for selected and unselected states
        if (position == selectedItemPosition) {
            holder.productContainer.setBackgroundResource(R.drawable.card_category_selected);
            holder.quantityLayer.setVisibility(View.VISIBLE);
        } else {
            holder.productContainer.setBackgroundResource(R.drawable.card_category_unselected);
            holder.quantityLayer.setVisibility(View.GONE);
        }

        holder.quantityLayer.setOnClickListener(view -> {
            // do nothing so the layer wont close if user miss touch the plus minus button
            return;
        });

        // Handle item click to update the selected item
        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedItemPosition;
            if (selectedItemPosition != position) {
                // update selected item
                selectedItemPosition = position;
                notifyItemChanged(previousSelected); // Deselect the previous item
                notifyItemChanged(position); // Select the new item
            } else {
                // unselect item
                selectedItemPosition = -1;
                notifyItemChanged(previousSelected); // Deselect the previous item
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
