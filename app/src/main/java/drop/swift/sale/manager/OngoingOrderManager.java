package drop.swift.sale.manager;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import drop.swift.sale.model.OngoingOrderItemModel;
import drop.swift.sale.model.OngoingOrderModel;

public class OngoingOrderManager {
    private static OngoingOrderManager instance;
    private ArrayList<OngoingOrderItemModel> ongoingOrderItemModels;
    private OngoingOrderModel ongoingOrderModels;

    public OngoingOrderManager() {
        this.ongoingOrderItemModels = new ArrayList<>();
        ongoingOrderModels = new OngoingOrderModel();
    }

    public static synchronized OngoingOrderManager getInstance() {
        if (instance == null) {
            instance = new OngoingOrderManager();
        }
        return instance;
    }

    public OngoingOrderModel getOngoingOrder() {
        return ongoingOrderModels;
    }

    public void addToOngoingOrder(String productId) {
        OngoingOrderItemModel existingItem = findExistingItem(productId);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + 1);
        } else {
            OngoingOrderItemModel newOngoingOrderItemModel = new OngoingOrderItemModel(productId, 1);
            ongoingOrderItemModels.add(newOngoingOrderItemModel);
        }
        ongoingOrderModels.setOngoingOrderItemModel(ongoingOrderItemModels);
    }

    public void removeFromOngoingOrder(String productId) {
        OngoingOrderItemModel existingItem = findExistingItem(productId);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() - 1;
            if (newQuantity > 0) {
                existingItem.setQuantity(newQuantity);
            } else {
                ongoingOrderItemModels.remove(existingItem);
            }
            ongoingOrderModels.setOngoingOrderItemModel(ongoingOrderItemModels);
            if (ongoingOrderModels.getOngoingOrderItemModel().size()==0)
                ongoingOrderModels.setOngoingOrderItemModel(null);
        }
    }

    public OngoingOrderItemModel findExistingItem(String productId) {
        for (OngoingOrderItemModel item : ongoingOrderItemModels) {
            if (item.getProductId().equals(productId)) {
                return item;
            }
        }
        return null;
    }
}
