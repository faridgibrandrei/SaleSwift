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
    private List<CategoryModel> categoryList;
    private int selectedItemPosition = -1; // Initially, no item is selected

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
        CategoryModel selectedCategory = categoryList.get(position);

        // Set the CardView's selected state based on the position
        holder.categoryContainer.setSelected(position == selectedItemPosition);

        // Apply different background drawables for selected and unselected states
        if (position == selectedItemPosition) {
            holder.categoryContainer.setBackgroundResource(R.drawable.card_category_selected);
        } else {
            holder.categoryContainer.setBackgroundResource(R.drawable.card_category_unselected);
        }

        // Handle item click to update the selected item
        holder.itemView.setOnClickListener(v -> {
            if (selectedItemPosition != position) {
                int previousSelected = selectedItemPosition;
                selectedItemPosition = position;
                notifyItemChanged(previousSelected); // Deselect the previous item
                notifyItemChanged(position); // Select the new item
            }
        });

        String categoryName = selectedCategory.getName();
        holder.categoryText.setText(categoryName);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }
}
