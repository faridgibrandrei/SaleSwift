package drop.swift.sale;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import drop.swift.sale.model.OngoingOrderItemModel;
import drop.swift.sale.model.OngoingOrderModel;
import drop.swift.sale.model.ProductListModel;
import drop.swift.sale.model.ProductModel;
import drop.swift.sale.module.Util;
import drop.swift.sale.module.firebase.FirebaseCallback;
import drop.swift.sale.module.firebase.FirebaseHelper;
import drop.swift.sale.observer.OngoingOrderObserver;
import drop.swift.sale.product.ProductAdapter;

public class HomeFragment extends Fragment implements OngoingOrderObserver {

    private CartAdapter cartAdapter;
    private ProductAdapter productAdapter;
    private CategoryAdapter categoryAdapter;

    private TextView tv_subtotal, tv_tax, tv_total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        tv_subtotal = view.findViewById(R.id.tv_subtotal_val);
        tv_tax = view.findViewById(R.id.tv_tax_val);
        tv_total = view.findViewById(R.id.tv_total_val);

        tv_subtotal.setText(Util.currencyFormatting(0));
        tv_tax.setText(Util.currencyFormatting(0));
        tv_total.setText(Util.currencyFormatting(0));

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
        OngoingOrderManager.getInstance().registerObserver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        OngoingOrderManager.getInstance().unregisterObserver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OngoingOrderManager.getInstance().unregisterObserver(cartAdapter);
    }

    @Override
    public void onOngoingOrderUpdate() {
        int subTotal = 0;

        OngoingOrderManager ongoingOrderManager = OngoingOrderManager.getInstance();
        if (ongoingOrderManager.getOngoingOrder()!=null && ongoingOrderManager.getOngoingOrder().getOngoingOrderItemModel()!=null) {
            for (OngoingOrderItemModel ongoingItem : ongoingOrderManager.getOngoingOrder().getOngoingOrderItemModel()) {
                ProductModel product = ProductListModel.getInstance().getProductById(ongoingItem.getProductId());
                subTotal += ongoingItem.getQuantity() * product.getPrice();
            }
        }

        int tax = subTotal * 10 / 100;
        int total = subTotal + tax;

        tv_subtotal.setText(Util.currencyFormatting(subTotal));
        tv_tax.setText(Util.currencyFormatting(tax));
        tv_total.setText(Util.currencyFormatting(total));
    }
}
