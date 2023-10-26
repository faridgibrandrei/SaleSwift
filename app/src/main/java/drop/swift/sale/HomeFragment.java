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

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import drop.swift.sale.cart.CartAdapter;
import drop.swift.sale.category.CategoryAdapter;
import drop.swift.sale.manager.OngoingOrderManager;
import drop.swift.sale.model.CategoryModel;
import drop.swift.sale.model.OngoingOrderModel;
import drop.swift.sale.model.ProductListModel;
import drop.swift.sale.model.ProductModel;
import drop.swift.sale.module.firebase.FirebaseHelper;
import drop.swift.sale.product.ProductAdapter;

public class HomeFragment extends Fragment {

    private CartAdapter cartAdapter;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        new FirebaseHelper().readDatabaseOnce(dataSnapshot -> {
            //--- PRODUCT ADAPTER ---//
            RecyclerView productRecyclerView = view.findViewById(R.id.product_recyclerview);
            RecyclerView.LayoutManager productLayoutManager = new GridLayoutManager(getContext(), 4, RecyclerView.VERTICAL, false);
            productRecyclerView.setLayoutManager(productLayoutManager);

            ProductListModel productList = ProductListModel.getInstance();
            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                productList.addProduct(snapshot.getValue(ProductModel.class));
            }

            productAdapter = new ProductAdapter(productList);
            productRecyclerView.setAdapter(productAdapter);
        }, "product");

        //--- CATEGORY ADAPTER ---//
        RecyclerView categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        RecyclerView.LayoutManager categoryLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);

        List<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels.add(new CategoryModel("All"));
        categoryModels.add(new CategoryModel("Vape"));
        categoryModels.add(new CategoryModel("Freebase"));
        categoryModels.add(new CategoryModel("Salt Nic"));
        categoryModels.add(new CategoryModel("Accessories"));
        categoryAdapter = new CategoryAdapter(categoryModels);
        categoryRecyclerView.setAdapter(categoryAdapter);


        //--- CART ADAPTER ---//
        RecyclerView cartRecyclerView = view.findViewById(R.id.cart_recyclerview);
        RecyclerView.LayoutManager cartLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        cartRecyclerView.setLayoutManager(cartLayoutManager);

        cartAdapter = new CartAdapter(new OngoingOrderModel());
        cartRecyclerView.setAdapter(cartAdapter);
        OngoingOrderManager.getInstance().registerObserver(cartAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OngoingOrderManager.getInstance().unregisterObserver(cartAdapter);
    }
}
