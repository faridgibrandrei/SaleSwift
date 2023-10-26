package drop.swift.sale.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Optional;

import drop.swift.sale.R;
import drop.swift.sale.manager.OngoingOrderManager;
import drop.swift.sale.model.OngoingOrderItemModel;
import drop.swift.sale.model.ProductListModel;
import drop.swift.sale.model.ProductModel;
import drop.swift.sale.module.Util;
import drop.swift.sale.module.helper.ImageHelper;

public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private ProductListModel productList;
    private int selectedItemPosition = -1; // Initially, no item is selected

    public ProductAdapter(ProductListModel productList) {
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
        ProductModel selectedProduct = productList.getProductModels().get(holder.getAdapterPosition());

        OngoingOrderManager ongoingOrderManager = OngoingOrderManager.getInstance();
        if (ongoingOrderManager.getOngoingOrder().getOngoingOrderItemModel()!=null) {
            Optional<Integer> isSelectedProductOrdered = ongoingOrderManager.getOngoingOrder().getOngoingOrderItemModel().stream()
                    .filter(obj -> obj.getProductId().equals(selectedProduct.getProductId()))
                    .map(OngoingOrderItemModel::getQuantity)
                    .findFirst();
            if (isSelectedProductOrdered.isPresent()) {
                holder.tv_quantity.setText(String.valueOf(isSelectedProductOrdered.get()));
            } else {
                holder.tv_quantity.setText("0");
            }
        }

        // Product Name
        holder.productName.setText(selectedProduct.getName());

        // Product Stock
        holder.productStock.setText(String.format("%s %s", selectedProduct.getStock(), " items"));

        // Product Price
        holder.productPrice.setText(Util.currencyFormatting(selectedProduct.getPrice()));

        // Product Image
        ImageHelper.loadFixImage(selectedProduct.getImageUrl(), holder.displayProduct);

        // Set CardView's selected state based on the position
        holder.productContainer.setSelected(holder.getAdapterPosition() == selectedItemPosition);

        // Apply different background drawables for selected and unselected states
        if (holder.getAdapterPosition() == selectedItemPosition) {
            holder.productContainer.setBackgroundResource(R.drawable.card_category_selected);
            holder.quantityLayer.setVisibility(View.VISIBLE);
        } else {
            holder.productContainer.setBackgroundResource(R.drawable.card_category_unselected);
            holder.quantityLayer.setVisibility(View.GONE);
        }

        // Quantity Layer
        holder.quantityLayer.setOnClickListener(view -> {
            // do nothing so the layer wont close if user miss touch the plus minus button
        });

        // Button increase item quantity
        holder.btn_increase_quantity.setOnClickListener(view -> {
            ongoingOrderManager.addToOngoingOrder(selectedProduct.getProductId());
            notifyItemChanged(holder.getAdapterPosition());
        });

        // Button decrease item quantity
        holder.btn_decrease_quantity.setOnClickListener(view -> {
            ongoingOrderManager.removeFromOngoingOrder(selectedProduct.getProductId());
            notifyItemChanged(holder.getAdapterPosition());
        });

        // Product Name Quantity Layer
        holder.productNameQuantityLayer.setText(selectedProduct.getName());

        // Product Price Quantity Layer
        holder.productPriceQuantityLayer.setText(Util.currencyFormatting(selectedProduct.getPrice()));

        // Handle item click to update the selected item
        holder.itemView.setOnClickListener(v -> {
            int previousSelected = selectedItemPosition;
            if (selectedItemPosition != holder.getAdapterPosition()) {
                // update selected item
                selectedItemPosition = holder.getAdapterPosition();
                notifyItemChanged(previousSelected); // Deselect the previous item
                notifyItemChanged(holder.getAdapterPosition()); // Select the new item
            } else {
                // unselect item
                selectedItemPosition = -1;
                notifyItemChanged(previousSelected); // Deselect the previous item
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.getProductModels().size();
    }
}
