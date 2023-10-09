package drop.swift.sale.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import drop.swift.sale.R;
import drop.swift.sale.category.CategoryViewHolder;
import drop.swift.sale.model.CategoryModel;
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
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
