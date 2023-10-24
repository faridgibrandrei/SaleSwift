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

import drop.swift.sale.cart.CartAdapter;
import drop.swift.sale.category.CategoryAdapter;
import drop.swift.sale.manager.OngoingOrderManager;
import drop.swift.sale.model.CartModel;
import drop.swift.sale.model.CategoryModel;
import drop.swift.sale.model.OngoingOrderModel;
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
        categoryModels.add(new CategoryModel("All"));
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
        productModels.add(new ProductModel("P1", "product 1", "url-1", 10000, 5));
        productModels.add(new ProductModel("P2", "product 2", "url-2", 15000, 4));
        productModels.add(new ProductModel("P3", "product 3", "url-3", 13500, 8));
        productModels.add(new ProductModel("P4", "product 4", "url-4", 22000, 9));
        productModels.add(new ProductModel("P5", "product 5", "url-5", 22000, 14));
        productModels.add(new ProductModel("P6", "product 6", "url-6", 28500, 4));
        productModels.add(new ProductModel("P7", "product 7", "url-7", 16000, 13));
        productModels.add(new ProductModel("P8", "product 8", "url-8", 16500, 13));
        productModels.add(new ProductModel("P9", "product 9", "url-9", 16000, 11));
        productModels.add(new ProductModel("P10", "product 10", "url-10", 16000, 2));
        productModels.add(new ProductModel("P11", "product 11", "url-11", 18000, 3));
        productModels.add(new ProductModel("P12", "product 12", "url-12", 19500, 20));
        productModels.add(new ProductModel("P13", "product 13", "url-13", 21000, 12));
        productModels.add(new ProductModel("P14", "product 14", "url-14", 21000, 7));
        ProductAdapter productAdapter = new ProductAdapter(productModels);
        productRecyclerView.setAdapter(productAdapter);

        RecyclerView cartRecyclerView = view.findViewById(R.id.cart_recyclerview);
        RecyclerView.LayoutManager cartLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(cartLayoutManager);

        List<CartModel> cartModels = new ArrayList<>();
        cartModels.add(new CartModel("product A", "url-A", 1000, 2));
        cartModels.add(new CartModel("product A", "url-A", 1000, 2));
        cartModels.add(new CartModel("product A", "url-A", 1000, 2));
        CartAdapter cartAdapter = new CartAdapter(cartModels);
        cartRecyclerView.setAdapter(cartAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
