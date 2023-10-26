package drop.swift.sale.model;

public class ProductModel {

    private String productId; //(Primary Key)
    private String name;
    private String imageUrl;
    private int price;
    private int stock;

//    private String category;

    public ProductModel() {
        // Default constructor required for Firebase
    }

    public ProductModel(String productId, String name, String imageUrl, int price, int stock) {
        this.productId = productId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}

