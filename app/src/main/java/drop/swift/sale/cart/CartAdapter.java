package drop.swift.sale.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import drop.swift.sale.R;
import drop.swift.sale.category.CategoryViewHolder;
import drop.swift.sale.model.CartModel;
import drop.swift.sale.model.CategoryModel;
import drop.swift.sale.model.OngoingOrderModel;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private List<CartModel> cartList;
    private OngoingOrderModel ongoingOrderModel;
    private int selectedItemPosition = -1; // Initially, no item is selected

    public CartAdapter(List<CartModel> cartList) {
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartModel selectedCart = cartList.get(position);

        holder.cartContainer.setBackgroundResource(R.drawable.card_cart);
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }
}
