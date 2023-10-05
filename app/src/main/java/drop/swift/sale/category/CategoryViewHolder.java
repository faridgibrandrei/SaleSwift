package drop.swift.sale.category;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import drop.swift.sale.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder {
    public TextView categoryText;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryText = itemView.findViewById(R.id.tv_category);
    }
}
