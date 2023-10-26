package drop.swift.sale.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import drop.swift.sale.R;
import drop.swift.sale.manager.OngoingOrderManager;
import drop.swift.sale.model.OngoingOrderItemModel;
import drop.swift.sale.model.OngoingOrderModel;
import drop.swift.sale.module.helper.ImageHelper;
import drop.swift.sale.observer.OngoingOrderObserver;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> implements OngoingOrderObserver {
    private OngoingOrderModel ongoingOrderModel;
    private int selectedItemPosition = -1; // Initially, no item is selected

    public CartAdapter(OngoingOrderModel ongoingOrderModel) {
        this.ongoingOrderModel = ongoingOrderModel;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OngoingOrderItemModel selectedCart = ongoingOrderModel.getOngoingOrderItemModel().get(position);
        holder.cartContainer.setBackgroundResource(R.drawable.card_cart);

        String dummyImageUrl = "https://store.geekvape.com/cdn/shop/products/Aegis-Legend2-500-5003_692f7f4c-b642-4958-87b1-f1835812d202.jpg?v=1669371676";
        ImageHelper.loadFixImage(dummyImageUrl, holder.cartDisplayProduct);
    }

    @Override
    public int getItemCount() {
        if (ongoingOrderModel.getOngoingOrderItemModel() != null) {
            return ongoingOrderModel.getOngoingOrderItemModel().size();
        } else {
            return 0;
        }
    }

    @Override
    public void onOngoingOrderUpdate() {
        OngoingOrderManager ongoingOrderManager = OngoingOrderManager.getInstance();
        this.ongoingOrderModel = ongoingOrderManager.getOngoingOrder();
        notifyDataSetChanged();
    }
}
