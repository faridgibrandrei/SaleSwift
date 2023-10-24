package drop.swift.sale.model;

import java.util.ArrayList;
import java.util.UUID;

public class OngoingOrderModel {

    private UUID orderId;
    private ArrayList<OngoingOrderItemModel> ongoingOrderItemModel;

    public OngoingOrderModel() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public ArrayList<OngoingOrderItemModel> getOngoingOrderItemModel() {
        return ongoingOrderItemModel;
    }

    public void setOngoingOrderItemModel(ArrayList<OngoingOrderItemModel> ongoingOrderItemModel) {
        this.ongoingOrderItemModel = ongoingOrderItemModel;
    }
}
