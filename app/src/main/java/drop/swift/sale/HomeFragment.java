package drop.swift.sale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import drop.swift.sale.category.CategoryAdapter;
import drop.swift.sale.model.CategoryModel;
import drop.swift.sale.model.ProductModel;
import drop.swift.sale.product.ProductAdapter;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        RecyclerView categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        List<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("Vape"));
        categoryModels.add(new CategoryModel("Freebase"));
        categoryModels.add(new CategoryModel("Salt Nic"));
        categoryModels.add(new CategoryModel("Accessories"));
        CategoryAdapter categoryAdapter = new CategoryAdapter(categoryModels);
        categoryRecyclerView.setAdapter(categoryAdapter);

        RecyclerView productRecyclerView = view.findViewById(R.id.product_recyclerview);
        RecyclerView.LayoutManager productLayoutManager = new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false);
        productRecyclerView.setLayoutManager(productLayoutManager);

        List<ProductModel> productModels = new ArrayList<>();
        productModels.add(new ProductModel("product A", "url-A", 1000, 5));
        productModels.add(new ProductModel("product A", "url-A", 1000, 5));
        productModels.add(new ProductModel("product A", "url-A", 1000, 5));
        productModels.add(new ProductModel("product A", "url-A", 1000, 5));
        productModels.add(new ProductModel("product A", "url-A", 1000, 5));
        ProductAdapter productAdapter = new ProductAdapter(productModels);
        productRecyclerView.setAdapter(productAdapter);

        return view;
    }
}
