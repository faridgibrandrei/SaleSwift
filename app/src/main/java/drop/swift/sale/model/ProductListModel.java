package drop.swift.sale.model;

import java.util.ArrayList;

import drop.swift.sale.manager.OngoingOrderManager;

public class ProductListModel {
    private ArrayList<ProductModel> productModels;
    private static ProductListModel instance;

    public ProductListModel() {
        this.productModels = new ArrayList<>();
    }

    public static synchronized ProductListModel getInstance() {
        if (instance == null) {
            instance = new ProductListModel();
        }
        return instance;
    }

    public void addProduct(ProductModel productModel) {
        this.productModels.add(productModel);
    }

    public ArrayList<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(ArrayList<ProductModel> productModels) {
        this.productModels = productModels;
    }

    public ProductModel getProductById(String productId) {
        for (ProductModel product : productModels) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null if no product with the given productId is found
    }
}
