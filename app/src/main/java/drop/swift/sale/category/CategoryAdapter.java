package drop.swift.sale.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import drop.swift.sale.R;
import drop.swift.sale.model.CategoryModel;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {
    private List<CategoryModel> categoryList; // Replace with your data type

    public CategoryAdapter(List<CategoryModel> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String categoryName = categoryList.get(position).getName();
        holder.categoryText.setText(categoryName);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
